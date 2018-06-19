package com.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scale {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private int bronze_limit;

	@Column(nullable = false)
	private int silver_limit;

	@Column(nullable = false)
	private int gold_limit;
	
	public Scale() {
		
	}
	
	public Scale(long id,int bronze_limit, int silver_limit, int gold_limit) {
		super();
		this.id = id;
		this.bronze_limit = bronze_limit;
		this.silver_limit = silver_limit;
		this.gold_limit = gold_limit;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBronze_limit() {
		return bronze_limit;
	}
	public void setBronze_limit(int bronze_limit) {
		this.bronze_limit = bronze_limit;
	}
	public int getSilver_limit() {
		return silver_limit;
	}
	public void setSilver_limit(int silver_limit) {
		this.silver_limit = silver_limit;
	}
	public int getGold_limit() {
		return gold_limit;
	}
	public void setGold_limit(int gold_limit) {
		this.gold_limit = gold_limit;
	}
	
}
