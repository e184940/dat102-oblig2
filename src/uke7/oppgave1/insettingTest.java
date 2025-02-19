package uke7.oppgave1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class insettingTest {
	
	Integer[] a = {2, 5, 7, 4, 1, 8, 9, 3, 6};
	Integer[] rett = {1, 2, 3, 4, 5, 6, 7, 8 ,9};

	
	@Test
	void testSortert(){
		innsettingSortering.innSort(a);
		assertArrayEquals(rett, a);
	}
	
	/*
	@Test
	void testErSortert(){
		Integer[] testTab = innsettingSortering.genererTilfeldigUsortTabell(10);
		innsettingSortering.innSort(testTab);
		boolean erStigende = true;
		for(int i = 0; i < testTab.length; i++) {
			if(!(testTab[i] <= testTab[i + 1])) {
				System.out.println(testTab[i]);
				erStigende = false;
			}
		}
		assertTrue(erStigende);
	}
	*/
	
	@Test
    void testGenererTilfeldigUsortTabell() {
        int length = 10;
        Integer[] testTab = innsettingSortering.genererTilfeldigUsortTabell(length);

        // sjekker at tabellen har riktig lengde
        assertEquals(length, testTab.length);

        // sjekker at tabellen inneholder tilfeldige verdier
        boolean hasRandomValues = false;
        for (int i = 1; i < testTab.length; i++) {
            if (!testTab[i].equals(testTab[i - 1])) {
                hasRandomValues = true;
                break;
            }
        }
        assertTrue(hasRandomValues);
    }
}
