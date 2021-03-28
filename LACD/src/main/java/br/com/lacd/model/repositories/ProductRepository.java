package br.com.lacd.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lacd.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { }
