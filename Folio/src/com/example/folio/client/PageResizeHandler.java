package com.example.folio.client;

import com.example.folio.client.util.Dimension;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Panel;

public class PageResizeHandler implements ResizeHandler {

	private Panel rootPanel;
	private Page page;
	
	private Dimension windowSize;
	
	private Timer resizeTimer = new Timer() {
		@Override
		public void run() {
			int pageHeight = page.calcHeight(windowSize);
			rootPanel.setHeight(pageHeight + "px");
			page.redraw(windowSize);
		}
	};

	public PageResizeHandler(Panel rootPanel) {
		this.rootPanel = rootPanel;
	}

	@Override
	public void onResize(ResizeEvent event) {
		windowSize = new Dimension(event.getWidth(), event.getHeight());
		resizeTimer.cancel();
		resizeTimer.schedule(200);
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
