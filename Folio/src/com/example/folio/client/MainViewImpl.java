package com.example.folio.client;

import java.util.logging.Logger;

import com.example.folio.client.project.ProjectPage;
import com.example.folio.client.util.Dimension;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A placeholder for currently selected view. Also contains the main menu.
 */
public class MainViewImpl implements MainView {

	private static Logger LOGGER = Logger.getLogger(MainViewImpl.class.getName());
	
	/**
	 * Container for currently selected view.
	 */
	private VerticalPanel root;
	
	private MainMenuPanel mainMenu;
	
	private PageResizeHandler resizeHandler;
	
	/**
	 * Current page. Won't be necessary when KeyDownHandler is handled by EventBus. TODO
	 */
	private Page page;
	
	public MainViewImpl() {
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

		// TODO Refactor the following by using EventBus
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
		}, KeyDownEvent.getType());
	}

	@Override
	public void setWidget(IsWidget widget) {
		root.clear();
		page = (Page) widget;
		if (page == null) {
			return;
		}

		mainMenu.setPage(page);	
		
		resizeHandler.setPage(page);
		root.add(page.asWidget());	
		root.setHeight(page.calcHeight(new Dimension(
				Window.getClientWidth(), Window.getClientHeight())) + "px");
		page.redraw();
	}

	@Override
	public Widget asWidget() {
		return root;
	}
	
}
