package com.example.folio.shared.model;

import java.util.Set;

/**
 * Project consists of a set of items (images, videos). Multiple tags can be applied to projects.
 */
public class Project {

	/**
	 * Project items (images, videos).
	 */
	private Set<Item> items;
	
	/**
	 * Tags that describe the project.
	 */
	private Set<Tag> tags;
	
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
