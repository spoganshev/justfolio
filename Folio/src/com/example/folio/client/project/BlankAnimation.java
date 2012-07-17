package com.example.folio.client.project;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.LayoutPanel;

public class BlankAnimation extends Animation {
	
	public enum Direction { UP, DOWN, LEFT, RIGHT };
	
	private LayoutPanel container;
	private AbsolutePanel panel;
	private AbsolutePanel blankPanel;
	private int width;
	private int height;
	private Direction direction;
	
	public BlankAnimation(LayoutPanel container, AbsolutePanel panel, AbsolutePanel blankPanel, int width, int height, Direction direction) {
		this.container = container;
		this.panel = panel;
		this.blankPanel = blankPanel;
		this.width = width;
		this.height = height;
		this.direction = direction;
	}
	
	
	@Override
	protected void onUpdate(double progress) {
		int part;
		if (direction == Direction.UP || direction == Direction.DOWN) {
			part = height / 5;	
		} else {
			part = width / 5;
		}
		
		if (progress < 0.33) {
		
			int offset = (int) (progress * 3 * part);
			container.setWidgetLeftWidth(panel, - offset, Unit.PX, width, Unit.PX);
			container.setWidgetTopHeight(panel, 0, Unit.PX, height, Unit.PX);
				
			container.setWidgetLeftWidth(blankPanel, width - offset , Unit.PX, width, Unit.PX);
			container.setWidgetTopHeight(blankPanel, 0 , Unit.PX, height, Unit.PX);
		
		} else {
			int offset = (int) (progress * part);
			
			container.setWidgetLeftWidth(panel, offset - part, Unit.PX, width, Unit.PX);
			container.setWidgetTopHeight(panel, 0, Unit.PX, height, Unit.PX);
			
			container.setWidgetLeftWidth(blankPanel, width - (part - offset)  , Unit.PX, width, Unit.PX);
			container.setWidgetTopHeight(blankPanel, 0 , Unit.PX, height, Unit.PX);					
		}

	}

}
