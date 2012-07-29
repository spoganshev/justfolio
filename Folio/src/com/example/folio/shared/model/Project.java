package com.example.folio.shared.model;

import java.util.Set;

/**
 * Project consists of a set of items (images, videos). Multiple tags can be applied to projects.
 */
public class Project {

	/**
	 * Project title is always shown over every item of the project
	 */
	private String title;
	
	/**
	 * Project description can be expanded on every item, hidden by default
	 */
	private String description;
	
	/**
	 * Project items (images, videos).
	 */
	private Set<Item> items;
	
	/**
	 * Tags that describe the project.
	 */
	private Set<Tag> tags;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
