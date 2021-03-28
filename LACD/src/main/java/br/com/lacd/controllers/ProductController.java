package br.com.lacd.controllers;

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

import br.com.lacd.model.Product;
import br.com.lacd.model.repositories.ProductRepository;
import br.com.lacd.services.FileProcessorService;
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
	
	/* ACTIONS */
	
	@ApiOperation(value="Find product by id")
	@GetMapping(value="/{id}", produces = {"application/json", "application/xml"})
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product worker = repository.findById(id).get();
		return ResponseEntity.ok(worker);
	}
	
	@ApiOperation(value="Upload and process multiple files")
	@PostMapping(value="/uploadFiles", produces = {"application/json", "application/xml"})
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		try {
            for(final MultipartFile file: files) {
            	fileProcessorService.registryProducts(file);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
}
