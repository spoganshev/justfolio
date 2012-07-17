package com.example.folio.client.cover;

import com.example.folio.client.component.CustomAnchor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;

public class CoverTop extends DockLayoutPanel {

	public CoverTop() {
		super(Unit.PX);
		setStyleName("top");
	}
	
	public void setCombo(ListBox comboGrids) {
		DockLayoutPanel comboPanel = new DockLayoutPanel(Unit.PX);
		comboPanel.addSouth(comboGrids, 130);
		addWest(comboPanel, 200);
		
		

		
		LayoutPanel linksPanel = new LayoutPanel();
		CustomAnchor anchorAllProjects = new CustomAnchor("All projects");
		CustomAnchor anchorIdentity = new CustomAnchor("Identity");
		CustomAnchor anchorDigital = new CustomAnchor("Digital");
		CustomAnchor anchorPrint = new CustomAnchor("Print");
		
		linksPanel.add(anchorAllProjects);
		linksPanel.add(anchorIdentity);
		linksPanel.add(anchorDigital);
		linksPanel.add(anchorPrint);
		
		linksPanel.setWidgetLeftWidth(anchorAllProjects, 0, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorIdentity, 100, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorDigital, 200, Unit.PX, 100, Unit.PX);
		linksPanel.setWidgetLeftWidth(anchorPrint, 300, Unit.PX, 100, Unit.PX);
		
		DockLayoutPanel linksContainer = new DockLayoutPanel(Unit.PX);
		linksContainer.addSouth(linksPanel, 30);
		
		addEast(linksContainer, 400);
	}

}
