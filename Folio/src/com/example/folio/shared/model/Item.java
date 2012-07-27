package com.example.folio.shared.model;

/**
 * Project item. Has a title and specified what background to use.
 */
public class Item {

	/**
	 * Item title.
	 */
	private String title;
	
	/**
	 * Type of background for an item on a Project page.
	 */
	private BackgroundType backgroundType;
	
	/**
	 * Used when backgroundType == IMAGE.
	 */
	private Image backgroundImage;
	
	/**
	 * Used when backgroundType == COLOR.
	 */
	private String backgroundColor;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
}
