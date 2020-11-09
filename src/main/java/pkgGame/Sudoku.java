package pkgGame;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import pkgHelper.LatinSquare;

/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {

	private HashMap<Integer, Cell> puzzleMap = new HashMap<Integer, Cell>();
	/**
	 * 
	 * iSize - the length of the width/height of the Sudoku puzzle.
	 * 
	 * @since Lab #2
	 */
	private int iSize;

	/**
	 * iSqrtSize - SquareRoot of the iSize. If the iSize is 9, iSqrtSize will be
	 * calculated as 3
	 * 
	 * @since Lab #2
	 */

	private int iSqrtSize;

	/**
	 * Sudoku - for Lab #2... do the following:
	 * 
	 * set iSize If SquareRoot(iSize) is an integer, set iSqrtSize, otherwise throw
	 * exception
	 * 
	 * @since Lab #2
	 * @param iSize- length of the width/height of the puzzle
	 * @throws Exception if the iSize given doesn't have a whole number square root
	 */
	public Sudoku(int iSize) throws Exception {

		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

		int[][] puzzle = new int[iSize][iSize];
		super.setLatinSquare(puzzle);
		FillDiagonalRegions();

		// FIXME
		// This should build the rest of the sudoku
		SetCells();
		fillRemaining(this.puzzleMap.get(Objects.hash(0, iSqrtSize)));
	}

	/**
	 * Sudoku - pass in a given two-dimensional array puzzle, create an instance.
	 * Set iSize and iSqrtSize
	 * 
	 * @since Lab #2
	 * @param puzzle - given (working) Sudoku puzzle. Use for testing
	 * @throws Exception will be thrown if the length of the puzzle do not have a
	 *                   whole number square root
	 */
	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

	}

	/**
	 * getPuzzle - return the Sudoku puzzle
	 * 
	 * @since Lab #2
	 * @return - returns the LatinSquare instance
	 */
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}

	/**
	 * getRegionNbr - Return region number based on given column and row
	 * 
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegionNbr(3,0) should return a value of 1
	 * 
	 * @param iCol - Given column number
	 * @param iRow - Given row number
	 * @since Lab #2
	 * 
	 * @return - return region number based on given column and row
	 */
	public int getRegionNbr(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return i;
	}

	/**
	 * getRegion - figure out what region you're in based on iCol and iRow and call
	 * getRegion(int)<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(0,3) would call getRegion(1) and return [2],[3],[3],[4]
	 * 
	 * @since Lab #2
	 * @param iCol given column
	 * @param iRow given row
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */
	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	/**
	 * getRegion - pass in a given region, get back a one-dimensional array of the
	 * region's content<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(2) and return [3],[4],[4],[1]
	 * 
	 * @since Lab #2
	 * @param r given region
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int i = (r % iSqrtSize) * iSqrtSize;
		int j = (r / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int iCnt = 0;

		for (; j < jMax; j++) {
			for (i = (r % iSqrtSize) * iSqrtSize; i < iMax; i++) {
				reg[iCnt++] = super.getLatinSquare()[j][i];
			}
		}

		return reg;
	}

	/**
	 * isPartialSudoku - return 'true' if...
	 * 
	 * It's a LatinSquare If each region doesn't have duplicates If each element in
	 * the first row of the puzzle is in each region of the puzzle At least one of
	 * the elemnts is a zero
	 * 
	 * 
	 * @since Lab #2
	 * @return true if the given puzzle is a partial sudoku
	 */
	public boolean isPartialSudoku() {

		if (!super.isLatinSquare()) {
			return false;
		}

		for (int k = 0; k < this.getPuzzle().length; k++) {

			if (super.hasDuplicates(getRegion(k))) {
				return false;
			}

			if (!hasAllValues(getRow(0), getRegion(k))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;

	}

	/**
	 * isSudoku - return 'true' if...
	 * 
	 * Is a partialSudoku Each element doesn't a zero
	 * 
	 * @since Lab #2
	 * @return - returns 'true' if it's a partialSudoku, element match (row versus
	 *         column) and no zeros
	 */
	public boolean isSudoku() {

		if (!isPartialSudoku()) {
			return false;
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given column /
	 * row
	 * 
	 * @since Lab #2
	 * @param iCol   puzzle column
	 * @param iRow   puzzle row
	 * @param iValue given value
	 * @return - returns 'true' if the proposed value is valid for the row and
	 *         column
	 */
	public boolean isValidValue(int iRow, int iCol, int iValue) {

		if (doesElementExist(super.getRow(iRow), iValue)) {
			return false;
		}
		if (doesElementExist(super.getColumn(iCol), iValue)) {
			return false;
		}
		if (doesElementExist(this.getRegion(iCol, iRow), iValue)) {
			return false;
		}

		return true;
	}

	/**
	 * PrintPuzzle This method will print the puzzle to the console (space between
	 * columns, line break after row)
	 * 
	 * @version 1.3
	 * @since Lab #3
	 */
	public void PrintPuzzle() {
		for (int i = 0; i < this.getPuzzle().length; i++) {
			System.out.println("");
			for (int j = 0; j < this.getPuzzle().length; j++) {
				System.out.print(this.getPuzzle()[i][j]);
				if ((j + 1) % iSqrtSize == 0)
					System.out.print(" ");
			}
			if ((i + 1) % iSqrtSize == 0)
				System.out.println(" ");

		}
		System.out.println("");
	}

	/**
	 * FillDiagonalRegions - After the puzzle is created, set the diagonal regions
	 * with random values
	 * 
	 * @since Lab #3
	 */
	private void FillDiagonalRegions() {

		for (int i = 0; i < iSize; i = i + iSqrtSize) {
			System.out.println("Filling region: " + getRegionNbr(i, i));
			SetRegion(getRegionNbr(i, i));
			ShuffleRegion(getRegionNbr(i, i));
		}
	}

	/**
	 * SetRegion - purpose of this method is to set the values of a given region
	 * (they will be shuffled later)
	 * 
	 * Example, the following Puzzle start state:
	 * 
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * SetRegion(2) would transform the Puzzle to:<br>
	 * 
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 1 2 0 0 <br>
	 * 3 4 0 0 <br>
	 * 
	 * @since Lab #3
	 * @param r - Given region number
	 */
	private void SetRegion(int r) {
		int iValue = 0;

		iValue = 1;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = iValue++;
			}
		}
	}

	/**
	 * SetRegion - purpose of this method is to set the values of a given region
	 * (they will be shuffled later)
	 * 
	 * Example, the following Puzzle start state:
	 * 
	 * 1 2 0 0 <br>
	 * 3 4 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * ShuffleRegion(0) might transform the Puzzle to:<br>
	 * 
	 * 2 3 0 0 <br>
	 * 1 4 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * @version 1.3
	 * @since Lab #3
	 * @param r - Given region number
	 */
	private void ShuffleRegion(int r) {
		int[] region = getRegion(r);
		shuffleArray(region);
		int iCnt = 0;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = region[iCnt++];
			}
		}
	}

	/**
	 * shuffleArray this method will shuffle a given one-dimension array
	 * 
	 * @since Lab #3
	 * @param ar given one-dimension array
	 */
	private void shuffleArray(int[] ar) {

		Random rand = new SecureRandom();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	private void SetCells() {
		// FIXME
		// Steps into Cell
		for (int iRow = 0; iRow < iSize; iRow++)
			for (int iCol = 0; iCol < iSize; iCol++) {
				Cell cell = new Cell(iRow, iCol); // creates instance of cell
				cell.setlstValidValues(getAllValidCellValues(iCol, iRow)); // finds all valid values and se them
				// cell.ShuffleValidValues(); // shuffles values with shuffle method from cell
				// hidden class
				puzzleMap.put(cell.hashCode(), cell); // (sets location key of cell as a hash(row,col), value)
			}
	}

	public boolean isValidValue(Cell cell, int val) {
		return isValidValue(cell.getiRow(), cell.getiCol(), val); // calls other method isValidMethod with getters in
																	// cell hidden class
		// FIXME

	}

	private HashSet<Integer> getAllValidCellValues(int iCol, int iRow) {
		HashSet<Integer> potValues = new HashSet<Integer>();
		for (int i = 1; i < iSize + 1; i++) { // loops to check validity of value in cell of values from 1 to the size
												// of sudoku
			if (isValidValue(iRow, iCol, i)) {
				potValues.add(i); // adds value to potential value to hashset
			}
		}
		return potValues;
		// FIXME
		// Prof used collection in walkthrough video, maybe check try something with
		// collections to get values ?...
		// ...But isValidValues is already a working method to find potential Values.
	}

	@SuppressWarnings("unused")
	private void ShowAvailableValues() { // helper method to see values for debugging
		for (int iRow = 0; iRow < iSize; iRow++) { // steps through
			for (int iCol = 0; iCol < iSize; iCol++) {

				Cell cell = puzzleMap.get(Objects.hash(iRow, iCol)); // fetches cell with location key stored in
																		// hashcode
				for (Integer i : cell.getLstValidValues()) {
					System.out.println(i + " ");
				} // Prints potential values in for each loop
				System.out.println(" ");
			}
		}
	}

	private boolean fillRemaining(Cell c) {
		// Given solution method from Walkthrough video
		if (c == null) {
			return true;
		}

		for (int num : c.getLstValidValues()) {
			if (isValidValue(c, num)) {
				this.getPuzzle()[c.getiRow()][c.getiCol()] = num;

				if (fillRemaining(c.GetNextCell(c))) {
					return true;
				}
				this.getPuzzle()[c.getiRow()][c.getiCol()] = 0;

			}
		}
		return false;
	}

	/**
	 * Cell - private class that handles possible remaining values
	 * 
	 * @version 1.4
	 * @since Lab #4
	 * @author Bert.Gibbons
	 *
	 */
	private class Cell {

		private int iRow;
		private int iCol;
		private ArrayList<Integer> lstValidValues = new ArrayList<Integer>();

		public Cell(int iRow, int iCol) {
			super();
			this.iRow = iRow;
			this.iCol = iCol;
		}

		public int getiRow() {
			return iRow;
		}

		public int getiCol() {
			return iCol;
		}

		@Override
		public int hashCode() {
			return Objects.hash(iRow, iCol);
		}

		@Override
		public boolean equals(Object o) {

			if (o == this)
				return true;

			if (!(o instanceof Cell)) {
				return false;
			}
			Cell c = (Cell) o;
			return iCol == c.iCol && iRow == c.iRow;

		}

		public ArrayList<Integer> getLstValidValues() {
			return lstValidValues;
		}

		public void setlstValidValues(HashSet<Integer> hsValidValues) {
			lstValidValues = new ArrayList<Integer>(hsValidValues);
		}

		public void ShuffleValidValues() {
			Collections.shuffle(lstValidValues);
		}

		/**
		 * 
		 * GetNextCell - get the next cell, return 'null' if there isn't a next cell to
		 * find
		 * 
		 * @param c
		 * @param iSize
		 * @return
		 */
		public Cell GetNextCell(Cell c) {
			// Given by Prof in Walkthrough

			int iCol = c.getiCol() + 1;
			int iRow = c.getiRow();
			int iSqrtSize = (int) Math.sqrt(iSize);

			if (iCol >= iSize && iRow < iSize - 1) {
				iRow = iRow + 1;
				iCol = 0;
			}
			if (iRow >= iSize && iCol >= iSize)
				return null;

			if (iRow < iSqrtSize) {
				if (iCol < iSqrtSize)
					iCol = iSqrtSize;
			} else if (iRow < iSize - iSqrtSize) {
				if (iCol == (int) (iRow / iSqrtSize) * iSqrtSize)
					iCol = iCol + iSqrtSize;
			} else {
				if (iCol == iSize - iSqrtSize) {
					iRow = iRow + 1;
					iCol = 0;
					if (iRow >= iSize)
						return null;
				}
			}

			return (Cell) puzzleMap.get(Objects.hash(iRow, iCol));

		}
	}

}