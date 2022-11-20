package com.ecommerce.Global;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.Entities.Product;
import com.ecommerce.payloads.ProductDto;

public class GlobalData {

	public static List<ProductDto> cart;
	
	static {
		cart = new ArrayList<ProductDto>();
	}
}
