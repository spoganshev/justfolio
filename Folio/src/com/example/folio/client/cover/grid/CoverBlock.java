package com.example.folio.client.cover.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.folio.client.Folio;
import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Position;
import com.example.folio.client.util.Rectangle;
import com.google.gwt.user.client.ui.LayoutPanel;

public class CoverBlock {
	
	public enum Orientation {
		PORTRAIT(2, 1) {
			@Override int calcHeight(int width) {
				return width *2;
			}			
		}, 
		LANDSCAPE(1, 1) {
			@Override int calcHeight(int width) {
				return width;
			}
		};
		
		private Orientation(int minHeight, int minWidth) {
			this.minHeight = minHeight;
			this.minWidth = minWidth;
		}
		
		abstract int calcHeight(int width);
		
		public final int minHeight;
		public final int minWidth;
	}
	
	public static class ImageInfo {
		public final boolean scale;
		public final Position focusPoint;
		public ImageInfo(int x, int y) {
			this.scale = false;
			focusPoint = new Position(x, y);
		}
		public ImageInfo() {
			this.scale = true;
			focusPoint = null;
		}
		
		@Override
		public String toString() {
			return (scale ? "Scale" : "Crop, ") + 
					(!scale ? "Center To " + focusPoint.getX() + ":" + focusPoint.getY() : "");
		}
	}
	
	private final List<CoverCell> cells;
	public final String imageUrl;	
	private CellPanel panel;

	private int orderId;
	private Orientation orientation;
	private int horizontalSize;
	private int verticalSize;
	private String name;
	private ImageInfo imageInfo;
	
	private Folio root;
	
	private Rectangle rect;
	
	public CoverBlock(String name, String imageUrl, ImageInfo imageInfo) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.imageInfo = imageInfo;
		cells = new ArrayList<CoverCell>();
	}
	
	void addCell(CoverCell cell) {
		cells.add(cell);
	}
	
	void removeCell(CoverCell cell) {
		cells.remove(cell);
	}
	
	void removeAllCells() {
		cells.clear();
	}

	public Rectangle calcRectangle() {
		if (rect == null) {
			Collections.sort(
				cells, 
				new Comparator<CoverCell>() {
					@Override
					public int compare(CoverCell cell1, CoverCell cell2) {
						if (cell1.getRowIdx() > cell2.getRowIdx()) {
							return 1;
						} else if (cell1.getRowIdx() < cell2.getRowIdx()) {
							return -1;
						} else {
							if (cell1.getColIdx() > cell2.getColIdx()) {
								return 1;
							} else if (cell1.getColIdx() < cell2.getColIdx()) {
								return -1;
							} else {
								return 0;
							}
						}
					}
				}
			);
			CoverCell topLeftCell = cells.get(0);
			CoverCell bottomRightCell = cells.get(cells.size() - 1);
			
			int x = topLeftCell.getColIdx() * CoverMetric.CELL_WIDTH + topLeftCell.getColIdx() * CoverMetric.CELL_SPACING;
			int y = topLeftCell.getRowIdx() * CoverMetric.CELL_HEIGHT + topLeftCell.getRowIdx() * CoverMetric.CELL_SPACING;
			int height = (bottomRightCell.getRowIdx() - topLeftCell.getRowIdx() + 1) * CoverMetric.CELL_HEIGHT + 
					(bottomRightCell.getRowIdx() - topLeftCell.getRowIdx()) * CoverMetric.CELL_SPACING;
			int width = (bottomRightCell.getColIdx() - topLeftCell.getColIdx() + 1) * CoverMetric.CELL_WIDTH +
					(bottomRightCell.getColIdx() - topLeftCell.getColIdx()) * CoverMetric.CELL_SPACING;
			
			rect = new Rectangle(x, y, width, height);
		}
		return rect;
	}

	public CellPanel getPanel(LayoutPanel container) {
		if ((panel == null) || (container.getWidgetIndex(panel) == -1)) {
	        panel = new CellPanel(this);
	        container.add(panel);
		}
		return panel;
	}
	
	private CellPanel getPanel() {
		return panel;
	}

	public void setPanel(CellPanel panel) {
		this.panel = panel;
	}

	public int getOrderId() {
		return orderId;
	}

	private void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getHorizontalSize() {
		return horizontalSize;
	}

	private void setHorizontalSize(int size) {
		this.horizontalSize = size;
	}

	public int getVerticalSize() {
		return verticalSize;
	}

	private void setVerticalSize(int verticalSize) {
		this.verticalSize = verticalSize;
	}
	
	public Dimension getSize() {
		return new Dimension(getHorizontalSize(), getVerticalSize());
	}
	
	public Dimension getDecrementedSize(int dec) {
		int width = getHorizontalSize();
		int newWidth = width - dec;		
		if (newWidth < orientation.minWidth) {
			newWidth = orientation.minWidth;
		}
		int newHeight = orientation.calcHeight(newWidth);
		return new Dimension(newWidth, newHeight);
	}
	
	public void fillSnapshotInfo(int orderId) {
		setOrderId(orderId);
		CoverCell firstCell = cells.get(0);
		int minCol = firstCell.getColIdx();
		int minRow = firstCell.getRowIdx();
		int maxCol = firstCell.getColIdx();
		int maxRow = firstCell.getRowIdx();
		for (CoverCell cell : cells) {			
			maxCol = Math.max(maxCol, cell.getColIdx());
			maxRow = Math.max(maxRow, cell.getRowIdx());
			minCol = Math.min(minCol, cell.getColIdx());
			minRow = Math.min(minRow, cell.getRowIdx());
		}
		setVerticalSize(maxRow - minRow + 1);
		setHorizontalSize(maxCol - minCol + 1);
		setOrientation(
			getHorizontalSize() >= getVerticalSize() ?
			Orientation.LANDSCAPE : 
			Orientation.PORTRAIT
		);

	}
	
	public String getSnapshotInfoStr() {
		return "orderId = "+orderId + ", orientation = " + orientation + ", size = " + horizontalSize;
	}
	
	public void copyFrom(CoverBlock from) {
		this.setPanel(from.getPanel());
		if (getPanel() != null) {
			this.getPanel().reconnectToBlock(this);
		}
		this.setHorizontalSize(from.getHorizontalSize());
		this.setVerticalSize(from.getVerticalSize());
		this.setOrderId(from.getOrderId());
		this.setOrientation(from.getOrientation());
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}

	public ImageInfo getImageInfo() {
		return imageInfo;
	}
	
	@Override
	public String toString() {
		return imageInfo.toString();
	}
	
	public void setRoot(Folio root) {
		this.root = root;
	}

	public Folio getRoot() {
		return root;
	}
	
	
}
