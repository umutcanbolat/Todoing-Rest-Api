package com.umutcanbolat.todoapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.umutcanbolat.todoapp.model.TodoList;
import com.umutcanbolat.todoapp.repo.TodoListDAO;

public class TodoListControllerTest {
	
	@InjectMocks
	TodoListController todoListController;
	
	@Mock
	TodoListDAO todoListDao;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTodoListById() {
		TodoList tl = new TodoList();
		tl.setListId(1);
		tl.setListName("todo list 1");
		
		Optional<TodoList> todoList = Optional.of(tl);
		
		Mockito.when(todoListDao.findById(Mockito.anyInt())).thenReturn(todoList);
		
		TodoList res = todoListController.getTodoListById(1);
		
		assertNotNull(res);
		assertEquals(tl.getListName(), res.getListName());
		assertEquals(tl.getListId(), res.getListId());
	}

}
