package uke7.oppgave2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class sorteringMålingerTest {

	Integer[] arr1 = {3, 6, 1, 8, 2, 9, 4, 5, 7};
	Integer[] arr2 = {3, 6, 1, 8, 2, 9, 4, 5, 7};
	Integer[] arr3 = {3, 6, 1, 8, 2, 9, 4, 5, 7};
	Integer[] arr4 = {3, 6, 1, 8, 2, 9, 4, 5, 7};
	Integer[] rett = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	@Test
	void testInsertion() {
		sorteringMålinger.insertonSort(arr1);
		assertArrayEquals(arr1, rett);
	}
	
	@Test 
	void testSelection() {
		sorteringMålinger.selectionSort(arr2);
		assertArrayEquals(arr2, rett);
	}
	
	@Test
	void testQuick() {
		sorteringMålinger.quickSort(arr3);
		assertArrayEquals(arr3, rett);
	}
	
	@Test
	void testMerge() {
		sorteringMålinger.mergeSort(arr4);
		assertArrayEquals(arr4, rett);
	}

}
