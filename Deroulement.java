import java.util.Scanner;

/*
 *   Classe Deroulement permet d'assurer le deroulement du programme , 
 *   qui arrange toutes les methodes creer en classe Jeu et verifie des conditions 
 *   d'arret de jeu  
 */
public class Deroulement {
	/*
	 * Pour chaque deroulement On dispose d'un nouveau mot ,donc propriete Game
	 * permet d'associer a chaque deroulement des proprietes et des methode de JEU
	 * depend de ce nom generer
	 */
	private Jeu Game;
	// InputConsole pour lire l'entrer de l'utilisateur (le caractere entre)
	private char InputFirstChar;
	// hint pour lire l'entrer de l'utilisateur quand il demande un hint
	public String FullInput;
	// Garder les Caracteres generer automatique ( B C E D S A )  dans une propriete 
	private char[] CaractereGenerer;
	public static final Scanner Console = new Scanner(System.in);
	/*
	 * Lors de l'instanciation de la classe , on prend en argument le Jeu
	 *
	 */
	public Deroulement(Jeu Game) {
		this.Game = Game;
	}
	
	/*
	 *  Methode Start consiste a faire derouler le jeu , dans chaque tour 
	 *   1 - On affiche l'etat des traits ( _ _ _ _ )
	 *   2 - ..  ...    les caracteres de l'aide ( B C E D S A )
	 *   3 - On demande utilisateur d'entrer un caractere :
	 *   4 - On fait les verification des donnees entres
	 *   5 - On affiche nombre d'essais restans
	 */
	public void start() {
		/*
		 *  A chaque debut de jeu on initialise les données de jeu (Mot Generer + la liste ) selon
			le choix de l'utilisateur 
		 */
		Game.setData(Menu.choixTheme);
		// Generer Les caractere brouilles
		this.CaractereGenerer = Game.GenerateChar();
		 System.out.println("Bienvenu Dans Votre Jeu !");
		 System.out.println("Vous Avez " + Game.getMaxEssai() + " Essais Errone");
		 
		/*
		 *  SetTrait est Un accesseur de la propriete du trait dans la classe Jeu
		 *  InitTrait Pour initialiser le nombre des Traits selon le mot prelever au hasard
		 */
		Game.setTrait(Game.InitTrait());
		do {
			//Chaque tour affichage etat traits
			Game.DisplayTrait();
			// Affichage caractere
			DisplayCaracter();
			//Saisie Caractere d'apres l'utilisateur
			
				System.out.println("*************************");
				System.out.println("Sacrifier par un essai pour avoir un Hint (Tapez \"hint\" Max-2) ");
				System.out.print("Entrer Un caractere ou (hint) : ");
				// Prendre tout le mot saisie (Si le cas de "hint")
				FullInput = Console.next();
				// Prendre le premier caractere de FullInput
				this.InputFirstChar = FullInput.charAt(0);
				
			// Si l'entrer n'est pas un HINT ou bien le nombre de hint maximal est atteint
			if (!FullInput.equalsIgnoreCase("hint")) {
				/*
				 * Si le caractere ou la chaine(premier caractere) entre n'est pas "hint" et il'est juste
				 */
				if (Game.Trait(InputFirstChar)) {
					System.out.println("-->> Vous avez bien tourver le caractere ! <<--");
					System.out.println();
				}
				/*
				 * Si le caractere ou la chaine entre n'est pas "hint"
				 */
				else {
					System.out.println("-->> Oops , Vous avez mal choisie le caractere ! <<--");
					System.out.println();
					Dessin.draw(); 
				}
			}
			
			/*
			 * Si la chaine entree etait un "hint"
			 */
			else {
				/*
				 * Les cas ou l'utilisateur peux  utiliser un hint :
				 * 	1 - Si le Nombre des hint utilisee ne depasse pas 2
				 * 	2 - Si il reste plus d'un trait de caractere vide dans le mot ( A B R _ I C _ T)
				 *  3 - Si le nombre d'essais restant n'est pas a zero
				 */
				if (Game.getHintnbr() < 2 && Game.Traitleftnbr() > 1 && (Game.getMaxEssai()-Game.getnbrEssaiErrone())>=1 )  {
					System.out.println("-->> Vous avez utiliser un hint ! <<--"); 
					Game.UseHint(); 
					Dessin.draw(); 
				}
				else {
					System.out.println("-->> Vous pouvez plus Utiliser Hint ! <<--");	
				}
			}
			
			Game.DisplayEssaiLeft();
			if (Game.NoTraitLeft()) {
				System.out.println("Votre Reponse : ");
				Game.DisplayTrait();
			}
		}while((Game.getnbrEssaiErrone() < Game.getMaxEssai()) && 
			!Game.NoTraitLeft()	);
		/*
		 * Si le nombre d'essai errone n'a pas atteint le max 
		 */
		
		if ( Game.getnbrEssaiErrone() < Game.getMaxEssai()) {
			System.out.println("|--------------------------------|");
			System.out.println("|--------------------------------|");
			System.out.println("|----------- BRAVO !!! ----------|");
			System.out.println("|--------------------------------|");
			System.out.println("|--------------------------------|");
			System.out.println("Vous Avez Gagne le Mot a ete bien : " + Game.getWordGenerated());
			for (int i=0;i<4 ;i++) {
				System.out.println();
			}
		} else {
			System.out.println("Dommage , Vous avez Perdu le Mot a ete : " + Game.getWordGenerated());
			for (int i=0;i<4 ;i++) {
				System.out.println();
			}
		}
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
	 *  A chaque fois on termine un deroulement on doit faire une reintialisation
	 *  du jeu , en creant une instance de nouveau jeu et de generer un nouveau mot
	 *  , reintialiser le compteur du dessin pendu
	 */
	public Jeu ResetGame() {
		Jeu newGame = new Jeu();
		this.Game = newGame;
		Dessin.count = 0;
		return newGame;
	}
}
