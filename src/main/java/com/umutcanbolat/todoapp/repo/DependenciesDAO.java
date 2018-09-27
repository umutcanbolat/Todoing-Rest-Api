package com.umutcanbolat.todoapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.umutcanbolat.todoapp.model.Dependencies;

public interface DependenciesDAO extends CrudRepository<Dependencies, Integer>{
	Iterable<Dependencies> findAllByTodoItem(int todoItem);
	Iterable<Dependencies> findAllByDependentTo(int dependentTo);
	@Query("from Dependencies where todoItem=?1 and dependentTo=?2")
	List<Dependencies> findByTodoItemAndDependentTo(int todoItem, int dependentTo);

}
