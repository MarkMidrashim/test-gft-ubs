package br.com.lacd.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.lacd.entities.Shopkeeper;
import br.com.lacd.entities.Product;
import br.com.lacd.model.repositories.ProductRepository;
import br.com.lacd.services.FileProcessorService;
import br.com.lacd.services.FileStorageService;
import br.com.lacd.services.ProductSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ProductController", tags= {"ProductController"})
@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {
	
	/* DI */
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private FileProcessorService fileProcessorService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private ProductSaleService productSaleService;
	
	/* ACTIONS */
	
	@ApiOperation(value="Find product by id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = repository.findById(id).get();
		return ResponseEntity.ok(product);
	}
	
	@ApiOperation(value="Find product by product name")
	@GetMapping(value="/findByProduct/{productName}", produces = {"application/json", "application/xml"})
	public ResponseEntity<List<Product>> findByProduct(@PathVariable String productName) {
		List<Product> products = repository.findByProduct(productName);
		return ResponseEntity.ok(products);
	}
	
	@ApiOperation(value="Get total product in stock")
	@GetMapping(value="/saleProductsPerShopkeepers", produces = {"application/json", "application/xml"})
	public ResponseEntity<?> saleProductsPerShopkeepers(
			@RequestParam(value="product") String productName, 
			@RequestParam(value="shopkeepers", defaultValue="1") Integer numberOfShopkeepers) {
		List<Product> products = repository.findByProduct(productName);		
		List<Shopkeeper> shopkeepers = productSaleService.divideStockProductPerShopkeepers(products, numberOfShopkeepers);
		return ResponseEntity.ok(shopkeepers);
	}
	
	@ApiOperation(value="Products uploading and deserializing file(s) with asynchronous parallelism")
	@PostMapping(value="/upload", produces = {"application/json", "application/xml"})
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		try {
			Arrays.asList(files)
				.stream()
				.map(file -> {
					try {
						fileStorageService.storageFile(file);
						fileProcessorService.registryProducts(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return file;
				})
				.collect(Collectors.toList());
			
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
}
