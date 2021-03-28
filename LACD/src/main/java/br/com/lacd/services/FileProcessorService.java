package br.com.lacd.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lacd.model.DataList;
import br.com.lacd.model.Product;
import br.com.lacd.model.repositories.ProductRepository;

@Service
public class FileProcessorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorService.class);
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Async
    public CompletableFuture<List<Product>> registryProducts(final MultipartFile file) throws Exception {
		final long start = System.currentTimeMillis();
		
		List<Product> prods = parseJsonToObject(file);
		LOGGER.info("Saving a list of products of size {} records", prods.size());
		
		prods = repository.saveAll(prods);
		
		LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
		return CompletableFuture.completedFuture(prods);
    }

	/**
	 * Método responsável por converter um json para objeto Produto
	 * @param file
	 * @return
	 */
    private List<Product> parseJsonToObject(final MultipartFile file) throws Exception {
		final List<Product> prods = new ArrayList<>();

		try {
			final ObjectMapper mapper = new ObjectMapper();
			DataList dataList = mapper.readValue(
				new File(fileStorageService.getFilePath(StringUtils.cleanPath(file.getOriginalFilename()))), 
				DataList.class
			);
			
			List<Product> products = dataList.getData();
			products.stream().collect(Collectors.toCollection(() -> prods));
		} catch(final IOException e) {
		    LOGGER.error("Failed to parse JSON file {}", e);
		    throw new Exception("Failed to parse JSON file {}", e);
		}
		
		return prods;
    }

}
