package com.umutcanbolat.todoapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.umutcanbolat.todoapp.model.Dependencies;
import com.umutcanbolat.todoapp.model.TodoItem;
import com.umutcanbolat.todoapp.repo.TodoItemDAO;

public class TodoItemCotrollerTest {

	@InjectMocks
	TodoItemController todoItemController;

	@Mock
	TodoItemDAO todoItemDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testChangeItemStatusById() {

		// Create a todo item with no dependencies
		// and status = false (Not completed)
		TodoItem ti = new TodoItem();
		ti.setItemName("item 1");
		ti.setItemDesc("desc");
		ti.setList(1);
		ti.setItemId(6);
		ti.setDependencies(new ArrayList<Dependencies>());
		ti.setStatus(false);
		Optional<TodoItem> todoItem = Optional.of(ti);

		Mockito.when(todoItemDao.findById(6)).thenReturn(todoItem);

		TodoItem res = todoItemController.changeItemStatusById(6);

		assertNotNull(res);
		// Status must be true after changing it
		assertEquals(true, res.isStatus());

		// Create a new dependency. item 6 is dependent to item 18
		Dependencies dep = new Dependencies();
		dep.setDependentTo(18);
		dep.setId(50);
		dep.setTodoItem(6);

		List<Dependencies> depList = new ArrayList<Dependencies>();
		depList.add(dep);

		// Set this dependency to item with id 6
		ti.setDependencies(depList);

		// Create an item which is dependent by the item id 6
		TodoItem dependentItem = new TodoItem();
		dependentItem.setItemName("dependent item");
		dependentItem.setList(1);
		dependentItem.setItemId(18);
		dependentItem.setDependencies(new ArrayList<Dependencies>());
		// Set dependent item's status to false. Which is not completed
		dependentItem.setStatus(false);

		Optional<TodoItem> dependentItemOpt = Optional.of(dependentItem);

		Mockito.when(todoItemDao.findById(18)).thenReturn(dependentItemOpt);

		res = todoItemController.changeItemStatusById(6);
		assertNotNull(res);

		// Result must be false. The status shouldn't be changed because there is a
		// dependent item whose status is false as well
		assertEquals(false, res.isStatus());
	}

	@Test
	public void testGetItemById() throws JsonProcessingException {
		// Create a todo item
		TodoItem ti = new TodoItem();
		ti.setItemName("item 1");
		ti.setItemDesc("desc");
		ti.setList(1);
		ti.setItemId(6);
		ti.setDependencies(new ArrayList<Dependencies>());
		ti.setStatus(false);
		Optional<TodoItem> todoItem = Optional.of(ti);

		Mockito.when(todoItemDao.findById(Mockito.anyInt())).thenReturn(todoItem);

		TodoItem res = todoItemController.getItemById(6);

		assertNotNull(res);
		assertEquals(ti.getItemName(), res.getItemName());
		assertEquals(ti.getItemDesc(), res.getItemDesc());
		assertEquals(ti.getList(), res.getList());
		assertEquals(ti.isStatus(), res.isStatus());
	}

}
