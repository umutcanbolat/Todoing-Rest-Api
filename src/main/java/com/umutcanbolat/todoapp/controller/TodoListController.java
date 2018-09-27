package com.umutcanbolat.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.umutcanbolat.todoapp.model.TodoList;
import com.umutcanbolat.todoapp.repo.TodoListDAO;

@RestController
public class TodoListController {
	
	@Autowired
	TodoListDAO todoListDao;
	
	@RequestMapping("/addTodoList")
	public String addTodoList(TodoList todoList) {
		todoListDao.save(todoList);
		return "added " + todoList.getListName() + " " + todoList.getListId();
	}
	
	@RequestMapping("/deleteTodoListById")
	public String deleteTodoListById(@RequestParam int listId) {
		todoListDao.deleteById(listId);
		return "deleted " + listId;
	}

	@RequestMapping("/getTodoListById")
	public String getTodoListById(@RequestParam int listId) {
		TodoList l = todoListDao.findById(listId).orElse(null);
		
		if(l!=null) {
			try {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				return ow.writeValueAsString(l);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			return "no list found with id " + listId;
		}
	}
	
	@RequestMapping("/getTodoListAll")
	public String getTodoListAll() {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(todoListDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
