package com.todo.serv;

public class Todo {
private int todo_id;
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
public String getTodoTargetDate() {
	return todoTargetDate;
}
public void setTodoTargetDate(String todoTargetDate) {
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
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public String getModifiedBy() {
	return modifiedBy;
}
public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}
public String getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(String modifiedDate) {
	this.modifiedDate = modifiedDate;
}
private String todoTitle;
private String todoDescription;
private String todoStatus;
private String todoTargetDate;
private String todoTargetTime;
private String createdBy;
private String createdDate;
private String modifiedBy;
private String modifiedDate;
}
