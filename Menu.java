import java.util.Scanner;

public class Menu {
	public static final Scanner Console = new Scanner(System.in);
	static int  choix;
	public static void menu(Deroulement Der,Jeu Game) {
		do {	
			System.out.println("/*******************************/");
			System.out.println("/ 1-     Lancer Le Jeu    	/ ");
			System.out.println("/ 0-     Quiter Le Jeu    	/  ");
			System.out.println("/*******************************/");
			System.out.print("Entrer votre choix : ");
			 choix = Console.nextInt();
			switch (choix) {
			case 1:
				Der.start();
				Game = Der.ResetGame();
				break;
			case 0:
				break;
		} 
		} while (choix != 0);
	
	}
	
}
