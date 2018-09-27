package com.umutcanbolat.todoapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dependencies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@ManyToOne
//	@JoinColumn(name = "itemId")
//	@JsonIgnore
	private int todoItem;

	@Column(nullable = false)
	private int dependentTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTodoItem() {
		return todoItem;
	}

	public void setTodoItem(int todoItem) {
		this.todoItem = todoItem;
	}

	public int getDependentTo() {
		return dependentTo;
	}

	public void setDependentTo(int dependentTo) {
		this.dependentTo = dependentTo;
	}

	@Override
	public String toString() {
		return "Dependencies [id=" + id + ", todoItem=" + todoItem + ", dependentTo=" + dependentTo + "]";
	}

	



}
