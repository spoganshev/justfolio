package com.example.folio.client.cover.grid;


public class CoverMetric {

	public static int CELL_WIDTH = 216;
	public static int CELL_HEIGHT = 144;
	public static int CELL_SPACING = 12;	

	
	public static int calcNumberOfColumns(int screenWidth) {
		int rows = screenWidth / (CELL_WIDTH + CELL_SPACING);
		return rows;
	}
	
	public static void main(String[] args) {
		int width = 900;
		int columns = calcNumberOfColumns(width);
		System.out.println(
			"Screen width = " + width + 
			", columns = " + columns + 
			", cover width = " + (columns*CELL_WIDTH + (columns - 1) *CELL_SPACING)
		);
	}
	
}
