package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pkgGame.Sudoku​;

class SudokuTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}


	@Test
	public void getRegion​_test1() throws Exception {
		int[][] puzzle = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
						  {6, 7, 2, 1, 9, 5, 3, 4, 8},
						  {1, 9, 8, 3, 4, 2, 5, 6, 7},
						  {8, 5, 9, 7, 6, 1, 4, 2, 3},
						  {4, 2, 6, 8, 5, 3, 7, 9, 1},
						  {7, 1, 3, 9, 2, 4, 8, 5, 6},
						  {9, 6, 1, 5, 3, 7, 2, 8, 4},
						  {2, 8, 7, 4, 1, 9, 6, 3, 5},
						  {3, 4, 5, 2, 8, 6, 1, 7, 9}};
		int[] expectedPuzzleRegion = {4,2,3,7,9,1,8,5,6};
		int[] region;
		try {
			Sudoku​ s1 = new Sudoku​(puzzle);

			region = s1.getRegion​(5);
			System.out.println(Arrays.toString(region));
			assertTrue(Arrays.equals(expectedPuzzleRegion, region));

		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void getRegion​_test2() throws Exception {
		int[][] puzzle = {{1, 4, 3, 2},
						  {3, 2, 1, 4},
						  {2, 3, 4, 1},
						  {4, 1, 2, 3}};
		int[] expectedPuzzleRegion = {2, 3, 4, 1};
		int[] region;
		try {
			Sudoku​ s1 = new Sudoku​(puzzle);

			region = s1.getRegion​(2);
			System.out.println(Arrays.toString(region));
			assertTrue(Arrays.equals(expectedPuzzleRegion, region));

		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void getRegion​_test3() throws Exception {
		int[][] puzzle = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
						  {6, 7, 2, 1, 9, 5, 3, 4, 8},
						  {1, 9, 8, 3, 4, 2, 5, 6, 7},
						  {8, 5, 9, 7, 6, 1, 4, 2, 3},
						  {4, 2, 6, 8, 5, 3, 7, 9, 1},
						  {7, 1, 3, 9, 2, 4, 8, 5, 6},
						  {9, 6, 1, 5, 3, 7, 2, 8, 4},
						  {2, 8, 7, 4, 1, 9, 6, 3, 5},
						  {3, 4, 5, 2, 8, 6, 1, 7, 9}};
		int[] expectedPuzzleRegion = {4,2,3,7,9,1,8,5,6};
		int[] region;
		try {
			Sudoku​ s1 = new Sudoku​(puzzle);

			region = s1.getRegion​(7, 4);
			System.out.println(Arrays.toString(region));
			assertTrue(Arrays.equals(expectedPuzzleRegion, region));

		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void getRegion​_test4() throws Exception {
		int[][] puzzle = {{1, 4, 3, 2},
						  {3, 2, 1, 4},
						  {2, 3, 4, 1},
						  {4, 1, 2, 3}};
		int[] expectedPuzzleRegion = {2, 3, 4, 1};
		int[] region;
		
		try {
			Sudoku​ s1 = new Sudoku​(puzzle);

			region = s1.getRegion​(0,2);
			System.out.println(Arrays.toString(region));
			assertTrue(Arrays.equals(expectedPuzzleRegion, region));

		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void hasDuplicates_test() {
		//TODO: ADD TESTING
		}
	@Test
	public void isSudoku_test1() throws Exception {
		int[][] puzzle = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							{6, 7, 2, 1, 9, 5, 3, 4, 8},
							{1, 9, 8, 3, 4, 2, 5, 6, 7},
							{8, 5, 9, 7, 6, 1, 4, 2, 3},
							{4, 2, 6, 8, 5, 3, 7, 9, 1},
							{7, 1, 3, 9, 2, 4, 8, 5, 6},
							{9, 6, 1, 5, 3, 7, 2, 8, 4},
							{2, 8, 7, 4, 1, 9, 6, 3, 5},
							{3, 4, 5, 2, 8, 6, 1, 7, 9}};

		Sudoku​ S1 = new Sudoku​(puzzle);
		
		assertTrue(S1.isSudoku());
	}}
		/*
		@Test
		public void {
		

}
		@Test
		public void {
			
		}
		
		@Test
		public void {
			
		}
		
		@Test
		public void {
			
		}
		
		@Test
		public void {
			
		}
		
		@Test
		public void {
			
		}
		
		@Test
		public void {
			
		}
	*/
	
	
	
	
	


