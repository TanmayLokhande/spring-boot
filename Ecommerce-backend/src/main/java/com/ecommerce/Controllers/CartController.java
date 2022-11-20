package com.ecommerce.Controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entities.User;
import com.ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Global.GlobalData;
import com.ecommerce.Repositories.UserRepo;
import com.ecommerce.Services.ProductService;
import com.ecommerce.payloads.ProductDto;
import com.ecommerce.payloads.UserDto;


@RestController
@RequestMapping("/api/cart/")
public class CartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/add-to-cart/{productId}")
	public ResponseEntity<List<ProductDto>> addToCart(@PathVariable("productId") Integer productId) {
		ProductDto productDto = this.productService.getProductById(productId);
		List<ProductDto> productDtos = GlobalData.cart;
		productDtos.add(productDto);
		return new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.OK);
		
 	}
	
	@GetMapping("/show-cart")
	public ResponseEntity<List<ProductDto>> getCart(){
		List<ProductDto> productDtos = GlobalData.cart;
		return new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<UserDto> getUser(){
		Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
		 username = ((UserDetails)principal).getUsername();
		
		} else {
		 username = principal. toString();
	
		}
		
		User user = this.userRepo.findByUserEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", 0));
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class	);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.OK);
	}
	
}
