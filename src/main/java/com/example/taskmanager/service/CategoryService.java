package com.example.taskmanager.service;

import com.example.taskmanager.entity.Category;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(String name) {
        return categoryRepository.save(new Category(name));
    }

    public Category getCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Категория с id: " + id + " не найдена"));
    }

    public void deleteCategory(Long id) {
        Category taskForDelete = categoryRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с ID: " + id + " не найдёна"));
        categoryRepository.delete(taskForDelete);
    }
}
