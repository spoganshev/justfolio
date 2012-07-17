package com.example.folio.client.cover;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;

public class CoverBasement extends DockLayoutPanel {

	public CoverBasement() {
		super(Unit.PX);
		setStyleName("basement");
		addWest(new HTML("&copy; Justfolio 2012"), 200);
	}

}
