package com.example.folio.shared.model;

/**
 * Project item. Has a title and specified what background to use.
 */
public class Item {

	/**
	 * URL-friendly item name, generated from the title.
	 */
	private String name;
	
	/**
	 * Item title.
	 */
	private String title;
	
	/**
	 * Actual image.
	 */
	private Image image;
	
	/**
	 * Type of background for an item on a Project page. NO_BACKGROUND by default.
	 */
	private BackgroundType backgroundType = BackgroundType.NO_BACKGROUND;
	
	/**
	 * Used when backgroundType == IMAGE.
	 */
	private Image backgroundImage;
	
	/**
	 * Used when backgroundType == COLOR.
	 */
	private String backgroundColor;
	
	public Item() {
	}

	public Item(String title, String imageUrl) {
		setTitle(title);
		this.image = new Image(imageUrl);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		setName(title.replaceAll(" ", "").toLowerCase());
	}

	public BackgroundType getBackgroundType() {
		return backgroundType;
	}

	public void setBackgroundType(BackgroundType backgroundType) {
		this.backgroundType = backgroundType;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
}
