package com.example.folio.shared.model;

/**
 * Foreground or background image.
 */
public class Image {

	/**
	 * URL to an image.
	 */
	private String url;
	
	public Image() {
	}

	public Image(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
}
