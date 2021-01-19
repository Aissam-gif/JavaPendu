import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Jeu {
	private int nbrEssaiErrone;
	private final int MaxEssai = 5;   //nbr total des essais
	private String WordGenerated; // Stockage mot générer de la liste
	private char[] Trait; 		  // Pour qu'on puisse modifier l'etat du trait apres dans le deroulement 
	private int hintnbr = 0;
	private int indexWordGenerated;

	private List<String> Fruits = Arrays.asList(new String[]{"pomme","framboise","fraise","melon","orange","pistache","datte",
			"tomate","avocat","pistache","amande","ananas","citron","mandarine",
			"cerise","banane","clementine","mangue","abricot","pasteque"});
	private List<String> Legume = Arrays.asList(new String[]{"Courgette","Concombre","Haricot","Poireau","Tomate","Olive",
			"Champignon","Brocoli","Avocat","Epinard","Oignon","Poivron","Potiron"});
	private List<String> Capitale = Arrays.asList(new String[] {"Amsterdam","Washington","Bruxelles","Colombo","Londres",
			"Luxembourg","Madrid","Monaco","Moscou","Singapour","Stockholm"});
	private List<String> Pays = Arrays.asList(new String[] {"Afghanistan","Argentine","Bangladesh","Cameroun","Colombie",
				"Irlande","Morocco","Espagne","Egypte","Danemark","Thailande","Singapour","Madagascar","Luxembourg","Kazakhstan","Australie"	
	});
	private List<String> Metier = Arrays.asList(new String[] {"Agriculteur","Agronome","Ambulancier","Animateur","Approvisionneur","Arbitre",
					"Avocat","Banquier","Biochimiste","Biologiste","Camionneur","Chanteur","Chasseur","Chirurgien","Coiffeur","Compositeur","Astronaute"});
	
	private Map<Integer,List<String>> Words = new HashMap<Integer,List<String>>();
	
	/*
	 *  Apres le choix de l'utilisateur on initialise cette liste juste avec
	 *  la liste des mots de type choisi
	 */
	private List<String> ListChoisie;
	public Jeu() {
		/*
		 * On ajoute les listes des fruits,legumes a note HashMap Words
		 *  Cle 1 Contient la liste des fruits
		 *  Cle 2 Contient la liste des Legumes
		 */
		Words.put(1,Fruits);
		Words.put(2,Legume);
		Words.put(3, Capitale);
		Words.put(4, Pays);
		Words.put(5, Metier);
		this.nbrEssaiErrone = 0;
	}
	
	/*
	 * 		D'apres le choix de l'utilisateur de type des mots ,
	 * 		On doit generer le mot convenable d'apres la liste des mots de type choisi
	 */
	public void setData(int key) {
		// Word.get return la liste des mots a qui appartient la cle
		this.ListChoisie = Words.get(key);
		this.indexWordGenerated = (int) (Math.random()*ListChoisie.size());
		// On retourne l'element qui se trouve a cet indice
		this.WordGenerated = ListChoisie.get(this.indexWordGenerated);
		
	}
	
	
	/*
	 * Fonction qui permet de transferer un tableau des caractères minuscule
	 * 		en Majuscule
	 * 
	 *  Utilisation : ( Lorsqu'on va generer les caracteres brouilles ( e f g a c e h )
	 *  						  On doit les afficher en Majuscule  ( E F G A C E H )
	 */
	public char[] LowerToUpper(char[] LowerWord) {
		char[] UpperCase = new char[LowerWord.length];
		for (int i=0;i<LowerWord.length;i++) {
			UpperCase[i] = Character.toUpperCase(LowerWord[i]);
		}
		return UpperCase;
	}
	
	/*
	 *  Fonction qui permet de generer les caractères brouiller du mot
	 *  Pomme -> A D E O P Q M M 
	 */
	public char[] GenerateChar() {
		String Mot  = this.WordGenerated;
		char[] FinalString = new char[Mot.length() + 5];
		char[] MotChar = Mot.toCharArray();
	
		for (int i=0;i<Mot.length();i++) {
			FinalString[i] = MotChar[i];
		}
		
		// Adding 5 more Letters to the final String
		for (int i=Mot.length(), k = 0 ; i < Mot.length() + 5 ; i++,k++) {
			FinalString[i] = (char) (FinalString[k] + 2);
		} 
		// Trier le tableau des caracteres
		Arrays.sort(FinalString);
		// Transformer les cars tableau en Majuscule
		FinalString = LowerToUpper(FinalString);
		return FinalString;
	}
	

	/*
	 * 	Lors de l'appel d'un hint on place un caractere automatique pour l'utilisateur
	 */
	public void UseHint() {
		for (int i=0;i<Trait.length;i++) {
			if (this.Trait[i] == '_') {
				this.Trait[i] = Character.toUpperCase(WordGenerated.charAt(i));
				break;
			}
		} 
		hintnbr++;
		this.nbrEssaiErrone++;
	}
	
	/*
	 * 		Pour avoir l'index du caractere entrée (Input) qui vérifie les conditions :
	 * 		- Il se trouve bien dans le mot 
	 * 		- Il n'est pas deja present dans le tableau des trais
	 */
	public int getIndexChar(char Input) {
		char[] Word = WordGenerated.toCharArray();
		for (int i=0;i<Word.length;i++) {
				// p == p || P == p->P && trait[i] == '_' 
			if ((Word[i] == Input || Word[i] == Character.toUpperCase(Input)) && this.Trait[i] == '_') {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * Modifier les Trait avec le nouveau caractere entré
	 * 	- Si le caractère appartient au mot On le place dans son propre indice , 
	 * 		On retourne true
	 * 	- Sinon on incremente le nombre d'essai errone entré
	 * 		On retourne false
	 */
	public boolean Trait(char Input) {
		int indexChar = getIndexChar(Input);
		if (indexChar >= 0) 
		{
				// To Upper Case juste pour afficher les caractere en Maj
			if (Character.isLowerCase(Input)) {				
				this.Trait[indexChar]  = Character.toUpperCase(Input);
			} else {
				this.Trait[indexChar] = Input;
			}
			return true;
		}
		else {			
			nbrEssaiErrone++;		
		}
		return false;
	}
	
	
	/*
	 * 	Calcul de nombre de trait restant vide
	 */
	public int Traitleftnbr() {
		int compteur = 0;
		for (int i=0;i<Trait.length;i++) {
			if (Trait[i] == '_') {
				compteur++;
			}
		}
		return compteur;
	}
	
	/*
	 * 	Dans le debut de jeu on doit initialiser le tableau des traits par des tirés
	 */
	public char[] InitTrait() {
		char[] trait = new char[WordGenerated.length()];
		for (int i=0;i<trait.length;i++) trait[i] = '_';
		return trait;
	}
	


	
	/*
	 * Affichage l'etat du trait
	 */
	public void DisplayTrait() {
		for (int i=0;i<Trait.length;i++) {
			System.out.print(Trait[i] + " ");
		}
		System.out.println();
	}
	
	/*
	 * Verficiation S'il existe un trait vide return false sinon true
	 */
	public boolean NoTraitLeft() {
		for (int i=0;i< Trait.length;i++) {
			//Existe encore des traits
			if (Trait[i] == '_') return false;
		}
		//No Trait left
		return true;
	}
	public void DisplayEssaiLeft() {
		System.out.println("Nombre d'essais restant : " + (MaxEssai - nbrEssaiErrone));
	}
	
	
	/*
	 * 			GETTERS AND SETTERS		
	 */
	public int getnbrEssaiErrone() {
		return nbrEssaiErrone;
	}
	public void setnbrEssaiErrone(int nbrEssaiErrone) {
		this.nbrEssaiErrone = nbrEssaiErrone;
	}
	public int getMaxEssai() {
		return MaxEssai;
	}
	public String getWordGenerated() {
		return WordGenerated;
	}
	public void setWordGenerated(String wordGenerated) {
		WordGenerated = wordGenerated;
	}
	public int getHintnbr() {
		return hintnbr;
	}

	public char[] getTrait() {
		return Trait;
	}

	public void setTrait(char[] trait) {
		Trait = trait;
	}
}
