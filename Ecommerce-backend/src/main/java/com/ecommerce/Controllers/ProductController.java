package com.ecommerce.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Services.ProductService;
import com.ecommerce.payloads.ApiResponse;
import com.ecommerce.payloads.ProductDto;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
		ProductDto createdProduct = this.productService.createProduct(productDto);
		return new ResponseEntity<ProductDto>(createdProduct,HttpStatus.CREATED);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable("productId") Integer productId){
		ProductDto updateProduct = this.productService.updateProduct(productDto, productId);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Integer productId){
		ProductDto product = this.productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> allProducts = this.productService.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catId}")
	public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable("catId") Integer catId){
		List<ProductDto> allProducts = this.productService.getproductsByCategory(catId);
		return new ResponseEntity<List<ProductDto>>(allProducts,HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}")
	public ApiResponse deleteProduct(@PathVariable("productId") Integer productId) {
		this.productService.deleteProduct(productId);
		return new ApiResponse("The product is deleted successfully!!",true);
	}
	
	
	
	
	
	
}
