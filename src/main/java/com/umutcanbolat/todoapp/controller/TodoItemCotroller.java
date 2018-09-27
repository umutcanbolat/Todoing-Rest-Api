package com.umutcanbolat.todoapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.umutcanbolat.todoapp.model.TodoItem;
import com.umutcanbolat.todoapp.repo.TodoItemDAO;

@RestController
public class TodoItemCotroller {
	@Autowired
	TodoItemDAO itemDao;
	@Autowired
	DependenciesController depCtl;
	
	@RequestMapping("/addItem")
	public String addItem(TodoItem item) {
		try {
			if (item != null) {
				if (item.getItemName() != null && !item.getItemName().isEmpty()) {
					if (item.getList() != 0) {
						item.setCreteDate(new Date());
						itemDao.save(item);
						return "added " + item.toString();
					}
				}
				
			}
			return "itemName and/or list are not provided";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "err";
		}
	}
	
	@RequestMapping("/deleteItemById")
	public String deleteItemById(@RequestParam int itemId) {
		
		try {
			depCtl.deleteAllDependenciesByTodoItem(itemId);
			itemDao.deleteById(itemId);
			return "deleted " + itemId;
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		
	}
	
	@RequestMapping("/getItemById")
	public String getItemById(@RequestParam int itemId) {
		TodoItem it = itemDao.findById(itemId).orElse(null);
		if(it!=null) {
			try {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				return ow.writeValueAsString(it);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else {
			return "no item found with id " + itemId;
		}
	}
	
	@RequestMapping("/getAllItems")
	public String getAllItems() {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			return ow.writeValueAsString(itemDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		//return itemDao.findAll().toString();
	}
	
}
