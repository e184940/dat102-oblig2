package uke7.oppgave2;

public class sorteringMålinger {

	public static void insertonSort(Integer[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			int tempI = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > tempI) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = tempI;
		}
	}

	public static void selectionSort(Integer[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int tempS = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = tempS;
		}
	}

	public static void quickSort(Integer[] arr, int lav, int hoy) {
		if (lav < hoy) {
			int pivotIndex = partition(arr, lav, hoy);
			quickSort(arr, lav, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, hoy);
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

	public static void mergeSort(Integer[] arr, int venstre, int hoyre) {
		if (venstre < hoyre) {
			int midt = (venstre + hoyre) / 2;
			mergeSort(arr, venstre, midt);
			mergeSort(arr, midt + 1, hoyre);
			merge(arr, venstre, midt, hoyre);
		}
	}

	private static void merge(Integer[] arr, int venstre, int midt, int hoyre) {
		int n1 = midt - venstre + 1;
		int n2 = hoyre - midt;

		Integer[] venstreArray = new Integer[n1];
		Integer[] hoyreArray = new Integer[n2];

		System.arraycopy(arr, venstre, venstreArray, 0, n2);
		System.arraycopy(arr, midt + 1, hoyreArray, 0, n2);

		int i = 0;
		int j = 0;
		int k = venstre;
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
		
		while(i < n1) arr[k++] = venstreArray[i++];
		while(j < n2) arr[k++] = hoyreArray[j++];
		
	}

}
