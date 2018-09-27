package com.umutcanbolat.todoapp.repo;

import org.springframework.data.repository.CrudRepository;

import com.umutcanbolat.todoapp.model.TodoList;

public interface TodoListDAO extends CrudRepository<TodoList, Integer>{

}
