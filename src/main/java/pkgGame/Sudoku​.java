package pkgGame;

import pkgHelper.LatinSquare;

public class Sudoku​ extends LatinSquare {
	private int iSize;
	private int iSqrtSize;

	public Sudoku​(int iSize) throws Exception {
		this(new int[iSize][iSize]);
	}
 
	public Sudoku​(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		iSqrtSize = (int) Math.sqrt(puzzle.length);
		if(iSize%iSqrtSize !=0) {
			throw new Exception("NotSquareSize");
		}
	}

	public int[][] getPuzzle(){
		return super.getLatinSquare();
	}
	
	int[] getRegion​(int r) {
		int[] region = new int[iSize];
		int cStart = (r%iSqrtSize) * iSqrtSize;
		int rStart = (r/iSqrtSize) * iSqrtSize;
		for(int iCol = 0; iCol<iSqrtSize; iCol++)for(int iRow = 0; iRow < iSqrtSize; iRow ++){
			region[iCol*iSqrtSize + iRow] = getPuzzle()[iCol+cStart][iRow+rStart];
		}
		return region;
	}
	
	int[] getRegion​(int iCol, int iRow) {
		int r = iCol/iSqrtSize + iSqrtSize * (iRow / iSqrtSize);
		return getRegion​(r);
	}
	
	boolean hasDuplicates() {
		for(int i = 0; i < iSize; i++) {
			if(super.hasDuplicates(getRegion​(i)) || super.hasDuplicates(getRow(i))|| super.hasDuplicates(getColumn(i))) {
				return true;
			}
		}
		return false;
	}
	
	boolean isPartialSudoku() {
		if(!ContainsZero() || hasDuplicates()) {
			return false;
		}
		return true;
	}
	
	boolean isSudoku() {
		if(!isLatinSquare() || ContainsZero() || hasDuplicates()) {
			return false;
		}
		return true;
	}
	
	boolean isValidValue​(int iCol, int iRow, int iValue) throws Exception {
		int[][] np = this.getPuzzle();
		np[iCol][iRow] = iValue;
		return new Sudoku​(np).isPartialSudoku();
	}
}
