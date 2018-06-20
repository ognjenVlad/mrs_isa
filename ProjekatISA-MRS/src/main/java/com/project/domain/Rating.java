package com.project.domain;

public class Rating {

	private Integer ones;
	private Integer twos;
	private Integer threes;
	private Integer fours;
	private Integer fives;
	
	public Rating(Integer ones, Integer twos, Integer threes, Integer fours, Integer fives) {
		super();
		this.ones = ones;
		this.twos = twos;
		this.threes = threes;
		this.fours = fours;
		this.fives = fives;
	}
	
	public Rating() {
		super();
	}

	public Integer getOnes() {
		return ones;
	}

	public void setOnes(Integer ones) {
		this.ones = ones;
	}

	public Integer getTwos() {
		return twos;
	}

	public void setTwos(Integer twos) {
		this.twos = twos;
	}

	public Integer getThrees() {
		return threes;
	}

	public void setThrees(Integer threes) {
		this.threes = threes;
	}

	public Integer getFours() {
		return fours;
	}

	public void setFours(Integer fours) {
		this.fours = fours;
	}

	public Integer getFives() {
		return fives;
	}

	public void setFives(Integer fives) {
		this.fives = fives;
	}
	
	public Integer getTotalVotes(){
		return this.ones + this.twos + this.threes + this.fours + this.fives;
	}
	
	public Double getRating(){
		int total = getTotalVotes();
		double rating = 5 * this.fives + 4 * this.fours + 3 * this.threes + 2 * this.twos + this.ones;
		rating = rating / total;
		return rating;
	}
	
	
}
