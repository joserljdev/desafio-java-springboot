package br.com.compassouol.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.compassouol.catalogo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query("SELECT p FROM Product p "
			+ "WHERE p.price BETWEEN :min AND :max "
			+ "AND (p.name LIKE %:q% OR p.description LIKE %:q%)")
	List<Product> listaProdutosFiltrados(@PathVariable Double min, @PathVariable Double max, @PathVariable String q);
	
}