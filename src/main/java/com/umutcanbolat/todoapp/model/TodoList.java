package com.umutcanbolat.todoapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TodoList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int listId;
	@Column(nullable = false)
	private String listName;
	@OneToMany(mappedBy = "list")
	private List<TodoItem> items;

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "TodoList [listId=" + listId + ", listName=" + listName + "]";
	}

}
