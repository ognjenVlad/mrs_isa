package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hall implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String hall_id;
	
	@Column(nullable = false)
	private int rows1;
	
	@Column(nullable = false)
	private int columns1;

	public Hall() {};
	public Hall(Long id, String hall_id, int rows, int columns) {
		super();
		this.id = id;
		this.hall_id = hall_id;
		this.rows1 = rows;
		this.columns1 = columns;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHall_id() {
		return hall_id;
	}

	public void setHall_id(String hall_id) {
		this.hall_id = hall_id;
	}

	public int getRows() {
		return rows1;
	}

	public void setRows(int rows) {
		this.rows1 = rows;
	}

	public int getColumns() {
		return columns1;
	}

	public void setColumns(int columns) {
		this.columns1 = columns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
