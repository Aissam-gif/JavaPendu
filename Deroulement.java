import java.util.Scanner;

/*
 *   Classe Dï¿½roulement permet d'assurer le dï¿½roulement du programme ,
 *   qui arrange toutes les mï¿½thodes crï¿½er en classe Jeu et vï¿½rifie des conditions
 *   d'arret de jeu  
 */
public class Deroulement {
	/*
	 * Pour chaque dï¿½roulement On dispose d'un nouveau mot ,donc propriï¿½tï¿½e Game
	 * permet d'associer a chaque deroulement des proprietï¿½s et des methode de JEU
	 * depend de ce nom gï¿½nerer
	 */
	private Jeu Game;
	// InputConsole pour lire l'entrer de l'utilisateur (le caractere entrï¿½)
	private char InputConsole;
	// hint pour lire l'entrer de l'utilisateur quand il demande un hint
	public String hint;
	// Garder les Caracteres gï¿½nerer automatique ( B C E D S A )  dans une propriï¿½tï¿½e 
	private char[] CaractereGenerer;
	public static final Scanner Console = new Scanner(System.in);
	/*
	 * Lors de l'instanciation de la classe , on prend en argument le Jeu
	 *
	 */
	public Deroulement(Jeu Game) {
		this.Game = Game;
		//Generer caractere au hasard
		this.CaractereGenerer = Game.GenerateChar();
	}
	
	/*
	 *  Methode Start consiste a faire derouler le jeu , dans chaque tour 
	 *   1 - On affiche l'ï¿½tat des traits ( _ _ _ _ )
	 *   2 - ..  ...    les caractï¿½res de l'aide ( B C E D S A )
	 *   3 - On demande utilisateur d'entrer un caractï¿½re :
	 *   4 - On fait les vï¿½rification des donnï¿½es entrï¿½s
	 *   5 - On affiche nombre d'essais restans
	 */
	public void start() {
		 System.out.println("Bienvenu Dans Votre Jeu !");
		 System.out.println("Vous Avez " + Game.getMaxEssai() + " Essais ");
		/*
		 *  SetTrait est Un accesseur de la proprietï¿½ du trait dans la classe Jeu
		 *  InitTrait Pour initialiser le nombre de Trait selon le mot dï¿½finie au hasard
		 */
		 
		Game.setTrait(Game.InitTrait());
		do {
			//Chaque tour affichage ï¿½tat traits
			DisplayTrait();
			// Affichage caractere
			DisplayCaracter();
			//Saisie Caractere d'apres l'utilisateur
			try {
				System.out.println("*************************");
				System.out.println("Sacrifier par un essai pour avoir un Hint (Tapez \"hint\" Max-2) ");
				System.out.print("Entrer Un caractere : ");
				hint = Console.next();
				// Prendre le premier caractere saisie de l'utilisateur
				this.InputConsole = hint.charAt(0);
				
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caractere !");
				System.out.println();
			}
			/*
			 * Si le caractï¿½re ou la chaine(premier caractï¿½re) entrï¿½ n'est pas "hint" et il'est juste
			 */
			if (Game.Trait(InputConsole) && !hint.equalsIgnoreCase("hint")) {
				System.out.println("-->> Vous avez bien tourver le caractere ! <<--");
				System.out.println();
			}
			/*
			 * Si le caractï¿½re ou la chaine entrï¿½ n'est pas "hint"
			 */
			else if (!hint.equalsIgnoreCase("hint")){
				System.out.println("-->> Oops , Vous avez mal choisie le caractere ! <<--");
				System.out.println();
				Dessin.draw();  
			} 
			/*
			 * Si la chaine entrï¿½e etait un "hint"
			 */
			else {
				/*
				 * Les cas ou l'utilisateur peux  utiliser un hint :
				 * 	1 - Si le Nombre des hint utilisï¿½ ne depasse pas 2
				 * 	2 - Si il reste plus d'un trait de caractï¿½re vide dans le mot ( A B R _ I C _ T)
				 *  3 - Si le nombre d'essais restant n'est pas a zero
				 */
				if (Game.getHintnbr() <= 2 && Game.Traitleftnbr() > 1 && (Game.getMaxEssai()-Game.getnbrEssaiErrone())>=1 )  {
					System.out.println("-->> Vous avez utiliser un hint ! <<--");
					Game.UseHint();
					Dessin.draw();	
				}
				else {
					System.out.println("-->> Vous pouvez plus Utiliser Hint ! <<--");
					Game.setnbrEssaiErrone(Game.getnbrEssaiErrone()-1);
				}
			}
			
			DisplayEssaiLeft();
			if (NoTraitLeft()) {
				System.out.println("Votre Reponse : ");
				DisplayTrait();
			}
		}while((Game.getnbrEssaiErrone() < Game.getMaxEssai()) && 
			!NoTraitLeft()	);
		/*
		 * Si le nombre d'essai errone n'a pas atteint le max 
		 */
		if ( Game.getMaxEssai() - Game.getnbrEssaiErrone() > 0) {
			System.out.println("|--------------------------------|");
			System.out.println("|--------------------------------|");
			System.out.println("|----------- BRAVO !!! ----------|");
			System.out.println("|--------------------------------|");
			System.out.println("|--------------------------------|");
			System.out.println("Vous Avez Gagnï¿½ le Mot a ete bien : " + Game.getWordGenerated());
			for (int i=0;i<5 ;i++) {
				System.out.println();
			}
		} else {
			System.out.println("Dommage , Vous avez Perdu le Mot a ete : " + Game.getWordGenerated());
			for (int i=0;i<5 ;i++) {
				System.out.println();
			}
		}
	} 
	/*
	 * Affichage l'etat du trait
	 */
	public void DisplayTrait() {
		char[] Trait = Game.getTrait();
		for (int i=0;i<Trait.length;i++) {
			System.out.print(Trait[i] + " ");
		}
		System.out.println();
	}
	/*
	 * Affichage des Caracteres brouiller
	 */
	public void DisplayCaracter() {
		for (int i=0;i<CaractereGenerer.length;i++) {
			System.out.print(CaractereGenerer[i] +" ");
		}
		System.out.println();
	}
	/*
	 * Verficiation S'il existe un trait vide return false sinon true
	 */
	public boolean NoTraitLeft() {
		char[] Trait = Game.getTrait();
		for (int i=0;i< Trait.length;i++) {
			//Existe encore des traits
			if (Trait[i] == '_') return false;
		}
		//No Trait left
		return true;
	}
	public void DisplayEssaiLeft() {
		System.out.println("Nombre d'essais restant : " + (Game.getMaxEssai() - Game.getnbrEssaiErrone()));
		
	}
	/*
	 *  A chaque fois on termine un deroulement on doit faire une reintialisation
	 *  du jeu , en crï¿½ant une instance je noubeau jeu et de generer un nouveau mot
	 *  , reintialiser le compteur du dessin pendu
	 */
	public Jeu ResetGame() {
		Jeu newGame = new Jeu();
		this.CaractereGenerer = newGame.GenerateChar();
		this.Game = newGame;
		Dessin.count = 3;
		return newGame;
	}
}
