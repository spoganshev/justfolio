package com.example.folio.client;

import com.example.folio.client.util.Dimension;
import com.google.gwt.user.client.ui.Panel;

public abstract class Page {

	public abstract Panel getPagePanel();
	
	public abstract int calcHeight(Dimension windowSize);
	
	public abstract void redraw();
	
	public abstract void redraw(Dimension windowSize);
	
	public abstract String getHistoryToken();
	
}
