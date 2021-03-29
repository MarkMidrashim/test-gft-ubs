package br.com.lacd.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lacd.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { 
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.product) LIKE LOWER(:productName)")
	List<Product> findByProduct(@Param("productName") String productName);
}
