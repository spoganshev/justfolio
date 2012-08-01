package com.example.folio.client.cover;

import java.util.Map;

import com.example.folio.client.Folio;
import com.example.folio.client.Page;
import com.example.folio.client.TestData;
import com.example.folio.client.cover.grid.CellPanel;
import com.example.folio.client.cover.grid.CoverBlock;
import com.example.folio.client.cover.grid.CoverGrid;
import com.example.folio.client.cover.grid.CoverMetric;
import com.example.folio.client.cover.grid.Transformator;
import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Rectangle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CoverPage extends Page {
	
	public final static int TOP_HEIGHT = 185;
	public final static int BASEMENT_HEIGHT = 185;

	private Folio folio;
	
	private DockLayoutPanel coverPage;
	private LayoutPanel gridContainer;
	private CoverTop topPanel;	
	private ListBox comboGrids;
	private int currentCountOfColumns = 0;
	private CoverGrid selectedGrid;
	
	private Map<String, CoverGrid> grids = TestData.getCoverGrids();
	
	public CoverPage(Folio folio) {
		this.folio = folio;
		coverPage = new DockLayoutPanel(Unit.PX);
		coverPage.setStyleName("container");
		gridContainer = new LayoutPanel();
		topPanel = new CoverTop();
		coverPage.addNorth(topPanel, TOP_HEIGHT);
		coverPage.addSouth(new CoverBasement(), BASEMENT_HEIGHT);
		coverPage.add(gridContainer);
		
		
		comboGrids = new ListBox();
		comboGrids.setStyleName("combo");
		comboGrids.addItem("", "");
		for (String name : grids.keySet()) {
			comboGrids.addItem(name, name);			
		}
		comboGrids.addChangeHandler(new ChangeHandler() {			
			@Override public void onChange(ChangeEvent event) {
				gridSelectionChanged();
			}
		});		
		topPanel.setCombo(comboGrids);
	}

	@Override
	public Widget asWidget() {
		return coverPage;
	}	

	@Override
	public int calcHeight(Dimension windowSize) {
		return windowSize.height;
	}

	@Override
	public void redraw(Dimension windowSize) {
		if (selectedGrid != null) {
			int countOfCoumns = CoverMetric.calcNumberOfColumns(Window.getClientWidth());
			if (countOfCoumns < currentCountOfColumns) {
				// it becomes smaller
				currentCountOfColumns = countOfCoumns;
				CoverGrid newGrid = 
					(new Transformator()).calcNewGrid(selectedGrid, currentCountOfColumns, folio);
				redrawGrid(newGrid);
			} else if (countOfCoumns > currentCountOfColumns) {
				// it becomes bigger
				currentCountOfColumns = countOfCoumns;
				CoverGrid newGrid =
					(new Transformator()).calcNewGrid(selectedGrid, currentCountOfColumns, folio);
				redrawGrid(newGrid);
			}
		}
	}
	
	@Override
	public void redraw() {
		Window.enableScrolling(true);
		super.redraw();
	}
	
	private void redrawGrid(CoverGrid grid) {
		String width = grid.calcWidth() + "px";
		gridContainer.setWidth(width);
		coverPage.setWidth(width);	
		int height = grid.calcHeight();
		coverPage.setHeight(CoverPage.TOP_HEIGHT + height + CoverPage.BASEMENT_HEIGHT + "px");
		gridContainer.setHeight(height + "px");
		for (CoverBlock coverBlock : grid.getBlocks()) {
	        CellPanel panel = coverBlock.getPanel(gridContainer);

			Rectangle rect = coverBlock.calcRectangle();
		    gridContainer.setWidgetLeftWidth(panel, rect.x, Unit.PX, rect.width, Unit.PX);
		    gridContainer.setWidgetTopHeight(panel, rect.y, Unit.PX, rect.height, Unit.PX);
		    panel.redraw();
		    gridContainer.animate(480);
		}
	}
	
	private void gridSelectionChanged() {
		int selectedIdx = comboGrids.getSelectedIndex();
		String value = comboGrids.getValue(selectedIdx);
		gridContainer.clear();
		if (value.length() > 0) {
			selectedGrid = grids.get(value);
			currentCountOfColumns = CoverMetric.calcNumberOfColumns(Window.getClientWidth());					
			selectedGrid = (new Transformator()).calcNewGrid(selectedGrid, currentCountOfColumns, folio);
			redrawGrid(selectedGrid);					
		} else {
			coverPage.setHeight("0px");
			gridContainer.setHeight("0px");
		}
	}


}
