package com.in17.springboot.blogrestapi.post;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Post {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String title;
	private String author;
	private String dateCreated;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "TEXT")
	private String imagePath;

	public Post() {
		super();
	}

	public Post(String id, String title, String author, String dateCreated, String description, String imagePath) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.dateCreated = dateCreated;
		this.description = description;
		this.imagePath = imagePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}