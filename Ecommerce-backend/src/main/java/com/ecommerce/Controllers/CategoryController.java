package com.ecommerce.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.Services.CategoryService;
import com.ecommerce.Services.FileService;
import com.ecommerce.payloads.ApiResponse;
import com.ecommerce.payloads.CategoryDto;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catDto){
		CategoryDto createCategory = this.categoryService.createCategory(catDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer catId){
		CategoryDto cat = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ApiResponse deleteCategory(@PathVariable("catId") Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ApiResponse("This category is successfully deleted !!",true);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") Integer catId){
		CategoryDto categoryDto = this.categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategoryById(){
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.OK);
	}
	
	//postImage
	@PostMapping("/image/upload/{catId}")
	public ResponseEntity<CategoryDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable("catId") Integer catId) throws IOException{
		CategoryDto catDto = this.categoryService.getCategoryById(catId); 
		String fileName = this.fileService.uploadImage(path, image);		
		catDto.setCatImage(fileName);
		CategoryDto updateCategory = this.categoryService.updateCategory(catDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//methodToServeFile
	@GetMapping(value="/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream	 resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
}
