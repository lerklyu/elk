package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Category;
import com.example.taskmanager.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Tag(name="Категории задач", description = "Управление категориями задач")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Показать все категории")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @Operation(summary = "Сохранить категорию задачи")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category.getName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию по ID")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
