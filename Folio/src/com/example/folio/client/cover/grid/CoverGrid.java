package com.example.folio.client.cover.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.folio.client.Folio;
import com.example.folio.client.util.Position;
import com.google.gwt.user.client.Window;

public class CoverGrid {
	
	private static Logger LOGGER = Logger.getLogger(CoverGrid.class.getName());
	
	private final List<List<CoverCell>> cells;
	private final List<CoverBlock> blocks;
	private final String name;
	private int cols;
	
	
	public CoverGrid(String name, int cols) {
		this.name = name;
		this.cols = cols;
		int initialRows = 2;
		cells = new ArrayList<List<CoverCell>>(initialRows);
		for (int i = 0; i < initialRows; i++) {
			List<CoverCell> row = new ArrayList<CoverCell>(cols);
			for (int j = 0; j < cols; j++) {
				CoverCell cell = new CoverCell(i, j);
				row.add(cell);
			}
			cells.add(row);
		}
		blocks = new ArrayList<CoverBlock>();
	}
	
	public CoverCell getCell(int rowIdx, int colIdx) {
		int rows = cells.size();
		if (rows <= rowIdx) {
			for (int i = rows; i < rowIdx + 1; i++) {
				List<CoverCell> row = new ArrayList<CoverCell>(cols);
				for (int j = 0; j < cols; j++) {
					CoverCell cell = new CoverCell(i, j);
					row.add(cell);
				}
				cells.add(row);				
			}			
		}
		return cells.get(rowIdx).get(colIdx);
	}
	
	public Position getCellPosition(CoverCell cell) {
		int rowIdx = -1;
		int colIdx = -1;
		for (List<CoverCell> row : cells) {
			rowIdx++;
			colIdx = row.indexOf(cell);
			if (colIdx != -1) {
				break;
			}
		}
		if ((rowIdx != -1) && (colIdx != -1)) {
			return new Position(rowIdx, colIdx);			
		} else {
			return null;
		}
	}
	
	public void addBlock(CoverBlock block, CoverCell... cells) {
		List<CoverCell> blockCells = Arrays.asList(cells);
		addBlock(block, blockCells);
	}
	
	public void addBlock(CoverBlock block, List<CoverCell> cells) {
		block.removeAllCells();
		for (CoverCell cell : cells) {
			cell.setBlock(block);
			block.addCell(cell);
		}
		blocks.add(block);
	}
	
	public void removeBlock(CoverBlock block, CoverCell... cells) {
		List<CoverCell> blockCells = Arrays.asList(cells);
		removeBlock(block, blockCells);
	}
	
	public void removeBlock(CoverBlock block, List<CoverCell> cells) {
		for (CoverCell cell : cells) {
			cell.setBlock(null);
			block.removeCell(cell);
		}
		blocks.remove(block);
	}

	public List<CoverBlock> getBlocks() {
		return blocks;
	}
	
	public int calcWidth() {
		int actualColsCount = cols;
		for (int i = cols -1; i >= 0; i--) {
			int emptyRow = 0;
			for (int j = 0; j < cells.size(); j++) {
				CoverCell cell = getCell(j, i);
				if (cell.getBlock() == null) {
					emptyRow++;
				}
			}
			if (emptyRow == cells.size()) {
				actualColsCount--;
			}
		}
		return actualColsCount * CoverMetric.CELL_WIDTH + (actualColsCount - 1) * CoverMetric.CELL_SPACING;
	}
	
	public int calcHeight() {
		int rows = getRows();
		int actualRowsCount = rows;
		for (int i = rows -1; i >= 0; i--) {
			int emptyRow = 0;
			for (int j = 0; j < cols; j++) {
				CoverCell cell = getCell(i, j);
				if (cell.getBlock() == null) {
					emptyRow++;
				}
			}
			if (emptyRow == cols) {
				actualRowsCount--;
			}
		}
		return actualRowsCount * CoverMetric.CELL_HEIGHT + (actualRowsCount - 1) * CoverMetric.CELL_SPACING;
	}

	public String getName() {
		return name;
	}

	public int getRows() {
		return cells.size();
	}

	public int getCols() {
		return cols;
	}
	
	public void printBlocks() {
		for (CoverBlock block : blocks) {
			LOGGER.log(Level.INFO, block.getSnapshotInfoStr());
		}
	}
	
}
