package com.example.folio.client;

import com.example.folio.client.gin.FolioGinjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Folio implements EntryPoint {

	public static Folio instance; // XXX a dirty hack, we should get rid of this later
	
	// XXX make private after instance is not needed anymore
	public final FolioGinjector injector = GWT.create(FolioGinjector.class);
	
	public void onModuleLoad() {
		instance = this;
		prefetchImages();
		
		RootPanel.get().add(injector.getMainView());
		
		injector.getPlaceHistoryHandler().handleCurrentHistory();
		
	}

	private void prefetchImages() {
		for (int i = 1; i < 9; i++) {
			String name = "block" + i + ".jpg";
			Image.prefetch(name);
		}
	}
	
}
