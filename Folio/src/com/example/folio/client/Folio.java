package com.example.folio.client;


import java.util.logging.Logger;

import com.example.folio.client.cover.CoverPage;
import com.example.folio.client.project.ProjectPage;
import com.example.folio.client.util.Dimension;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Folio implements EntryPoint {

	private static Logger LOGGER = Logger.getLogger(Folio.class.getName());
	
	private VerticalPanel root;
	private Page page;
	private PageResizeHandler resizeHandler;

	private MainMenuPanel mainMenu;
	private Page coverPage;
	
	public void onModuleLoad() {	

		for (int i = 1; i < 9; i++) {
			String name = "block" + i + ".jpg";
			Image.prefetch(name);
		}
		
		final FlowPanel menu = new FlowPanel();
		menu.setStyleName("menu");
		mainMenu = new MainMenuPanel();
		menu.add(mainMenu);
		RootPanel.get("menu").add(menu);
		
		root = new VerticalPanel();
		root.setWidth("100%");

		RootPanel.get("root").add(root);
		
		resizeHandler = new PageResizeHandler(root);		
		Window.addResizeHandler(resizeHandler);
		
		
		 RootPanel.get().addDomHandler(new KeyDownHandler() {			
				@Override
				public void onKeyDown(KeyDownEvent event) {
					LOGGER.fine("KeyDownEvent " + event.getNativeKeyCode());
					if (page instanceof ProjectPage) {
						ProjectPage projectPage = (ProjectPage) page; 
						int keyCode = event.getNativeKeyCode();
						if (keyCode == 40) {
							projectPage.swipeForward();
						} else if (keyCode == 38) {
							projectPage.swipeBack();
						} else if (keyCode == 39) {
							projectPage.swipeRight();
						} else if (keyCode == 37) {
							projectPage.swipeLeft();
						}
					}
				}
			} , 
		    KeyDownEvent.getType()
		);
		
	    History.addValueChangeHandler(new ValueChangeHandler<String>() {
	        public void onValueChange(ValueChangeEvent<String> event) {
	          String historyToken = event.getValue();
	          if ("".equals(historyToken)) {
	        	  openPage(getCoverPage());
	          } else if (historyToken.startsWith("project")) {
//	        	  int id = Integer.parseInt(historyToken.substring(7));
	        	  openPage(new ProjectPage());
	          }
	          LOGGER.info(historyToken);
	        }
	      });
	    
	    History.fireCurrentHistoryState();

	}
	
	public void openPage(Page newPage) {
		if (page != null && root.getWidgetIndex(page.getPagePanel()) != -1) {
			root.remove(page.getPagePanel());
		}
		page = newPage;

		mainMenu.setPage(page);	
		
		resizeHandler.setPage(page);
		root.add(page.getPagePanel());	
		root.setHeight(page.calcHeight(new Dimension(Window.getClientWidth(), Window.getClientHeight())) + "px");
		page.redraw();
		String historyItem = page.getHistoryToken();
		if (historyItem != null) {
			History.newItem(historyItem, false);
		}
	}	
	

	public Page getCoverPage() {
		if (coverPage == null) {
			coverPage = new CoverPage(this);
		}
		return coverPage;
	}
	
}
