package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
 * Service classes are used here to implement additional business logic/validation
 * This class has to be annotated with @Service annotation.
 * @Service - It is a specialization of the component annotation. It doesn't currently
 * provide any additional behavior over the @Component annotation, but it's a good idea
 * to use @Service over @Component in service-layer classes because it specifies intent
 * better. Additionally, tool support and additional behavior might rely on it in the
 * future.
 * */
@Service
public class CategoryServiceImpl implements CategoryService {

	/*
	 * Autowiring should be implemented for the CategoryRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/*
	 * This method should be used to save a new category.Call the corresponding
	 * method of Respository interface.
	 */
	public Category createCategory(Category category) throws CategoryNotCreatedException {
		Optional<Category> optional = categoryRepository.findById(category.getId());
		if (optional.isPresent()) {
			throw new CategoryNotCreatedException("Category Already Exists");
		}
		Category createdCategory = categoryRepository.insert(category);
		if(createdCategory == null){
			throw new CategoryNotCreatedException("Category Not Created");
		}
		return createdCategory;
	}

	/*
	 * This method should be used to delete an existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException {
		Optional<Category> optional = categoryRepository.findById(categoryId);
		if (optional.isPresent()) {
			categoryRepository.deleteById(categoryId);
			return true;
		}else{
			throw new CategoryDoesNoteExistsException("Category Does Not Exist");
		}
	}

	/*
	 * This method should be used to update a existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public Category updateCategory(Category category, String categoryId) {
		if(!categoryRepository.findById(categoryId).isPresent()){
			return null;
		}
		categoryRepository.save(category);
		return category;
	}

	/*
	 * This method should be used to get a category by categoryId.Call the
	 * corresponding method of Respository interface.
	 */
	public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
//		Optional<Category> optional = categoryRepository.findById(categoryId);
//		if (optional.isPresent()) {
//			return optional.get();
//		}else{
//			throw new CategoryNotFoundException("Category Not Found");
//		}

		Category category;
		try {
			category = categoryRepository.findById(categoryId).get();
		} catch (NoSuchElementException e) {
			throw new CategoryNotFoundException("Category not found");
		}
		return category;
	}

	/*
	 * This method should be used to get a category by userId.Call the corresponding
	 * method of Respository interface.
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		return categoryRepository.findAllCategoryByCategoryCreatedBy(userId);
	}
}
