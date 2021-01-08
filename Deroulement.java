import java.util.Scanner;

public class Deroulement {
	private Jeu Game;
	private char InputConsole;
	private char[] CaractereGenerer;
	public static final Scanner Console = new Scanner(System.in);
	public Deroulement(Jeu Game) {
		this.Game = Game;
		//Generer caractere au hasard
		this.CaractereGenerer = Game.GenerateChar();
	}
	
	public void start() {
		 System.out.println("Bienvenu Dans Votre Jeu !");
		 System.out.println("Vous Avez " + Game.getMaxEssai() + " Essais ");
		//System.out.println("The Word was : " + Game.getWordGenerated());
		// SetTrait est Un accesseur de la proprieté du trait dans la classe GAME
		// InitTrait Pour initialiser le nombre de Trait selon le mot définie au hasard
		Game.setTrait(Game.InitTrait());
		//char[] CaracteresGenerer = Game.GenerateChar();
		do {
			//Chaque tour affichage état trait
			DisplayTrait();
			DisplayCaracter();
			//Saisie Caractere d'apres l'utilisateur
			try {
				System.out.println("*************************");
				System.out.print("Entrer Un caractere : ");	
				// Prendre le premier caractere saisie de l'utilisateur
				this.InputConsole = Console.next().charAt(0);
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caractere !");
				System.out.println();
			}
			//Verficiation l'etat du Trait avec le nouveau caractere
			if (Game.Trait(InputConsole)) {
				System.out.println("-->> Vous avez bien tourver le caractere ! <<--");
				System.out.println();
			}else {
				System.out.println("-->> Oops , Vous avez mal choisie le caractere ! <<--");
				System.out.println();
				Dessin.draw();
			}
			DisplayEssaiLeft();
			if (NoTraitLeft()) {
				System.out.println("Votre Reponse : ");
				DisplayTrait();
			}
		}while((Game.getnbrEssaiErrone() < Game.getMaxEssai()) && 
			!NoTraitLeft()	);
		if ( Game.getMaxEssai() - Game.getnbrEssaiErrone() > 0) {
			System.out.println("Bravo , Vous Avez Gagné le Mot a ete bien : " + Game.getWordGenerated());
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
	public void DisplayTrait() {
		char[] Trait = Game.getTrait();
		for (int i=0;i<Trait.length;i++) {
			System.out.print(Trait[i] + " ");
		}
		System.out.println();
	}
	public void DisplayCaracter() {
		for (int i=0;i<CaractereGenerer.length;i++) {
			System.out.print(CaractereGenerer[i] +" ");
		}
		System.out.println();
	}
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
	// Reset All The Game
	public Jeu ResetGame() {
		Jeu newGame = new Jeu();
		this.CaractereGenerer = newGame.GenerateChar();
		this.Game = newGame;
		Dessin.count = 4;
		return newGame;
	}
}
