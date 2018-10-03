package com.umutcanbolat.todoapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.umutcanbolat.todoapp.model.Dependencies;
import com.umutcanbolat.todoapp.model.TodoItem;
import com.umutcanbolat.todoapp.repo.TodoItemDAO;

@RestController
@CrossOrigin
public class TodoItemController {
	@Autowired
	TodoItemDAO itemDao;
	@Autowired
	DependenciesController depCtl;

	@PostMapping("/addItem")
	public TodoItem addItem(TodoItem item) throws IllegalStateException {
		if (item != null) {
			if (item.getItemName() != null && !item.getItemName().isEmpty()) {
				if (item.getList() != 0) {
					item.setCreateDate(new Date());
					itemDao.save(item);
					return item;
				}
			}
		}
		// return "itemName and/or list are not provided";
		throw new IllegalStateException("itemName and/or list are not provided");
	}

	@PutMapping("/changeItemStatusById/{itemId}")
	public TodoItem changeItemStatusById(@PathVariable int itemId) throws IllegalStateException {

		TodoItem it = itemDao.findById(itemId).orElse(null);
		if (it != null) {
			if (it.isStatus()) {
				it.setStatus(false);
			} else {
				for(Dependencies dep: it.getDependencies()) {
					try {
						if(!this.getItemById(dep.getDependentTo()).isStatus()) {
							return it;
						}
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				it.setStatus(true);
			}
			itemDao.save(it);
			return it;
		} else {
			throw new IllegalStateException("no item found with id " + itemId);
		}
	}

	@DeleteMapping("/deleteItemById/{itemId}")
	public String deleteItemById(@PathVariable int itemId) {
		depCtl.deleteAllDependenciesByTodoItem(itemId);
		itemDao.deleteById(itemId);
		return "deleted " + itemId;
	}

	@DeleteMapping("/deleteAllItemsByListId/{listId}")
	public String deleteAllItemsByListId(@PathVariable int listId) {
		Iterable<TodoItem> todoItems = itemDao.findAllByList(listId);
		for (TodoItem it : todoItems) {
			this.deleteItemById(it.getItemId());
		}
		itemDao.deleteAll(todoItems);
		return "deleted all items of listId: " + listId;
	}

	@RequestMapping("/getAllItemsByListId/{listId}")
	public List<TodoItem> getAllItemsByListId(@PathVariable int listId) {
		Iterable<TodoItem> todoItems = itemDao.findAllByList(listId);

		return (List<TodoItem>) todoItems;
	}

	@RequestMapping("/getItemById")
	public TodoItem getItemById(@RequestParam int itemId) throws JsonProcessingException {
		TodoItem it = itemDao.findById(itemId).orElse(null);
		if (it != null) {
			return it;
		} else {
			throw new IllegalStateException("no item found with id " + itemId);
		}
	}

	@RequestMapping("/getAllItems")
	public List<TodoItem> getAllItems() {
		return (List<TodoItem>) itemDao.findAll();
	}

}
