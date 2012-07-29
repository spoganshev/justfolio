package com.example.folio.shared.model;

public class ImageItem extends Item {
	
	private Image image;
	
	private Boolean stretched;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Boolean getStretched() {
		return stretched;
	}

	public void setStretched(Boolean stretched) {
		this.stretched = stretched;
	}
	
}
