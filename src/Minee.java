/**
 * Igrica Mine se igra tako sto korisnik bira polja unutar tabele koja je dimenzija 10x10
 * Ukoliko korisnik izabere polje na kojoj je mina, program se zavrsava
 * Ukoliko korisnik izabere neko drugo polje, polje se otvara i na njemu je broj mina oko njega
 * Ukoliko korisnik izabere polje na kojoj se nalazi nula, tj oko kojeg nema mina, automatski se otvaraju i ostale nule koje su povezane sa tim poljem
 * @author eminamuratovic
 *
 */
public class Minee {
	public static void main(String[] args) {
		int[][] mat= new int[10][10]; //tabela sa minama
		int[][] m = new int [10][10]; // tabela koja spasava polja i govori da li je polje vec uneseno
		String[][] ma1 = new String [10][10]; // tabela koju koristimo da pamtimo unesena polja
		String[][] ma = new String [10][10]; // tabela koju koristimo za jedno po jedno uneseno polje
		for(int i = 0; i < ma.length; i++)
			for(int j = 0; j < ma.length; j++)
				ma[i][j] = " ";
		mat=napraviMatricu(10, 10);
		ispisiMatricu(mat);
		mat=rasporediMine(mat);
		ispisiMatricu(mat);
		mat=popuniBrojevima(mat);
		ispisiMatricu(mat);
		igraj(mat, m, ma, ma1);
		
	}
	/**
	 * funkcija pamti unesena polja
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat tabela sa minama
	 * @param m prazna tabela u koju se spasavaju unesena polja
	 * @return vraca zadano polje 
	 */
	public static int matricaSaUnesenimPoljima(int x, int y, int[][] mat, int[][] m)
	{
		if(m[x][y] != 2) 
		{
			m[x][y] = 2;
			return mat[x][y];
		}
		return m[x][y];
	}
	/**
	 * funkcija provjerava da li je polje vec otvoreno
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat tabela sa minama
	 * @param m prazna tabela u koju se spasavaju unesena polja
	 * @return true ili false
	 */
	public static boolean provjeraOtvorenogPolja(int x, int y, int[][] mat, int[][] m)
	{
		if(matricaSaUnesenimPoljima(x, y, mat, m) == 2) return true;
		return false;
	}
	/**
	 * ukoliko je polje koje je korisnik izabrao nula, funkcija se pokrece
	 * otvara sve nule koje su povezane s tim poljem
	 * @param x prva koordinata, red 
	 * @param y druga koordinata, kolona
	 * @param mat tabela sa minama
	 * @param m tabela pomocu koje pamtimo jedno polje i provjeravamo da li je korisnik vec unio to polje
	 * @param ma1 prazna tabela u kojoj pamtimo korisnikova unesena polja
	 * @param ma tabela koju koristimo za jedno polje koje je korisnik unio
	 */
	public static void otvoriPolje(int x, int y, int[][] mat, int[][] m, String[][] ma1, String[][] ma) {
		if(x < 0 || x > mat.length - 1 || y < 0 || y > mat.length - 1)
			return;
		if(mat[x][y] == 0 && provjeraOtvorenogPolja(x, y, mat, m) == false) {
			ma1 = napraviTabelu(x, y, mat, ma);
			ispisiTabelu(ma1);
			otvoriPolje(x+1, y, mat, m, ma1, ma); //desno
			otvoriPolje(x-1, y, mat, m, ma1, ma); //lijevo
			otvoriPolje(x, y+1, mat, m, ma1, ma); //gore
			otvoriPolje(x, y-1, mat, m, ma1, ma); //dole
		}
		else {ma1 = napraviTabelu(x, y, mat, ma);
		ispisiTabelu(ma1);}
	}
	/**
	 * pravi matricu
	 * @param red int broj redova
	 * @param kolone int broj kolona
	 * @return vraca matricu
	 */
	public static int[][] napraviMatricu(int red, int kolone)
	{
		int[][] mat=new int[red][kolone];
		
		return mat;
	}
	/**
	 * ispisuje matricu u tabelu
	 * @param mat int dvodimenzionalni niz, matrica
	 */
	public static void ispisiMatricu(int[][] mat)
	{
		for(int i=0; i<mat.length; i++)
			{
			System.out.print("|");
			for(int j=0; j<mat[0].length; j++)
				System.out.printf("%2d |", mat[i][j]);
			System.out.println();
			}
		System.out.println();
	}
	/**
	 * nalazi neki random broj u zadanom intervalu
	 * @param poc int pocetak intervala
	 * @param kraj int kraj intervala
	 * @return neki random broj
	 */
	public static int getRandom(int poc, int kraj)
	{
		int broj=(int)(poc+Math.random()*(kraj-poc));
		return broj;
	}
	/**
	 * rasporedjuje mine, trecina od ukupne povrsine
	 * @param mat int dvodimenzionalni niz, matrica
	 * @return nova matrica sa minama
	 */
	public static int[][] rasporediMine(int[][] mat)
	{
		int brMina=(int)(mat.length*mat.length*0.3);
		int x, y;
		while(brMina>0)
		{
			x=getRandom(0, mat.length);
			y=getRandom(0, mat.length);
			if(mat[x][y]==0)
			{mat[x][y]=-1;
			brMina--;
			}
		}
		return mat;
	}
	/**
	 * popunjava tabelu sa brojevima u odnosu na to koliko mina se nalazi oko tog broja
	 * @param mat int dvodimenzionalni niz, matrica
	 * @return matrica popunjena brojevima
	 */
	public static int[][] popuniBrojevima(int[][] mat)
	{
		for(int i=0; i<mat.length; i++)
			{for(int j=0; j<mat.length; j++)
			{
				if(mat[i][j]==-1)
				{
					if(i!=0 && j!=0 && i!=mat.length-1 && j!=mat.length-1) // sredina tabele
					{
						if(mat[i-1][j-1]!=-1) mat[i-1][j-1]++;
						if(mat[i-1][j]!=-1) mat[i-1][j]++;
						if(mat[i-1][j+1]!=-1) mat[i-1][j+1]++;
						if(mat[i][j-1]!=-1) mat[i][j-1]++;
						if(mat[i][j+1]!=-1) mat[i][j+1]++;
						if(mat[i+1][j-1]!=-1) mat[i+1][j-1]++;
						if(mat[i+1][j]!=-1) mat[i+1][j]++;
						if(mat[i+1][j+1]!=-1) mat[i+1][j+1]++;
					}
					if(i==0 && j==0) // prvi element
						{
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
						if(mat[i+1][j]!=-1)mat[i+1][j]++;
						if(mat[i+1][j+1]!=-1)mat[i+1][j+1]++;
						}
					if(j!=mat.length-1 && i==0 && j!=0) // ostatak prvog reda bez zadnjeg i prvog elementa
					{
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
						if(mat[i+1][j]!=-1)mat[i+1][j]++;
						if(mat[i+1][j+1]!=-1)mat[i+1][j+1]++;
						if(mat[i][j-1]!=-1)mat[i][j-1]++;
						if(mat[i+1][j-1]!=-1)mat[i+1][j-1]++;
					}
					if(i==0 && j==mat.length-1) // prva linija, zadnji elemenat
					{
						if(mat[i][j-1]!=-1) mat[i][j-1]++;
						if(mat[i+1][j]!=-1) mat[i+1][j]++;
						if(mat[i+1][j-1]!=-1) mat[i+1][j-1]++;
					}
					if(i!=mat.length-1 && j==0 && i!=0 ) //prva kolona, bez zadnjeg i prvog elementa
					{
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
						if(mat[i-1][j+1]!=-1)mat[i-1][j+1]++;
						if(mat[i+1][j]!=-1)mat[i+1][j]++;
						if(mat[i+1][j+1]!=-1)mat[i+1][j+1]++;
					}
					if(i==mat.length-1 && j==0) // zadnji elemenat prve kolone
					{
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
						if(mat[i-1][j+1]!=-1)mat[i-1][j+1]++;
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
					}
					if(i== mat.length-1 && j!=0 && j!=mat.length-1) // zadnji red bez prvog i zadnjeg elementa
					{
						if(mat[i-1][j-1]!=-1)mat[i-1][j-1]++;
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
						if(mat[i-1][j+1]!=-1)mat[i-1][j+1]++;
						if(mat[i][j-1]!=-1)mat[i][j-1]++;
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
					}
					if(i==mat.length-1 && j==mat.length-1) // zadnji elemenat zadnjeg reda
					{
						if(mat[i-1][j-1]!=-1)mat[i-1][j-1]++;
						if(mat[i][j-1]!=-1)mat[i][j-1]++;
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
					}
					if(j==mat.length-1 && i!=0 && i!=mat.length-1) // zadnja kolona bez prvog i zadnjeg elementa
					{
						if(mat[i-1][j-1]!=-1)mat[i-1][j-1]++;
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
						if(mat[i][j-1]!=-1)mat[i][j-1]++;
						if(mat[i+1][j-1]!=-1)mat[i+1][j-1]++;
						if(mat[i+1][j]!=-1)mat[i+1][j]++;
					}
					
				}
				
			}
	}
		return mat;
}
	/**
	 * Korisnik pomocu ove funkcije igra igricu
	 * Bira polje i otvara ga
	 * Ukoliko otvori minu, korisnik je zavrsio igru i ispisuje se poruka
	 * Ukoliko pogodi drugo polje onda se igra nastavlja i na tom polju pise koliko mina ima oko toga polja
	 * @param mat tabela sa minama
	 * @param m tabela pomocu koje pamtimo jedno polje i provjeravamo da li je korisnik vec unio to polje
	 * @param ma tabela koju koristimo za jedno polje koje je korisnik unio
	 * @param ma1 prazna tabela u kojoj pamtimo korisnikova unesena polja
	 */
	public static void igraj(int[][] mat, int[][] m, String[][] ma, String[][] ma1)
	{
		int x, y, brojac=0;
		do{
			do{System.out.print("Unesite poziciju: ");
			x=TextIO.getInt();
			y=TextIO.getInt();
			brojac++;
			if(mat[x][y]==-1) System.out.println("Zao nam je! Izgubili ste!");
			else if(brojac==mat.length*mat.length-7)
				System.out.println("Cestitamo! Pobijedili ste!");
			else{
				if(mat[x][y] == 0)
					otvoriPolje(x, y, mat, m, ma1, ma);
					
				else {
					ma1 = napraviTabelu(x, y, mat, ma);
					ispisiTabelu(ma1);
					}
				
			}
			}while(provjeraOtvorenogPolja(x, y, mat, m) == true);
		} while(mat[x][y]!=-1 || brojac!=mat.length*mat.length-7);
		
	}
	/**
	 * pravi tabelu koja pamti polja korisnika
	 * @param x prva koordinata, broj kolone
	 * @param y druga koordinata, broj reda
	 * @param mat tabela sa brodovima
	 * @return ma tabela sa unesenim poljem
	 */
	private static String[][] napraviTabelu(int x, int y, int[][] mat, String[][] m) 
	{
		int broj;
		for(int i=0; i<m.length; i++)
		{
		for(int j=0; j<m[0].length; j++)
			{
			if(i==x && j==y) 
				{
				broj=mat[i][j];
				m[i][j]="";
				m[i][j]+=broj;
				}			
			}		
		}
		return m;
	}
	/**
	 * funkcija ispisuje tabelu sa unesenim poljima
	 * @param m tabela sa poljima
	 */
	private static void ispisiTabelu(String[][] m) 
	{
		System.out.print("   ");
		for(int i = 0; i < m.length; i++)
			System.out.printf(" %2d ", i);
		System.out.println();
		for(int i=0; i<m.length; i++)
		{
			System.out.printf(" %d ", i);
			System.out.print("|");
		for(int j=0; j<m.length; j++)
			System.out.printf("%2s |", m[i][j]);	
		System.out.println();
			}
		
		
	}
}
