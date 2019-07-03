package office;

import java.util.Scanner;

public class Word {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dobrodošli u konzolni Word™!");
		System.out.println("----------------------------");
		
		String komande= "Komande:\r\n" + 
				" -1) Izlaz iz programa\r\n" + 
				"  0) Obriši rečenicu [int]\r\n" + 
				"  1) Dodaj rečenicu [String]\r\n" + 
				"  2) Dodaj blok teksta [String]\r\n" + 
				"  3) Izmeni rečenicu [int] [String]\r\n" + 
				"  4) Ispis celog teksta\r\n" + 
				" 99) Ispis komandnog menija\r\n" + 
				"----------------------------";
		
		System.out.println(komande);
		
		int duzina = 0;
		String[] niz = new String[100];
		
		int k = 0;
		while(k != -1) {
			System.out.print("komanda: ");
			k=sc.nextInt();
			switch(k) {
				case -1: break;
				case 0: duzina = obrisi(niz, duzina, sc.nextInt()); break;
				case 1: duzina = dodaj_recenicu(niz, duzina, sc.nextLine()); break;
				case 2: duzina = dodaj_blok(niz, duzina, sc.nextLine()); break;
				case 3: izmeni(niz, sc.nextInt(),sc.nextLine()); break;
				case 4: ispis(niz, duzina); break;
				case 99: System.out.println(komande); break;
				default: System.err.println("Nepostojeca komanda");
			}
		}
	}

	private static void ispis(String[] niz, Integer duzina) {
		System.out.println("---------- tekst ------------");
		for(int i=1; i<=duzina; i++) {
			System.out.println(i + ": " + niz[i-1]);
		}
		System.out.println("---------- status ------------");
		System.out.println("broj reči: " + broj_reci(niz, duzina));
	}

	private static int broj_reci(String[] niz, Integer duzina) {
		int broj = 0;
		for(int i=0;i<duzina;i++)
			broj += niz[i].split(" ").length;
		return broj;
	}

	private static String formatiraj(String tekst) {
		tekst = tekst.trim(); //brise nepotrebne blanko znake sa pocetka i kraja
		tekst = tekst.substring(0,1).toUpperCase() + tekst.substring(1); //samo prvi kapitalizuj
		char last = tekst.charAt(tekst.length() - 1);
		if(last != '.' && last !='!' && last!= '?')
			tekst += '.';
		return tekst;
	}
	private static void izmeni(String[] niz, int linija, String tekst) {
		niz[linija-1] = formatiraj(tekst);
	}

	private static int dodaj_blok(String[] niz, int duzina, String tekst) {
		String[] recenice = tekst.split("[.!?]");
		for(int i =0; i<recenice.length; i++)
			duzina = dodaj_recenicu(niz, duzina, recenice[i]);
		return duzina;
	}

	private static int dodaj_recenicu(String[] niz, int duzina, String tekst) {
		niz[duzina] = formatiraj(tekst);
		return ++duzina;
	}

	private static int obrisi(String[] niz, int duzina, int linija) {
		for(int i=linija;i<duzina;i++)
			niz[i-1]=niz[i];
		return --duzina;
	}
}
