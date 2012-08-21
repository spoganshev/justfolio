package com.example.folio.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.example.folio.client.cover.grid.CoverBlock;
import com.example.folio.client.cover.grid.CoverBlock.ImageInfo;
import com.example.folio.client.cover.grid.CoverGrid;
import com.example.folio.client.cover.grid.Transformator;
import com.example.folio.client.project.ProjectPageItem;
import com.example.folio.shared.model.Item;
import com.example.folio.shared.model.Project;

public class TestData {

	public static Map<String, CoverGrid> getCoverGrids() {
		Map<String, CoverGrid> grids = new HashMap<String, CoverGrid>();
		CoverGrid gridAndreys = TestData.makeAndreysGrid();
		grids.put(gridAndreys.getName(), gridAndreys);
		CoverGrid gridSingle = TestData.makeSingleBlockGrid();
		grids.put(gridSingle.getName(), gridSingle);
		CoverGrid gridNice = TestData.makeNiceGrid();
		grids.put(gridNice.getName(), gridNice);
		CoverGrid gridColumn = TestData.makeColumnGrid();
		grids.put(gridColumn.getName(), gridColumn);
		CoverGrid gridSmallCells = TestData.makeSmallCellsGrid();
		grids.put(gridSmallCells.getName(), gridSmallCells);
		CoverGrid gridSmallCols = TestData.makeSmallColsGrid();
		grids.put(gridSmallCols.getName(), gridSmallCols);	
		return grids;
	}	
	
	public static CoverGrid makeSingleBlockGrid() {
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
			}
		}; 
		CoverGrid grid = new CoverGrid("Single block layout", 4);	
		grid.addBlock(
				blocks.get(0), 
				grid.getCell(0, 0),
				grid.getCell(0, 1),
				grid.getCell(0, 2),
				grid.getCell(0, 3),
				grid.getCell(1, 0),
				grid.getCell(1, 1),
				grid.getCell(1, 2),
				grid.getCell(1, 3),
				grid.getCell(2, 0),
				grid.getCell(2, 1),
				grid.getCell(2, 2),
				grid.getCell(2, 3),
				grid.getCell(3, 0),
				grid.getCell(3, 1),
				grid.getCell(3, 2),
				grid.getCell(3, 3),
				grid.getCell(4, 0),
				grid.getCell(4, 1),
				grid.getCell(4, 2),
				grid.getCell(4, 3)
		);		
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	public static CoverGrid makeNiceGrid() {
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
				add(new CoverBlock("Block 2", "block2.jpg", new ImageInfo(630, 420)));
				add(new CoverBlock("Block 3", "block3.jpg", new ImageInfo()));
				add(new CoverBlock("Block 4", "block4.jpg", new ImageInfo()));
				add(new CoverBlock("Block 5", "block5.jpg", new ImageInfo(70, 150)));
				add(new CoverBlock("Block 6", "block6.jpg", new ImageInfo()));
				add(new CoverBlock("Block 7", "block7.jpg", new ImageInfo()));
			}
		}; 
		
		CoverGrid grid = new CoverGrid("Nice layout", 4);		
		
		CoverBlock block1 = blocks.get(0);
		grid.addBlock(
			block1, 
			grid.getCell(0, 0), 
			grid.getCell(1, 0)
		);
		CoverBlock block2 = blocks.get(1);
		grid.addBlock(
			block2, 
			grid.getCell(2, 0), 
			grid.getCell(3, 0)
		);
		CoverBlock block3 = blocks.get(2);
		grid.addBlock(
			block3, 
			grid.getCell(0, 1), 
			grid.getCell(0, 2),
			grid.getCell(1, 1), 
			grid.getCell(1, 2),
			grid.getCell(2, 1), 
			grid.getCell(3, 1),
			grid.getCell(2, 2), 
			grid.getCell(3, 2)
		);
		CoverBlock block4 = blocks.get(3);
		grid.addBlock(
			block4, 
			grid.getCell(0, 3),
			grid.getCell(1, 3)
		);
		CoverBlock block5 = blocks.get(4);
		grid.addBlock(
			block5, 
			grid.getCell(2, 3),
			grid.getCell(3, 3)
		);		
		CoverBlock block6 = blocks.get(5);
		grid.addBlock(
			block6, 
			grid.getCell(4, 0),
			grid.getCell(4, 1),
			grid.getCell(5, 0),
			grid.getCell(5, 1)
		);
		CoverBlock block7 = blocks.get(6);
		grid.addBlock(
			block7, 
			grid.getCell(4, 2),
			grid.getCell(4, 3),
			grid.getCell(5, 2),
			grid.getCell(5, 3)
		);
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	public static CoverGrid makeAndreysGrid() {
		
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
				add(new CoverBlock("Block 2", "block2.jpg", new ImageInfo(630, 420)));
				add(new CoverBlock("Block 3", "block3.jpg", new ImageInfo()));
				add(new CoverBlock("Block 4", "block4.jpg", new ImageInfo()));
				add(new CoverBlock("Block 5", "block5.jpg", new ImageInfo(70, 150)));
				add(new CoverBlock("Block 6", "block6.jpg", new ImageInfo()));
				add(new CoverBlock("Block 7", "block7.jpg", new ImageInfo()));
				add(new CoverBlock("Block 8", "block8.jpg", new ImageInfo()));
			}
		}; 
		
		CoverGrid grid = new CoverGrid("Andrey's layout, fixed", 4);		
		
		CoverBlock block1 = blocks.get(0);
		grid.addBlock(
			block1, 
			grid.getCell(0, 0), 
			grid.getCell(0, 1),
			grid.getCell(1, 0),
			grid.getCell(1, 1)
		);
		CoverBlock block2 = blocks.get(1);
		grid.addBlock(
			block2, 
			grid.getCell(0, 2), 
			grid.getCell(0, 3),
			grid.getCell(1, 2),
			grid.getCell(1, 3),
			grid.getCell(2, 2),
			grid.getCell(2, 3),
			grid.getCell(3, 2),
			grid.getCell(3, 3)
		);
		CoverBlock block3 = blocks.get(2);
		grid.addBlock(
			block3, 
			grid.getCell(2, 0), 
			grid.getCell(3, 0)
		);
		CoverBlock block4 = blocks.get(3);
		grid.addBlock(
			block4, 
			grid.getCell(2, 1)
		);
		CoverBlock block5 = blocks.get(4);
		grid.addBlock(
			block5, 
			grid.getCell(3, 1)
		);		
		CoverBlock block6 = blocks.get(5);
		grid.addBlock(
			block6, 
			grid.getCell(4, 0),
			grid.getCell(4, 1),
			grid.getCell(4, 2),
			grid.getCell(5, 0),
			grid.getCell(5, 1),
			grid.getCell(5, 2),
			grid.getCell(6, 0),
			grid.getCell(6, 1),
			grid.getCell(6, 2)
		);
		CoverBlock block7 = blocks.get(6);
		grid.addBlock(
			block7, 
			grid.getCell(4, 3),
			grid.getCell(5, 3)
		);
		CoverBlock block8 = blocks.get(7);
		grid.addBlock(
			block8, 
			grid.getCell(6, 3)
		);
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	public static CoverGrid makeColumnGrid() {
		
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
				add(new CoverBlock("Block 2", "block2.jpg", new ImageInfo(630, 420)));
				add(new CoverBlock("Block 3", "block3.jpg", new ImageInfo()));
			}
		}; 
		
		CoverGrid grid = new CoverGrid("Column layout, fixed", 4);		
		
		CoverBlock block1 = blocks.get(0);
		grid.addBlock(
			block1, 
			grid.getCell(0, 0), 
			grid.getCell(0, 1),
			grid.getCell(1, 0),
			grid.getCell(1, 1)
		);
		CoverBlock block2 = blocks.get(1);
		grid.addBlock(
			block2, 
			grid.getCell(2, 0), 
			grid.getCell(2, 1),
			grid.getCell(3, 0),
			grid.getCell(3, 1)
		);
		CoverBlock block3 = blocks.get(2);
		grid.addBlock(
			block3, 
			grid.getCell(4, 0), 
			grid.getCell(4, 1),
			grid.getCell(5, 0),
			grid.getCell(5, 1)
		);
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	public static CoverGrid makeSmallCellsGrid() {
		
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
				add(new CoverBlock("Block 2", "block2.jpg", new ImageInfo(630, 420)));
				add(new CoverBlock("Block 3", "block3.jpg", new ImageInfo()));
				add(new CoverBlock("Block 4", "block4.jpg", new ImageInfo()));
				add(new CoverBlock("Block 5", "block5.jpg", new ImageInfo(70, 150)));
				add(new CoverBlock("Block 6", "block6.jpg", new ImageInfo()));
				add(new CoverBlock("Block 7", "block7.jpg", new ImageInfo()));
				add(new CoverBlock("Block 8", "block8.jpg", new ImageInfo()));
			}
		}; 
		
		CoverGrid grid = new CoverGrid("Small cells", 4);		
		
		CoverBlock block1 = blocks.get(0);
		grid.addBlock(
			block1, 
			grid.getCell(0, 0)
		);
		CoverBlock block2 = blocks.get(1);
		grid.addBlock(
			block2, 
			grid.getCell(0, 1)
		);
		CoverBlock block3 = blocks.get(2);
		grid.addBlock(
			block3, 
			grid.getCell(0, 2)
		);
		CoverBlock block4 = blocks.get(3);
		grid.addBlock(
			block4, 
			grid.getCell(0, 3)
		);
		CoverBlock block5 = blocks.get(4);
		grid.addBlock(
			block5, 
			grid.getCell(1, 0)
		);		
		CoverBlock block6 = blocks.get(5);
		grid.addBlock(
			block6, 
			grid.getCell(1, 1)
		);
		CoverBlock block7 = blocks.get(6);
		grid.addBlock(
			block7, 
			grid.getCell(1, 2)
		);
		CoverBlock block8 = blocks.get(7);
		grid.addBlock(
			block8, 
			grid.getCell(1, 3)
		);
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	public static CoverGrid makeSmallColsGrid() {
		
		List<CoverBlock> blocks = new ArrayList<CoverBlock>() {
			{
				add(new CoverBlock("Block 1", "block1.jpg", new ImageInfo(800, 450)));
				add(new CoverBlock("Block 2", "block2.jpg", new ImageInfo(630, 420)));
				add(new CoverBlock("Block 3", "block3.jpg", new ImageInfo()));
				add(new CoverBlock("Block 4", "block4.jpg", new ImageInfo()));
				add(new CoverBlock("Block 5", "block5.jpg", new ImageInfo(70, 150)));
				add(new CoverBlock("Block 6", "block6.jpg", new ImageInfo()));
				add(new CoverBlock("Block 7", "block7.jpg", new ImageInfo()));
				add(new CoverBlock("Block 8", "block8.jpg", new ImageInfo()));
			}
		}; 
		
		CoverGrid grid = new CoverGrid("Small cols", 4);		
		
		CoverBlock block1 = blocks.get(0);
		grid.addBlock(
			block1, 
			grid.getCell(0, 0),
			grid.getCell(1, 0)
		);
		CoverBlock block2 = blocks.get(1);
		grid.addBlock(
			block2, 
			grid.getCell(0, 1),
			grid.getCell(1, 1)
		);
		CoverBlock block3 = blocks.get(2);
		grid.addBlock(
			block3, 
			grid.getCell(0, 2),
			grid.getCell(1, 2)
		);
		CoverBlock block4 = blocks.get(3);
		grid.addBlock(
			block4, 
			grid.getCell(0, 3),
			grid.getCell(1, 3)
		);
		CoverBlock block5 = blocks.get(4);
		grid.addBlock(
			block5, 
			grid.getCell(2, 0),
			grid.getCell(3, 0)
		);		
		CoverBlock block6 = blocks.get(5);
		grid.addBlock(
			block6, 
			grid.getCell(2, 1),
			grid.getCell(3, 1)
		);
		CoverBlock block7 = blocks.get(6);
		grid.addBlock(
			block7, 
			grid.getCell(2, 2),
			grid.getCell(3, 2)
		);
		CoverBlock block8 = blocks.get(7);
		grid.addBlock(
			block8, 
			grid.getCell(2, 3),
			grid.getCell(3, 3)
		);
		
		Transformator.askBlocksToFillSnapshotInfo(grid);
		return grid;
	}
	
	
	public static List<ProjectPageItem> getFourProjectItems() {
		List<ProjectPageItem> items = new ArrayList<ProjectPageItem>();
		for (int i = 1; i <= 4; i++) {
			items.add(new ProjectPageItem("block" + i + ".jpg", "Block " + i));
		}
		return items;
	}
	
	public static List<ProjectPageItem> randomProjectItems() {
		List<ProjectPageItem> items = new ArrayList<ProjectPageItem>();
		int count = generator.nextInt(8);
		for (int i = 0; i < count; i++) {
			items.add(getRandomItem());
		}
		return items;
	}
	
	public static ProjectPageItem getRandomItem() {
		ProjectPageItem[] items = new ProjectPageItem[8];
		for (int i = 0; i < items.length; i++) {
			int itemId = i + 1;
			items[i] = new ProjectPageItem("block" + itemId + ".jpg", "Block " + itemId);
		}
		return getRand(items);
	}
	
	private static Random generator = new Random();
	
	private static <T> T getRand(T[] arr) {
	    int rnd = generator.nextInt(arr.length);
	    return arr[rnd];
	}
	
	private static final int[] ITEM_COUNT = { 2, 3, 5, 1, 8 };
	
	private static List<Project> SAMPLE_PROJECTS = initSampleProjects();
	
	private static List<Project> initSampleProjects() {
		List<Project> projects = new ArrayList<Project>();
		for (int i = 1; i <= 5; ++i) {
			Project p = new Project("Project " + i);
			List<Item> items = new ArrayList<Item>();
			int itemCount = ITEM_COUNT[i - 1];
			for (int j = 1; j <= itemCount; ++j) {
				int blockNumber = (j + i) % 8 + 1;
				items.add(new Item("Item " + j, "block" + blockNumber + ".jpg"));
			}
			p.setItems(items);
			projects.add(p);
		}
		return projects;
	}
	
	public static List<Project> getSampleProjects() {
		return SAMPLE_PROJECTS;
	}
	
	public static Project getRandomProject() {
		return SAMPLE_PROJECTS.get(generator.nextInt(SAMPLE_PROJECTS.size()));
	}

	public static Project getProjectByName(String projectName) {
		for (Project p : SAMPLE_PROJECTS) {
			if (projectName.equals(p.getName())) {
				return p;
			}
		}
		return null;
	}
	
}
