package com.example.folio.client.project;

import com.example.folio.client.Page;
import com.example.folio.client.TestData;
import com.example.folio.client.place.ProjectPlace;
import com.example.folio.client.project.BlankAnimation.Direction;
import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Position;
import com.example.folio.shared.model.Item;
import com.example.folio.shared.model.Project;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ProjectPage extends Page {

	@Inject
	private PlaceController placeController;
	
	private LayoutPanel container;
	
	private ProjectPageItem projectItem;
	
	private int horIndex;
	private int verIndex = 0;
	
	private int totalHor;
	private int totalVer;
	
	private Project project;
	
	AbsolutePanel prevPanel;
	
	/**
	 * Without a pause Window.getClientWidth(), Window.getClientHeight() return incorrect values.
	 */
	private Timer redrawTimer = new Timer() {
		@Override
		public void run() {
			ProjectPage.super.redraw();
		}
	};
	
	public ProjectPage() {
	}

	@Override
	public Widget asWidget() {
		container = new LayoutPanel();
		container.setStyleName("project_page");
		projectItem = TestData.getRandomItem();
		Item item = project.getItems().get(horIndex);
		projectItem = new ProjectPageItem(item.getImage().getUrl(), item.getTitle());
		AbsolutePanel itemPanel = projectItem.getPanel();
		container.add(itemPanel);
		projectItem.setPage(this);
		return container;
	}
	
	@Override
	public int calcHeight(Dimension windowSize) {
		return windowSize.height;
	}

	@Override
	public void redraw(Dimension windowSize) {
		projectItem.scale(windowSize);
		AbsolutePanel itemPanel = projectItem.getPanel();
		container.setWidgetLeftWidth(itemPanel, 0, Unit.PX, windowSize.width, Unit.PX);
		container.setWidgetTopHeight(itemPanel, 0, Unit.PX, windowSize.height, Unit.PX);
		container.setHeight(windowSize.height + "px");
		container.setWidth(windowSize.width + "px");
	}
	
	@Override
	public void redraw() {
		Window.enableScrolling(false);
		redrawTimer.cancel();
		redrawTimer.schedule(200);
	}
	
	private void swipe(final Position from) {
		final int width = Window.getClientWidth();
		final int height = Window.getClientHeight();
		
		project = TestData.getSampleProjects().get(verIndex);
		totalHor = project.getItems().size();
		Item item = project.getItems().get(horIndex);
		placeController.goTo(new ProjectPlace(project.getName(), item.getName()));
		final ProjectPageItem nextItem = new ProjectPageItem(item.getImage().getUrl(),
				item.getTitle());
		final AbsolutePanel nextPanel = nextItem.getPanel();
		nextItem.scale(new Dimension(width, height));
		container.add(nextPanel);
		container.setWidgetLeftWidth(nextPanel, from.getX(), Unit.PX, width, Unit.PX);
		container.setWidgetTopHeight(nextPanel, from.getY(), Unit.PX, height, Unit.PX);
		
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {	
			@Override
			public boolean execute() {
				container.remove(prevPanel);
				return false;
			}
		}, 1100);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {			
			@Override
			public void execute() {
				prevPanel = projectItem.getPanel();
				container.setWidgetLeftWidth(prevPanel, - from.getX(), Unit.PX, width, Unit.PX);
				container.setWidgetTopHeight(prevPanel, - from.getY(), Unit.PX, height, Unit.PX);
				
				container.setWidgetLeftWidth(nextPanel, 0, Unit.PX, width, Unit.PX);
				container.setWidgetTopHeight(nextPanel, 0, Unit.PX, height, Unit.PX);
					
				container.animate(1000);
				
				projectItem = nextItem;
				projectItem.setPage(ProjectPage.this);	
				
			}
		});
	}
	
	private void blankSwipe(final Position from, Direction direction) {
		final int width = Window.getClientWidth();
		final int height = Window.getClientHeight();
		
		final AbsolutePanel blankPanel = new AbsolutePanel();
		blankPanel.setStyleName("blank_item");
		container.add(blankPanel);
		container.setWidgetLeftWidth(blankPanel, width, Unit.PX, width, Unit.PX);
		container.setWidgetTopHeight(blankPanel, 0, Unit.PX, height, Unit.PX);
		
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {	
			@Override
			public boolean execute() {
				container.remove(blankPanel);
				return false;
			}
		}, 1100);
		
		final Animation animation = new BlankAnimation(container, projectItem.getPanel(), blankPanel, width, height, direction); 
				
		Scheduler.get().scheduleDeferred(
			new ScheduledCommand() {			
				@Override
				public void execute() {
					animation.run(700);
				}
			}
		);
	}
	
	
	public void swipeForward() {
		final int height = Window.getClientHeight();
		if (verIndex < totalVer) {	
			verIndex++;
			horIndex = 0;
			swipe(new Position(0, height));	
		} else {
			blankSwipe(new Position(0, height), Direction.DOWN);
		}
	}
	
	public void swipeBack() {
		final int height = Window.getClientHeight();
		if (verIndex > 0) { 
			verIndex--;
			horIndex = 0;
			swipe(new Position(0, -height));
		} else {
			blankSwipe(new Position(0, -height), Direction.UP);
		}
	}

	
	public void swipeLeft() {
		final int width = Window.getClientWidth();
		if (horIndex > 0) {
			horIndex--;
			swipe(new Position(-width, 0));
		} else {
			blankSwipe(new Position(-width, 0), Direction.LEFT);
		}
	}
	
	public void swipeRight() {
		final int width = Window.getClientWidth();
		if (horIndex < totalHor) {
			horIndex++;
			swipe(new Position(width, 0));
		} else {
			blankSwipe(new Position(width, 0), Direction.RIGHT);
		}
	}

	public void setProjectAndItem(String projectName, String itemName) {
		project = TestData.getProjectByName(projectName);
		
		totalHor = project.getItems().size();
		horIndex = project.getItemIndex(itemName);
		if (horIndex == -1) {
			horIndex = 0;
		}
		
		verIndex = TestData.getSampleProjects().indexOf(project);
		totalVer = TestData.getSampleProjects().size();
	}
}
