
public class Minee {
	public static void main(String[] args) {
		int[][] mat= new int[5][5];
		mat=napraviMatricu(5, 5);
		ispisiMatricu(mat);
		mat=rasporediMine(mat);
		ispisiMatricu(mat);
		mat=popuniBrojevima(mat);
		ispisiMatricu(mat);
		igraj(mat);
		
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
			x=getRandom(0, 5);
			y=getRandom(0, 5);
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
						if(mat[i][j+1]!=-1)mat[i][j+1]++;
						if(mat[i-1][j]!=-1)mat[i-1][j]++;
						if(mat[i-1][j+1]!=-1)mat[i-1][j+1]++;
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
	public static void igraj(int[][] mat)
	{
		int x, y, brojac=0;
		do{
			System.out.print("Unesite poziciju: ");
			x=TextIO.getInt();
			y=TextIO.getInt();
			brojac++;
			if(mat[x][y]==-1) System.out.println("Zao nam je! Izgubili ste!");
			else if(brojac==mat.length*mat.length-7)
				System.out.println("Cestitamo! Pobijedili ste!");
			else{
				ispisiTabelu(x, y, mat);
			}
		} while(mat[x][y]!=-1 || brojac!=mat.length*mat.length-7);
		
	}
	private static void ispisiTabelu(int x, int y, int[][] mat) 
	{
		int broj;
		String[][] m=new String[mat.length][mat.length];
		for(int i=0; i<m.length; i++)
		{
		System.out.print("|");
		for(int j=0; j<m[0].length; j++)
			{
			m[i][j]=" ";
			if(i==x && j==y) 
				{
				broj=mat[i][j];
				m[i][j]="";
				m[i][j]+=broj;
				}
			System.out.printf("%2s |", m[i][j]);
			
			}
		System.out.println();
		}
	System.out.println();
	}
}
