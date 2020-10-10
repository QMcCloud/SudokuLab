package pkgHelper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class LatinSquareTest {

	@Test
	public void hasDuplicates_test1() {

		LatinSquare LS = new LatinSquare();
		int[] arr = { 1, 3, 5, 2, 4 };

		boolean bHasDuplicates = LS.hasDuplicates(arr);

		assertEquals(bHasDuplicates, false);

	}

	@Test
	public void hasDuplicates_test2() {

		LatinSquare LS = new LatinSquare();
		int[] arr = { 1, 3, 5, 2, 3 };

		boolean bHasDuplicates = LS.hasDuplicates(arr);

		assertEquals(bHasDuplicates, true);

	}

	@Test
	public void doesElementExist_Test1() {
		LatinSquare LS = new LatinSquare();
		int[] arr = { 1, 3, 5, 2, 4 };
		int iValue = 3;
		boolean bdoesElementExist = LS.doesElementExist(arr, iValue);

		assertEquals(bdoesElementExist, true);
	}

	@Test
	public void doesElementExist_Test2() {
		LatinSquare LS = new LatinSquare();
		int[] arr = { 1, 3, 5, 2, 4 };
		int iValue = 99;
		boolean bdoesElementExist = LS.doesElementExist(arr, iValue);

		assertEquals(bdoesElementExist, false);
	}

	@Test
	public void hasAllValues_Test1() {
		LatinSquare LS = new LatinSquare();
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 3, 2, 1 };

		boolean bhasAllValues = LS.hasAllValues(arr1, arr2);

		assertEquals(bhasAllValues, true);

	}

	@Test
	public void hasAllValues_Test2() {
		LatinSquare LS = new LatinSquare();
		int[] arr1 = { 1, 2, 3 };
		int[] arr2 = { 3, 2, 99 };

		boolean bhasAllValues = LS.hasAllValues(arr1, arr2);

		assertEquals(bhasAllValues, false);

	}

	@Test
	public void getColumn_Test1() {
		
		int[][] MySquare = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							{6, 7, 2, 1, 9, 5, 3, 4, 8},
							{1, 9, 8, 3, 4, 2, 5, 6, 7},
							{8, 5, 9, 7, 6, 1, 4, 2, 3},
							{4, 2, 6, 8, 5, 3, 7, 9, 1},
							{7, 1, 3, 9, 2, 4, 8, 5, 6},
							{9, 6, 1, 5, 3, 7, 2, 8, 4},
							{2, 8, 7, 4, 1, 9, 6, 3, 5},
							{3, 4, 5, 2, 8, 6, 1, 7, 9}};

		LatinSquare LS = new LatinSquare(MySquare);
		int [] ExpectedCol = {3, 7, 9, 5, 2, 1, 6, 8, 4};
		int [] Col = LS.getColumn(1);
		
		assertTrue(Arrays.equals(ExpectedCol, Col));
	}
	
	@Test
	public void getRow_Test1() {
		
		int[][] MySquare = { { 1, 2, 3 }, { 3, 1, 2 }, { 2, 3, 1 } };

		LatinSquare LS = new LatinSquare(MySquare);
		int [] ExpectedRow = {3,1,2};
		int [] Row = LS.getRow(1);
		
		assertTrue(Arrays.equals(ExpectedRow, Row));
	}
	
	@Test
	public void isLatinSquare_Test1() {
		
		int[][] MySquare = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							{6, 7, 2, 1, 9, 5, 3, 4, 8},
							{1, 9, 8, 3, 4, 2, 5, 6, 7},
							{8, 5, 9, 7, 6, 1, 4, 2, 3},
							{4, 2, 6, 8, 5, 3, 7, 9, 1},
							{7, 1, 3, 9, 2, 4, 8, 5, 6},
							{9, 6, 1, 5, 3, 7, 2, 8, 4},
							{2, 8, 7, 4, 1, 9, 6, 3, 5},
							{3, 4, 5, 2, 8, 6, 1, 7, 9}};

		LatinSquare LS = new LatinSquare(MySquare);
		
		assertTrue(LS.isLatinSquare());
	}
	
	@Test
	public void isLatinSquare_Test2() {
		
		int[][] MySquare = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							{6, 7, 2, 1, 9, 5, 3, 4, 8},
							{1, 9, 8, 3, 4, 2, 5, 6, 7},
							{8, 5, 9, 7, 6, 1, 4, 2, 3},
							{4, 2, 6, 8, 5, 3, 7, 9, 1},
							{7, 1, 3, 9, 2, 4, 8, 5, 6},
							{9, 6, 1, 5, 3, 7, 2, 8, 4},
							{2, 8, 7, 4, 1, 9, 6, 3, 5},
							{3, 4, 5, 2, 8, 6, 1, 7, 1}};
		// has 1 in (8,8),. duplicate one's in last row and column
		LatinSquare LS = new LatinSquare(MySquare);
		
		assertFalse(LS.isLatinSquare());
	}
	@Test
	public void isLatinSquare_Test3() {
		
		int[][] MySquare = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
							{6, 7, 2, 1, 9, 5, 3, 4, 8},
							{1, 9, 8, 3, 4, 2, 5, 6, 7},
							{8, 5, 9, 7, 6, 1, 4, 2, 3},
							{4, 2, 6, 8, 5, 3, 7, 9, 1},
							{7, 1, 3, 9, 2, 4, 8, 5, 6},
							{9, 6, 1, 5, 3, 7, 2, 8, 4},
							{2, 8, 7, 4, 1, 9, 6, 3, 5},
							{3, 4, 5, 2, 8, 6, 1, 7, 10}};
		// 10 in last row/column but nowhere else
		LatinSquare LS = new LatinSquare(MySquare);
		
		assertFalse(LS.isLatinSquare());
	}
}
