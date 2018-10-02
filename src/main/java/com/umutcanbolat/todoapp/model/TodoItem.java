package com.umutcanbolat.todoapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class TodoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
//	@ManyToOne
//	@JoinColumn(name = "listId")
//	@JsonIgnore
	private int list;
	@Column(nullable = false)
	private String itemName;
	private String itemDesc;
	//true: done, false:undone
	@Column(nullable = false)
	private boolean status;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date deadline;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date createDate;
	@OneToMany(mappedBy = "todoItem")
	private List<Dependencies> dependencies;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getList() {
		return list;
	}
	public void setList(int list) {
		this.list = list;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<Dependencies> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependencies> dependencies) {
		this.dependencies = dependencies;
	}
	@Override
	public String toString() {
		return "TodoItem [itemId=" + itemId + ", list=" + list + ", itemName=" + itemName + ", itemDesc=" + itemDesc
				+ ", status=" + status + ", deadline=" + deadline + ", createDate=" + createDate + ", dependencies="
				+ dependencies + "]";
	}
}
