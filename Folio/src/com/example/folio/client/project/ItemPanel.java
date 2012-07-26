package com.example.folio.client.project;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

public class ItemPanel extends AbsolutePanel implements HasMouseMoveHandlers, HasClickHandlers {
	
	private static Logger LOGGER = Logger.getLogger(ItemPanel.class.getName());
	
	private ProjectPageItem projectItem;
	private Image image;
	
	public ItemPanel(ProjectPageItem projectItem) {
		this.projectItem = projectItem;
		
		this.addMouseMoveHandler(new MouseMoveHandler() {			
			@Override public void onMouseMove(MouseMoveEvent event) {
				Element element = event.getRelativeElement();
				int width = element.getClientWidth();
				int height = element.getClientHeight();
				int x = event.getRelativeX(element);
				int y = event.getRelativeY(element);
				if (isLeftArea(x, y)) {
					setArrow("arrows/arrow_left.png", 100, height / 2);
				} else if (isRightArea(x, y)) {
					setArrow("arrows/arrow_right.png", width - 100, height / 2);
				} else if (isTopArea(x, y)) {
					setArrow("arrows/arrow_top.png", width / 2, 100);
				} else if (isBottomArea(x, y)) {
					setArrow("arrows/arrow_bottom.png", width / 2, height - 100);					
				} else {					
//					DOM.setStyleAttribute(ItemPanel.this.getElement(), "cursor", "default");
					if (image != null) {
						ItemPanel.this.remove(image);
					}
				}
//				LOGGER.fine("MouseMove: x = " + x + ", y = " + y);
			}
		});
		this.addClickHandler(new ClickHandler() {			
			@Override public void onClick(ClickEvent event) {
				Element element = event.getRelativeElement();
				int x = event.getRelativeX(element);
				int y = event.getRelativeY(element);
				LOGGER.fine("MouseClick: x = " + x + ", y = " + y);
				if (isLeftArea(x, y)) {
					onLeftClicked(); 
				} else if (isRightArea(x, y)) {
					onRightClicked();
				} else if (isTopArea(x, y)) {
					onUpClicked(); 
				} else if (isBottomArea(x, y)) {
					onDownClicked();
				} else {
					DOM.setStyleAttribute(ItemPanel.this.getElement(), "cursor", "default");
				}
			}
		});
	}
	
	private void setArrow(String url, int x, int y) {
//		DOM.setStyleAttribute(ItemPanel.this.getElement(), "cursor", "none"); 
		if (image != null) {
			ItemPanel.this.remove(image);
		}
		image = new Image(url);
		ItemPanel.this.add(image);
		ItemPanel.this.setWidgetPosition(image, x, y);
	}
	
	private boolean isLeftArea(int x, int y) {
		int width = getOffsetWidth();
		return x < width / 5;
	}

	private boolean isRightArea(int x, int y) {
		int width = getOffsetWidth();
		return x > 4* (width / 5);
	}
	
	private boolean isTopArea(int x, int y) {
		int height = getOffsetHeight();
		return y < height / 4;
	}
	
	private boolean isBottomArea(int x, int y) {
		int height = getOffsetHeight();
		return y > 3* (height / 4);
	}
	
	private void onUpClicked() {
		projectItem.onUpClicked();
	}
	
	private void onDownClicked() {
		projectItem.onDownClicked();
	}
	
	private void onLeftClicked() {
		projectItem.onLeftClicked();
	}
	
	private void onRightClicked() {
		projectItem.onRightClicked();
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}


	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}
}
