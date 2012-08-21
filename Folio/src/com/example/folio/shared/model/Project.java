package com.example.folio.shared.model;

import java.util.List;
import java.util.Set;

/**
 * Project consists of a set of items (images, videos). Multiple tags can be applied to projects.
 */
public class Project {

	/**
	 * URL-friendly unique project name, generated from the title.
	 */
	private String name;
	
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
	private List<Item> items;
	
	/**
	 * Tags that describe the project.
	 */
	private Set<Tag> tags;
	
	public Project() {
	}

	public Project(String title) {
		setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		setName(title.replaceAll(" ", "").toLowerCase());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getItemIndex(String itemName) {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getName().equals(itemName)) {
				return i;
			}
		}
		return -1;
	}
	
}
