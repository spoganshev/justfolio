package com.example.folio.client.cover.grid;

public class CoverCell {

	private int colIdx;
	private int rowIdx;

	private CoverBlock block;

	public CoverCell(int rowIdx, int colIdx) {
		super();
		this.colIdx = colIdx;
		this.rowIdx = rowIdx;
	}
	
	public int getColIdx() {
		return colIdx;
	}

	public int getRowIdx() {
		return rowIdx;
	}

	public CoverBlock getBlock() {
		return block;
	}

	void setBlock(CoverBlock block) {
		this.block = block;
	}
	
}
