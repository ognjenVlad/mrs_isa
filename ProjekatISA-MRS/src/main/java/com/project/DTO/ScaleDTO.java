package com.project.DTO;

public class ScaleDTO {
	private int bronze_limit;
	private int silver_limit;
	private int gold_limit;
	
	public ScaleDTO() {
		
	}
	
	public ScaleDTO(int bronze_limit, int silver_limit, int gold_limit) {
		super();
		this.bronze_limit = bronze_limit;
		this.silver_limit = silver_limit;
		this.gold_limit = gold_limit;
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
