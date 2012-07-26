package com.example.folio.client.project;

import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Utils;
import com.google.gwt.user.client.ui.Image;

public class ProjectPageItem {

	private ProjectPage page;
	
	private String imgUrl;
	private String itemName;
	
	private Image image;	
	private ItemPanel panel;

	public ProjectPageItem(String imgUrl, String itemName) {
		this.imgUrl = imgUrl;
		this.itemName = itemName;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	private Image getImage() {
		if (image == null) {
			image = new Image(getImgUrl());
		}
		return image;
	}

	public ItemPanel getPanel() {
		if (panel == null) {
			panel = new ItemPanel(this);
			panel.setStyleName("project_item");
			panel.add(getImage());
		}
		return panel;
	}
	
	public void scale(Dimension windowSize) {
		Utils.scaleImage(getPanel(), getImage(), windowSize);
		getPanel().setHeight(windowSize.height + "px");
		getPanel().setWidth(windowSize.width + "px");
	}
	
	protected void onUpClicked() {
		getPage().swipeBack();
	}
	
	protected void onDownClicked() {
		getPage().swipeForward();
	}
	
	protected void onLeftClicked() {
		getPage().swipeLeft();
	}
	
	protected void onRightClicked() {
		getPage().swipeRight();
	}

	public ProjectPage getPage() {
		return page;
	}

	public void setPage(ProjectPage page) {
		this.page = page;
	}
	
	
}
