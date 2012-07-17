package com.example.folio.client.component;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Anchor;

public class CustomAnchor extends Anchor {

	public CustomAnchor(String text) {
		super(text);
		setStyleName("anchor");
		
		addMouseOverHandler(new MouseOverHandler() {			
			@Override
			public void onMouseOver(MouseOverEvent arg0) {
				addStyleName("anchor_over");				
			}
		});
		addMouseOutHandler(new MouseOutHandler() {			
			@Override
			public void onMouseOut(MouseOutEvent arg0) {
				removeStyleName("anchor_over");
			}
		});
	}
	
}
