package uke7.oppgave2;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class sorteringMålinger {

	public static void main(String[] args) {
		int[] testN = {32000, 64000, 128000};
		int antMaalinger = 5;
		
		// metode for å utføre sortering, samt måle tid for de ulike algoritmene
		tabellSorteringer("Insertion Sort", testN, antMaalinger, sorteringMålinger::insertionSort, n -> (double)n * n);
		tabellSorteringer("Selection Sort", testN, antMaalinger, sorteringMålinger::selectionSort, n -> (double)n * n);
		tabellSorteringer("Quick Sort", testN, antMaalinger, sorteringMålinger::quickSort, n -> n * Math.log(n));
		tabellSorteringer("Merge Sort", testN, antMaalinger, sorteringMålinger::mergeSort, n -> n * Math.log(n));
	}

	// metode for å måle sorteringstid for de ulike algoritmene
	public static void tabellSorteringer(String metode, int[] testN, int antMaalinger,
			Consumer<Integer[]> sortFunksjon, Function<Integer, Double> teoriFunk) {
		double c = -1;
		
		System.out.println(metode);
		System.out.println("N        Ant. målinger     Målt tid i ms      Teoretisk tid");

		// løper gjennom hver av de ulike størrelsene N kan ha
		for (int n : testN) {
			long sumTid = 0;

			// måler tiden for å finne en gjenosnitt tid
			for (int i = 0; i < antMaalinger; i++) {
				// generer tilfeldig usortert array
				Integer[] arr = genererTilfeldigUsortTabell(n);
				long startTid = System.currentTimeMillis();
				// utfører sortering
				sortFunksjon.accept(arr); 
				long sluttTid = System.currentTimeMillis();

				sumTid += (sluttTid - startTid);
			}

			long snittTid = sumTid / antMaalinger;

			// initierer c
			if (c == -1) {
				c = snittTid / teoriFunk.apply(n);
			}

			// beregner teoretisk tid basert på n
			double teoriTid = c * teoriFunk.apply(n);
			System.out.println(n + "        " + antMaalinger + "               " + snittTid + "                  " + teoriTid);

		}
		System.out.println();
	}

	// gjenbrukt metode fra oppgave 1
	public static Integer[] genererTilfeldigUsortTabell(int n) {
		Integer[] enUsortTabell = new Integer[n];

		Random tilfeldig = new Random();
		for (int i = 0; i < n; i++) {
			enUsortTabell[i] = tilfeldig.nextInt(100000);
		}
		return enUsortTabell;
	}

	public static void insertionSort(Integer[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			// midlertidig lagring av element som skal sorters
			int tempI = arr[i];
			int j = i - 1;

			// flytter større element til høyre, for å gi plass til temp
			while (j >= 0 && arr[j] > tempI) {
				arr[j + 1] = arr[j];
				j--;
			}
			// setter element på riktig plass
			arr[j + 1] = tempI;
		}
	}

	public static void selectionSort(Integer[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			// antar at første usorterte element er minst
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					// oppdater index dersom mindre element blir funnet
					minIndex = j;
				}
			}
			// erstattet minste element med det største usorterte elementet
			int tempS = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = tempS;
		}
	}

	public static void quickSort(Integer[] arr) {
		quickSortStart(arr, 0, arr.length - 1);
	}

	public static void quickSortStart(Integer[] arr, int lav, int hoy) {
		if (lav < hoy) {
			// finner pivot
			int pivotIndex = partition(arr, lav, hoy);
			// sortere venstre array
			quickSortStart(arr, lav, pivotIndex - 1);
			// sortere høyre array
			quickSortStart(arr, pivotIndex + 1, hoy);
		}
	}

	// partisjonering for quicsort
	private static int partition(Integer[] arr, int lav, int hoy) {
		// velger siste element som pivot
		int pivot = arr[hoy];
		int i = lav - 1;

		// flytter element mindre enn pivot, til venstre
		for (int j = lav; j < hoy; j++) {
			if (arr[j] <= pivot) {
				i++;
				int tempQ = arr[i];
				arr[i] = arr[j];
				arr[j] = tempQ;
			}
		}

		// setter pivot på riktig plass
		int tempQ = arr[i + 1];
		arr[i + 1] = arr[hoy];
		arr[hoy] = tempQ;
		// returnere pivot indexen
		return i + 1;
	}

	public static void mergeSort(Integer[] array) {
		// dersom array har mindre enn 2 elementer, treng det ikke sorteres
		if (array.length < 2)
			return;
		mergeSortStart(array, 0, array.length - 1);
	}

	public static void mergeSortStart(Integer[] arr, int venstre, int hoyre) {
		if (venstre < hoyre) {
			// finner midtpunkt
			int midt = (venstre + hoyre) / 2;
			// sorterer venstre del
			mergeSortStart(arr, venstre, midt);
			// sorterer høyre del
			mergeSortStart(arr, midt + 1, hoyre);
			// slår sammen de to delene
			merge(arr, venstre, midt, hoyre);
		}
	}

	// brukes for å flette sammen sidene i koden ovenfor
	private static void merge(Integer[] arr, int venstre, int midt, int hoyre) {
		int n1 = midt - venstre + 1;
		int n2 = hoyre - midt;

		// oppretter midlertidig arrays
		Integer[] venstreArray = new Integer[n1];
		Integer[] hoyreArray = new Integer[n2];

		// kopierer data til dem
		System.arraycopy(arr, venstre, venstreArray, 0, n1);
		System.arraycopy(arr, midt + 1, hoyreArray, 0, n2);

		int i = 0;
		int j = 0;
		int k = venstre;
		
		// slår sammen arrayene i riktig rekkefølge
		while (i < n1 && j < n2) {
			if (venstreArray[i] <= hoyreArray[j]) {
				arr[k] = venstreArray[i];
				i++;
			} else {
				arr[k] = hoyreArray[j];
				j++;
			}
			k++;
		}

		while (i < n1)
			arr[k++] = venstreArray[i++];
		while (j < n2)
			arr[k++] = hoyreArray[j++];

	}

}
