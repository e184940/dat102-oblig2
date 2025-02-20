package uke7.oppgave2;

import java.util.Arrays;

public class quickSortLikeElement {

	public static void main(String[] args) {

		// for høy n vil føre til en stacksverflow error
		int n = 600;
		Integer[] arr = new Integer[n];
		Arrays.fill(arr, 30);

		long startTid = System.currentTimeMillis();
		quickSort(arr);
		long sluttTid = System.currentTimeMillis();

		System.out.println("Tid brukt på sortering av " + n + " elementer, der alle elemnter er like: "
				+ (sluttTid - startTid) + " ms");
	}

	public static void quickSort(Integer[] arr) {
		quickSortStart(arr, 0, arr.length - 1);
	}

	public static void quickSortStart(Integer[] arr, int lav, int hoy) {
		if (lav < hoy) {
			int pivotIndex = partition(arr, lav, hoy);
			quickSortStart(arr, lav, pivotIndex - 1);
			quickSortStart(arr, pivotIndex + 1, hoy);
		}
	}

	private static int partition(Integer[] arr, int lav, int hoy) {
		int pivot = arr[hoy];
		int i = lav - 1;

		for (int j = lav; j < hoy; j++) {
			if (arr[j] <= pivot) {
				i++;
				int tempQ = arr[i];
				arr[i] = arr[j];
				arr[j] = tempQ;
			}
		}

		int tempQ = arr[i + 1];
		arr[i + 1] = arr[hoy];
		arr[hoy] = tempQ;
		return i + 1;
	}
}
