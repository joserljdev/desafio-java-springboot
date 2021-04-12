package br.com.compassouol.catalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.compassouol.catalogo.dto.ProductDTO;
import br.com.compassouol.catalogo.entity.Product;
import br.com.compassouol.catalogo.exceptionhandler.ProductNotFoundException;
import br.com.compassouol.catalogo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	public ProductDTO criar(ProductDTO productDTO) {
		Product product = this.productRepository.save(new Product(productDTO));
		
		return new ProductDTO(product);
	}
	
	@Transactional
	public ProductDTO atualizar(String id, ProductDTO productDTO) {
		if(!this.productRepository.existsById(id))
			throw new ProductNotFoundException("O produto não pôde ser encontrado!");
		
		productDTO.setId(id);
		
		Product product = new Product(productDTO);
		this.productRepository.save(product);
	
		return new ProductDTO(product);
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> listarTodos() {
		List<Product> products = this.productRepository.findAll();
		
		return products.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<ProductDTO> filtrarProdutos(Double min_price, Double max_price, String q) {
		List<Product> productsFiltrados = this.productRepository.listaProdutosFiltrados(min_price, max_price, q);
		
		return productsFiltrados.stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ProductDTO buscarPorId(@PathVariable String id) {
		Optional<Product> optProduct = this.productRepository.findById(id);
		
		if(optProduct.isEmpty())
			throw new ProductNotFoundException("O produto não pôde ser encontrado!");
			
		return new ProductDTO(optProduct.get());
	}
	
	@Transactional
	public void remover(String id) {
		if(!this.productRepository.existsById(id))
			throw new ProductNotFoundException("O produto não pôde ser encontrado!");
		
		this.productRepository.deleteById(id);
	}
}