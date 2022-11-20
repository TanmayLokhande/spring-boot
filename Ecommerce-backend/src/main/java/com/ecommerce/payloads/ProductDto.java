package com.ecommerce.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private int productId;
	private String productName;
	private String productPrice;
	private CategoryDto category;
	private String productImage;
	
}
