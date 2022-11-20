package com.ecommerce.Services;

import java.util.List;

import com.ecommerce.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	void deleteUser(Integer userId);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(Integer userID);
	

	
}
