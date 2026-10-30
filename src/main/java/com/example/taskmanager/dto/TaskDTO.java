package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {

    @NotBlank(message = "Название задачи не может быть пустым")
    private String name;

    private String description;
    private boolean completed;

    @NotNull(message = "ID категории не может быть пустым")
    private Long categoryId;

    @NotNull(message = "ID пользователя не может быть пустым")
    private Long userId;
}
