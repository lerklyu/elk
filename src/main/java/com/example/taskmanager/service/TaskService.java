package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final CategoryService categoryService;

    private final UserService userService;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с ID: " + id + " не найдёна"));
    }

    public Task createTask(TaskDTO dto) {
        return taskRepository.save(Task.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .category(categoryService.getCategoryById(dto.getCategoryId()))
                .user(userService.getUserById(dto.getUserId()))
                .build());
    }

    public Task updateTask(TaskDTO dto, Long id) {
        Task existingTask = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с ID: " + id + " не найдёна"));
        existingTask.setName(dto.getName());
        existingTask.setDescription(dto.getDescription());
        existingTask.setCompleted(dto.isCompleted());
        existingTask.setCategory(
                categoryService.getCategoryById(dto.getCategoryId()));
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task taskForDelete = taskRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с ID: " + id + " не найдёна"));
        taskRepository.delete(taskForDelete);
    }

    public List<Task> getTasksByCategoryId(Long categoryId) {
        categoryService.getCategoryById(categoryId);
        return taskRepository.findByCategory_Id(categoryId);
    }
}
