package com.ecommerce.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Entities.User;
import com.ecommerce.Repositories.UserRepo;
import com.ecommerce.Services.UserService;
import com.ecommerce.payloads.UserDto;
import com.ecommerce.Exception.*;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User userSaved = this.userRepo.save(user);
		return this.modelMapper.map(userSaved, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		user.setUsername(userDto.getUsername());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		
		User savedUser = this.userRepo.save(user);
		
		
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(Integer userID) {
		User user = this.userRepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "id", userID));
		return this.modelMapper.map(user, UserDto.class);
	}

}
