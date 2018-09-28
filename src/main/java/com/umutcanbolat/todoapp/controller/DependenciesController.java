package com.umutcanbolat.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umutcanbolat.todoapp.model.Dependencies;
import com.umutcanbolat.todoapp.repo.DependenciesDAO;
import com.umutcanbolat.todoapp.repo.TodoItemDAO;

@RestController
public class DependenciesController {
	@Autowired
	DependenciesDAO depDao;
	@Autowired
	TodoItemDAO itemDao;

	@PostMapping("/addDependency")
	public Dependencies addDependency(Dependencies dep) {
		if(dep.getTodoItem() != dep.getDependentTo()) {
			if(depDao.findByTodoItemAndDependentTo(dep.getTodoItem(), dep.getDependentTo()).size()<1) {
				if(depDao.findByTodoItemAndDependentTo(dep.getDependentTo(), dep.getTodoItem()).size()<1) {
					if(itemDao.findById(dep.getDependentTo()).orElse(null)!=null && itemDao.findById(dep.getTodoItem()).orElse(null)!=null) {
						depDao.save(dep);
						return dep;
					}
				}
			}
		}
		throw new IllegalStateException();
		
//		System.out.println(depDao.findByTodoItemAndDependentTo(dep.getTodoItem(), dep.getDependentTo()).toString());
	}
	
	@DeleteMapping("/deleteAllDependenciesByTodoItem/{todoItem}")
	public void deleteAllDependenciesByTodoItem(@PathVariable int todoItem) {
		depDao.deleteAll(depDao.findAllByTodoItem(todoItem));
		depDao.deleteAll(depDao.findAllByDependentTo(todoItem));
	}
	
	@DeleteMapping("/deleteDependencyByTodoItem/{dep}")
	public void deleteDependencyByTodoItem(@PathVariable Dependencies dep) {
		List<Dependencies> deps = depDao.findByTodoItemAndDependentTo(dep.getTodoItem(), dep.getDependentTo());
		if(deps.size()>0) {
			depDao.deleteById(deps.get(0).getId());
		}
	}
}
