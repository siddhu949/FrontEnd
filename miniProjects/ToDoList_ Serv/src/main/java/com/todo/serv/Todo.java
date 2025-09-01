package com.todo.serv;

import java.sql.Date;

public class Todo {
    private int todo_id;
    private String todoTitle;
    private String todoDescription;
    private String todoStatus;
    private Date todoTargetDate;   // stays as java.sql.Date
    private String todoTargetTime;
    private String createdBy;
    private Date createdDate;      // also java.sql.Date
    private String modifiedBy;
    private Date modifiedDate;     // also java.sql.Date

    // --- Getters & Setters ---
    public int getTodo_id() {
        return todo_id;
    }
    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }
    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoDescription() {
        return todoDescription;
    }
    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public String getTodoStatus() {
        return todoStatus;
    }
    public void setTodoStatus(String todoStatus) {
        this.todoStatus = todoStatus;
    }

    public Date getTodoTargetDate() {
        return todoTargetDate;
    }
    public void setTodoTargetDate(Date todoTargetDate) {
        this.todoTargetDate = todoTargetDate;
    }

    public String getTodoTargetTime() {
        return todoTargetTime;
    }
    public void setTodoTargetTime(String todoTargetTime) {
        this.todoTargetTime = todoTargetTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
