import java.util.Scanner;

public class Menu {
	public static final Scanner Console = new Scanner(System.in);
	static int  choix;
	public static int choixTheme;
	public static void menu(Deroulement Der) {
			
			System.out.println("/*******************************/");
			System.out.println("/ 1-     Lancer Le Jeu    	/ ");
			System.out.println("/ 0-     Quiter Le Jeu    	/ ");
			System.out.println("/*******************************/");
			System.out.print("Entrer votre choix : ");
			 choix = Console.nextInt();
			switch (choix) {
			case 1:
				Der.start();
				break;
			case 0:
				break;
		} 
		
	
	}
	
	public static void menuTheme(Deroulement Der,Jeu Game) {
		do {
			System.out.println("/***************************/");
			System.out.println("/ 1-      Fruits    	 ");
			System.out.println("/ 2-      Legume    	 ");
			System.out.println("/ 3-     Capitales    	 ");
			System.out.println("/ 4-       Pays   	 ");
			System.out.println("/ 5-      Metiers   	 ");
			System.out.println("/***************************/");			
				System.out.print("Selectionner Un theme : ");
				choixTheme = Console.nextInt();
			switch(choixTheme) {
			case 1:
				// appel Menu au dessus
				menu(Der);
				// Lorsque le jeu est termine en renitialise le jeu
				Game = Der.ResetGame();
				break;
			case 2:
				menu(Der);
				Game = Der.ResetGame();
				break;
			case 3:
				menu(Der);
				Game = Der.ResetGame();
				break;
			case 4:
				menu(Der);
				Game = Der.ResetGame();
				break;
			case 5:
				menu(Der);
				Game = Der.ResetGame();
				break;
		 default:
			 menuTheme(Der,Game);
			}
		} while (choix != 0);
	}
	
}
