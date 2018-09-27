package com.umutcanbolat.todoapp.repo;

import org.springframework.data.repository.CrudRepository;

import com.umutcanbolat.todoapp.model.TodoItem;

public interface TodoItemDAO extends CrudRepository<TodoItem, Integer>{

}
