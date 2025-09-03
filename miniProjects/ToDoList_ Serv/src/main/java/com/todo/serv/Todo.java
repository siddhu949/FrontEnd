package com.todo.serv;

import java.time.LocalDateTime;

public class Todo {
    private int todoId;
    private String todoTitle;
    private String todoDesc;
    private String todoStatusCode;
    private LocalDateTime targetDatetime;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;

    // --- Getters & Setters ---
    public int getTodoId() { return todoId; }
    public void setTodoId(int todoId) { this.todoId = todoId; }

    public String getTodoTitle() { return todoTitle; }
    public void setTodoTitle(String todoTitle) { this.todoTitle = todoTitle; }

    public String getTodoDesc() { return todoDesc; }
    public void setTodoDesc(String todoDesc) { this.todoDesc = todoDesc; }

    public String getTodoStatusCode() { return todoStatusCode; }
    public void setTodoStatusCode(String todoStatusCode) { this.todoStatusCode = todoStatusCode; }

    public LocalDateTime getTargetDatetime() { return targetDatetime; }
    public void setTargetDatetime(LocalDateTime targetDatetime) { this.targetDatetime = targetDatetime; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }
	public void setEditedAt(Object object) {
		// TODO Auto-generated method stub
		
	}
}
