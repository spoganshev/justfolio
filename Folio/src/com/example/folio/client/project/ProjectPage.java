package com.example.folio.client.project;

import java.util.logging.Logger;

import com.example.folio.client.Page;
import com.example.folio.client.TestData;
import com.example.folio.client.project.BlankAnimation.Direction;
import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Position;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;

public class ProjectPage extends Page {

	private static Logger LOGGER = Logger.getLogger(ProjectPage.class.getName());
	
	private LayoutPanel container;
	
	private ProjectItem projectItem;
	
	private int horIndex;
	private int verIndex;
	
	AbsolutePanel prevPanel;
	
	public ProjectPage() {
		container = new LayoutPanel();
		container.setStyleName("project_page");
		projectItem = TestData.getRandomItem();
		AbsolutePanel itemPanel = projectItem.getPanel();
		container.add(itemPanel);
		projectItem.setPage(this);
	}

	@Override
	public Panel getPagePanel() {
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
		container.addDomHandler(new KeyPressHandler() {			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				LOGGER.fine("KeyPress" + event.getCharCode());				
			}
		}, KeyPressEvent.getType());
		redraw(new Dimension(Window.getClientWidth(), Window.getClientHeight()));
	}
	
	@Override
	public String getHistoryToken() {
		return "project";
	}
	
	private void swipe(final Position from) {
		final int width = Window.getClientWidth();
		final int height = Window.getClientHeight();
		
		final ProjectItem nextItem = TestData.getRandomItem();
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
		if (verIndex < 5) {	
			swipe(new Position(0, height));	
			verIndex++;
		} else {
			blankSwipe(new Position(0, height), Direction.DOWN);
		}
	}
	
	public void swipeBack() {
		final int height = Window.getClientHeight();
		if (verIndex > 0) { 
			swipe(new Position(0, -height));
			verIndex--;
		} else {
			blankSwipe(new Position(0, -height), Direction.UP);
		}
	}

	
	public void swipeLeft() {
		final int width = Window.getClientWidth();
		if (horIndex > 0) {
			swipe(new Position(-width, 0));
			horIndex--;
		} else {
			blankSwipe(new Position(-width, 0), Direction.LEFT);
		}
	}
	
	public void swipeRight() {
		final int width = Window.getClientWidth();
		if (horIndex < 5) {
			swipe(new Position(width, 0));
			horIndex++;
		} else {
			blankSwipe(new Position(width, 0), Direction.RIGHT);
		}
	}
}
	
