package uke7.oppgave1;

import java.util.Random;

public class innsettingSortering {

	public static void main(String[] args) {

		/*
		 * 
		 * Integer[] testTab = genererTilfeldigUsortTabell(10); for(int k : testTab) {
		 * System.out.println(k); }
		 * 
		 */

		// deklarerer og initierer tabeller
		Integer[][] tab = new Integer[3][];

		// deklarer tider for måling av kjøretid
		long tider[] = new long[3];
		long startTid = System.currentTimeMillis();
		// long sluttTid = 0;

		// itererer gjennom tabelenne
		for (int i = 0; i <= 2; i++) {
			// generer en tilfeldig tabell
			tab[i] = genererTilfeldigUsortTabell(70000);
			// måler starttiden
			startTid = System.currentTimeMillis();
			// sorterer tabellen
			innSort(tab[i]);
			// tidene for hver av kjøringene
			tider[i] = System.currentTimeMillis() - startTid;
		}

		// legger sammen alle kjøretidene
		long totalTid = 0;
		for (long e : tider) {
			totalTid += e;
		}

		// regner og skriver ut gjennosmnittlig kløretid
		System.out.println("Gjennomsnittlig kjøretid: " + (totalTid / 3) + " ms");
	}

	public static Integer[] genererTilfeldigUsortTabell(int n) {
		// deklarerer og initierer en tabell der vi lagrer en tilfeldig tid
		Integer[] enUsortTabell = new Integer[n];

		// genererer en tilfeldig integer
		Random tilfeldig = new Random();
		// hendholdsvis tilordner den tilfeldige inetegeren til hver sin index i tabellen
		for (int i = 0; i < n; i++) {
			enUsortTabell[i] = tilfeldig.nextInt();
		}
		return enUsortTabell;
	}

	public static <T extends Comparable<? super T>> void innSort(T[] a) {

		int n = a.length;

		if (n < 2)
			return; // håndterer små tabeller

		// setter minste index lik 0
		int minIndex = 0;

		// iterer gjennom mengden, for å finne minste element
		for (int i = 1; i < n; i++) {
			// finner minste element
			if (a[i].compareTo(a[minIndex]) < 0) {
				minIndex = i;
			}
		}

		// optimalisering, sånn at vi senere i while løkken ikke trenger å finne minste element

		// midlertidig variabel settes lik verdien til minste element
		T temp = a[minIndex];
		// flytter verdien fra første index, til minIndex
		a[minIndex] = a[0];
		// setter verdien til elementet på index 0, lik temp
		a[0] = temp;

		// starter fra index 1, siden antagelsen er at a[0] er minste element å bynn med
		for (int i = 2; i < n - 1; i++) {
			// lagrer midlertyidig verdien som skal settes inn på riktig plass
			T min = a[i];
			T max = a[i + 1];

			// sorterer elementene for elementene for å finne minste av de to
			if (min.compareTo(max) > 0) {
				T tempVerdi = min;
				min = max;
				max = tempVerdi;
			}

			// sammenligner med elementet før det på index i
			int j = i - 1;

			// blar gjennom tabellen, finner rikitg plass for største
			while (a[j].compareTo(max) > 0) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 2] = max;

			// blar gjennom tabellen, finner riktig plass for minste
			while (a[j].compareTo(min) > 0) {
				// j representerer indeksen til det forrige elementet i arrayet
				a[j + 1] = a[j];
				j--;
			}
			// dersom j blir -1, betyr det at temp er det minste elementet og må plasseres
			// på indeks 0
			a[j + 1] = min;

			// for tilfeller der lengde er oddetall
			if (n % 2 != 0) {
				T siste = a[n - 1];
				int k = n - 2;
				while (a[k].compareTo(siste) > 0) {
					a[k + 1] = a[k];
					k--;
				}
				a[j + 1] = siste;
			}

		}

	}

}