package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projection implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private ArrayList<String> actors;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private String director;
	
	@Column(nullable = false)
	private double length;
	
	@Column(nullable = false)
	private String poster;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private ArrayList<Hall> halls;
	
	@Column(nullable = false)
	private ArrayList<String> time;
	
	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private Long cinthe_id;
	
	@Column(nullable = true)
	private HashMap<String, Integer> ratings;
	
	private double rating;
	
	public Projection() {
		super();
	}

	public Projection(Long id, String name, ArrayList<String> actors, String genre, String director, double length,
			String poster, String description, ArrayList<Hall> halls, ArrayList<String> time, double price) {
		super();
		this.id = id;
		this.name = name;
		this.actors = actors;
		this.genre = genre;
		this.director = director;
		this.length = length;
		this.poster = poster;
		this.description = description;
		this.halls = halls;
		this.time = time;
		this.price = price;
		this.ratings = new HashMap<String, Integer>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getActors() {
		return actors;
	}

	public void setActors(ArrayList<String> actors) {
		this.actors = actors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Hall> getHalls() {
		return halls;
	}

	public void setHalls(ArrayList<Hall> halls) {
		this.halls = halls;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getCinthe_id() {
		return cinthe_id;
	}

	public void setCinthe_id(Long cinthe_id) {
		this.cinthe_id = cinthe_id;
	}

	public HashMap<String, Integer> getRatings() {
		return ratings;
	}

	public void setRatings(HashMap<String, Integer> ratings) {
		this.ratings = ratings;
	}
	
	
	public void setRating(double rating) {
		this.rating = rating;
	}

	public double getRating(){
		if (this.ratings.size() == 0){
			return 0.0;
		}
		double rating = 0.0;
		for (Entry<String, Integer> entry : this.ratings.entrySet()){
			rating += entry.getValue();
		}
		rating = rating / this.ratings.size();
		return rating;
	}
	
	
}
