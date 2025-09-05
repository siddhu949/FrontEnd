package com.todo.model;

import java.time.LocalDateTime;

public class Todo {

    private int todoId;
    private String todoTitle;
    private String todoDesc;
    private LocalDateTime targetDatetime;

    // Status fields
    private String todoStatusCode;   // 'P', 'I', 'C'
    private String todoStatusName;   // "Pending", "In Progress", "Completed"

    // User reference
    private int userId;              // FK to users table
    private String createdBy;        // username (for display)
    private String modifiedBy;

    // Audit fields
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    // --- Getters & Setters ---
    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDesc() {
        return todoDesc;
    }

    public void setTodoDesc(String todoDesc) {
        this.todoDesc = todoDesc;
    }

    public LocalDateTime getTargetDatetime() {
        return targetDatetime;
    }

    public void setTargetDatetime(LocalDateTime targetDatetime) {
        this.targetDatetime = targetDatetime;
    }

    public String getTodoStatusCode() {
        return todoStatusCode;
    }

    public void setTodoStatusCode(String todoStatusCode) {
        this.todoStatusCode = todoStatusCode;
    }

    public String getTodoStatusName() {
        return todoStatusName;
    }

    public void setTodoStatusName(String todoStatusName) {
        this.todoStatusName = todoStatusName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
