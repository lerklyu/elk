package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
@Tag(name = "Задачи", description = "Управление задачами")
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    @Operation(summary = "Получить все задачи")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить задачу по ID")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Получить задачи по ID категории")
    public ResponseEntity<List<Task>> getTasksByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(taskService.getTasksByCategoryId(categoryId));
    }

    @PostMapping
    @Operation(summary = "Создать задачу")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO dto) {
        return new ResponseEntity<>(taskService.createTask(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить задачу по ID задачи")
    public ResponseEntity<Task> updateTask(@Valid @RequestBody TaskDTO dto, @PathVariable Long id) {
        return ResponseEntity.ok(taskService.updateTask(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить задачу по ID")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
