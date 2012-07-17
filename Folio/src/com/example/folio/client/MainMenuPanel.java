package com.example.folio.client;

import java.util.logging.Logger;

import com.example.folio.client.component.CustomAnchor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;

public class MainMenuPanel extends DockLayoutPanel {

	private static Logger LOGGER = Logger.getLogger(MainMenuPanel.class.getName());
	
//	private Page page;
//	private LayoutPanel arrowsPanel;
//	private CustomAnchor nextItemButton;
//	private CustomAnchor prevItemButton;
//	private HandlerRegistration nextItemHandlerReg;
//	private HandlerRegistration prevItemHandlerReg;
	
	public MainMenuPanel() {
		super(Unit.PX);
		setStyleName("main_menu");

//		arrowsPanel = new LayoutPanel();
//		arrowsPanel.setHeight("50px");
//		nextItemButton = new CustomAnchor(">>>");
//		prevItemButton = new CustomAnchor("<<<");
//		arrowsPanel.add(nextItemButton);
//		arrowsPanel.add(prevItemButton);
//		
//		arrowsPanel.setWidgetLeftWidth(prevItemButton, 50, Unit.PX, 50, Unit.PX);
//		arrowsPanel.setWidgetLeftWidth(nextItemButton, 100, Unit.PX, 50, Unit.PX);
//		
//		addWest(arrowsPanel, 150);
		 
		LayoutPanel linksPanel = new LayoutPanel();
		linksPanel.setHeight("50px");
		CustomAnchor anchorAllProjects = new CustomAnchor("Project");
		CustomAnchor anchorIdentity = new CustomAnchor("Profile");
		CustomAnchor anchorDigital = new CustomAnchor("Contact");
		CustomAnchor anchorPrint = new CustomAnchor("Blog");
		
		linksPanel.add(anchorAllProjects);
		linksPanel.add(anchorIdentity);
		linksPanel.add(anchorDigital);
		linksPanel.add(anchorPrint);
		
		linksPanel.setWidgetLeftWidth(anchorAllProjects, 0, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorIdentity, 100, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorDigital, 200, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorPrint, 300, Unit.PX, 100, Unit.PX);
		
		addEast(linksPanel, 400);
	}


	public void setPage(Page page) {
//		this.page = page;
//		if (page instanceof CoverPage) {
//			arrowsPanel.setVisible(false);
//			if (nextItemHandlerReg != null) {
//				nextItemHandlerReg.removeHandler();
//			}
//			if (prevItemHandlerReg != null) {
//				prevItemHandlerReg.removeHandler();
//			}
//		} else {
//			final ProjectPage projectPage = (ProjectPage) page; 
//			arrowsPanel.setVisible(true);
//			nextItemHandlerReg = nextItemButton.addClickHandler(new ClickHandler() {				
//				@Override public void onClick(ClickEvent event) {
//					LOGGER.fine("next " + projectPage.getClass());
//					projectPage.swipeForward();
//				}
//			});
//			prevItemHandlerReg = prevItemButton.addClickHandler(new ClickHandler() {				
//				@Override public void onClick(ClickEvent event) {
//					LOGGER.fine("pref " + projectPage.getClass());
//					projectPage.swipeBack();
//				}
//			});
//		}
	}
	
	
	

}
