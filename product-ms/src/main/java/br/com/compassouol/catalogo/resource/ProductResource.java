package br.com.compassouol.catalogo.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassouol.catalogo.dto.ProductDTO;
import br.com.compassouol.catalogo.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductDTO> criar(@Valid @RequestBody ProductDTO productDTO) {
		productDTO = this.productService.criar(productDTO);
		
		return ResponseEntity.created(null).body(productDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> atualizar(@PathVariable String id, 
			@Valid @RequestBody ProductDTO productDTO) {
		
		productDTO = this.productService.atualizar(id, productDTO);
		
		return ResponseEntity.ok(productDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> buscarPorId(@PathVariable String id) {
		ProductDTO productDTO =  this.productService.buscarPorId(id);
		
		return ResponseEntity.ok(productDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> listarTodos() {
		List<ProductDTO> productsDTO = this.productService.listarTodos();
		
		return ResponseEntity.ok(productsDTO);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> filtrarPorParametros(@RequestParam(required = false) Double min_price, 
			@RequestParam(required = false) Double max_price, @RequestParam(required = false) String q) {
		
		List<ProductDTO> productsDTO = this.productService.filtrarProdutos(min_price, max_price, q);

		return ResponseEntity.ok(productsDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable String id) {
		this.productService.remover(id);
		
		return ResponseEntity.ok(null);
	}
}