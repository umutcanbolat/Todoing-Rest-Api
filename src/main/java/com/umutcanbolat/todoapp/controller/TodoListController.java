package com.umutcanbolat.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umutcanbolat.todoapp.model.TodoList;
import com.umutcanbolat.todoapp.repo.TodoListDAO;

@RestController
public class TodoListController {
	
	@Autowired
	TodoListDAO todoListDao;
	@Autowired
	TodoItemCotroller itemCtl;
	
	@PostMapping("/addTodoList")
	public TodoList addTodoList(TodoList todoList) {
		if(!"".equals(todoList.getListName())) {
			todoListDao.save(todoList);
			return todoList;
		}
		throw new IllegalStateException();
	}
	
	@DeleteMapping("/deleteTodoList/{listId}")
	public String deleteTodoListById(@PathVariable int listId) {
		itemCtl.deleteAllItemsByListId(listId);
		todoListDao.deleteById(listId);
		return "deleted " + listId;
	}

	@RequestMapping("/getTodoListById")
	public TodoList getTodoListById(@RequestParam int listId) {
		TodoList l = todoListDao.findById(listId).orElse(null);
		
		if(l!=null) {
			return l;
		}else {
			//return "no list found with id " + listId;
			throw new IllegalStateException("no list found with provided id: " + listId);
		}
	}
	
	@RequestMapping("/getTodoListAll")
	public List<TodoList> getTodoListAll() {
		return (List<TodoList>) todoListDao.findAll();

	}
}
