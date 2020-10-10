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

	protected int[][] getPuzzle(){
		return super.getLatinSquare();
	}
	
	protected int[] getRegion​(int r) {
		int[] region = new int[iSize];
		int rStart = (r%iSqrtSize) * iSqrtSize;
		int cStart = (r/iSqrtSize) * iSqrtSize;
		for(int iCol = 0; iCol<iSqrtSize; iCol++)for(int iRow = 0; iRow < iSqrtSize; iRow ++){
			region[iCol*iSqrtSize + iRow] = getPuzzle()[iCol+cStart][iRow+rStart];
		}
		return region;
	}
	
	protected int[] getRegion​(int iRow, int iCol) {
		int r = (iSqrtSize * (iCol/iSqrtSize)) + ((iRow / iSqrtSize));
		return getRegion​(r);
	}
	
	protected boolean hasDuplicates() {
		for(int i = 0; i < iSize; i++) {
			if(super.hasDuplicates(getRegion​(i)) || super.hasDuplicates(getRow(i))|| super.hasDuplicates(getColumn(i))) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isPartialSudoku() {
		if(!ContainsZero() || hasDuplicates()) {
			return false;
		}
		return true;
	}
	
	protected boolean isSudoku() {
		if(!isLatinSquare() || ContainsZero() || hasDuplicates()) {
			return false;
		}
		return true;
	}
	
	protected boolean isValidValue​(int iCol, int iRow, int iValue) throws Exception {
		int[][] np = this.getPuzzle();
		np[iCol][iRow] = iValue;
		return new Sudoku​(np).isPartialSudoku();
	}
}
