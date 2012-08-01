package com.example.folio.client;

import com.example.folio.client.util.Dimension;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * This is our 'View' in the MVP model.
 */
public abstract class Page implements IsWidget {

	public abstract int calcHeight(Dimension windowSize);
	
	public abstract void redraw(Dimension windowSize);
	
	public void redraw() {
		redraw(new Dimension(Window.getClientWidth(), Window.getClientHeight()));
	}
	
}
