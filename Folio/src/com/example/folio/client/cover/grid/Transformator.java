package com.example.folio.client.cover.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.folio.client.Folio;
import com.example.folio.client.TestData;
import com.example.folio.client.util.Dimension;
import com.example.folio.client.util.Position;
import com.example.folio.client.util.Rectangle;

public class Transformator {
	
	public CoverGrid calcNewGrid(CoverGrid sourceGrid, int newColumnsCount, Folio rootToInject) {
		CoverGrid resultGrid = new CoverGrid(sourceGrid.getName(), newColumnsCount);		
		List<CoverBlock> blocks = copyBlocks(sourceGrid.getBlocks(), rootToInject);
		Collections.sort(blocks, new Comparator<CoverBlock>() {
			@Override public int compare(CoverBlock block1, CoverBlock block2) {
				if (block1.getOrderId() > block2.getOrderId()) {
					return 1;
				} else if (block1.getOrderId() < block2.getOrderId()) {
					return -1;
				} else {
					return 0;	
				}				
			}
		});
		layout(blocks, resultGrid);
		return resultGrid;
	}
	
	private void layout(List<CoverBlock> blocks, CoverGrid grid) {
		for (CoverBlock block : blocks) {
			layoutBlock(block, grid, new Position(0, 0));
		}
	}
	
	private void layoutBlock(CoverBlock block, CoverGrid grid, Position fromPosition) {
		Rectangle freeSpace = findNextFreeSpace(grid, fromPosition, block.getVerticalSize());
		boolean layouted = false;
		Dimension blockSize = block.getSize();
		if (freeSpace.canContain(blockSize)) {
			addCellsToBlock(
				new Rectangle(freeSpace.x, freeSpace.y, blockSize.width, blockSize.height), 
				block, grid
			);
			layouted = true;
		} else {
			for (int dec = 1; dec < block.getHorizontalSize(); dec++) {
				Dimension smallerBlock = block.getDecrementedSize(dec);
				if (freeSpace.canContain(smallerBlock)) {
					addCellsToBlock(
						new Rectangle(freeSpace.x, freeSpace.y, smallerBlock.width, smallerBlock.height), 
						block, grid
					);
					layouted = true;
					break;
				}
			}				
		}
		if (!layouted) {
			int nextPosY = freeSpace.y < grid.getCols() - 1 ? freeSpace.y + 1 : 0;
			int nextPosX = freeSpace.y < grid.getCols() - 1 ? freeSpace.x : freeSpace.x + 1;			
			Position nextFromPosition = new Position(nextPosX, nextPosY);
			layoutBlock(block, grid, nextFromPosition);
		}
	}
	
	private void addCellsToBlock(Rectangle cellsArea, CoverBlock block, CoverGrid grid) {
		List<CoverCell> cellsForBlock = new ArrayList<CoverCell>();
		for (int i = cellsArea.x; i < cellsArea.x + cellsArea.height; i++) {
			for (int j = cellsArea.y; j < cellsArea.y + cellsArea.width; j++) {
				cellsForBlock.add(grid.getCell(i, j));
			}
		}
		grid.addBlock(block, cellsForBlock);
	}
	
	private Rectangle findNextFreeSpace(CoverGrid grid, Position searchFrom, int checkingHeight) {
		int x = -1;
		int y = -1;
		int width = -1;
		int height = -1;
		start: for (int i = searchFrom.getX(); i < grid.getRows(); i++) {
			for (int j = searchFrom.getY(); j < grid.getCols(); j++) {
				CoverCell cell = grid.getCell(i, j);
				if (cell.getBlock() == null) {
					x = i;
					y = j;
					List<Integer> heights = new ArrayList<Integer>();
					for (int c = y; c < grid.getCols(); c++) {
						width = c - y + 1;
						cell = grid.getCell(x, c);
						if (cell.getBlock() != null) {
							width--;
							break;
						}				
						int colHeight = -1;
						for (int r = x; r < x + checkingHeight; r++) {
							colHeight = r - x + 1;
							cell = grid.getCell(r, c);
							if (cell.getBlock() != null) {
								colHeight--;
								break;
							}
						}
						heights.add(colHeight);
					}
					height = Collections.min(heights);
					break start;
				}
			}
		}
		if (x != -1) {
			return new Rectangle(x, y, width, height);
		} else {
			return new Rectangle(grid.getRows(), 0, grid.getCols(), checkingHeight);
		}
	}
	
	public static void main(String[] args) {
		CoverGrid grid = TestData.makeAndreysGrid();
		Rectangle freeSpace = (new Transformator()).findNextFreeSpace(grid, new Position(0, 0), 5);
		System.out.println(freeSpace);
	}
	
	
	public static void askBlocksToFillSnapshotInfo(CoverGrid grid) {
		List<CoverBlock> processedBlocks = new ArrayList<CoverBlock>();
		
		int orderId = 0;
		for (int row = 0; row < grid.getRows(); row++) {
			for (int col = 0; col < grid.getCols(); col++) {
				CoverCell cell = grid.getCell(row, col);
				CoverBlock block = cell.getBlock();
				if (block != null && !processedBlocks.contains(block)) {				
					block.fillSnapshotInfo(orderId++);
					processedBlocks.add(block);
				}
			}
		}
	}
	
	private List<CoverBlock> copyBlocks(List<CoverBlock> list, Folio rootToInjectd) {
		List<CoverBlock> listCopy = new ArrayList<CoverBlock>();
		for (CoverBlock block : list) {
			CoverBlock copy = new CoverBlock(block.getName(), block.getImageUrl(), block.getImageInfo());
			copy.copyFrom(block);
			copy.setRoot(rootToInjectd);
			listCopy.add(copy);
		}
		return listCopy;
	}
	
}
