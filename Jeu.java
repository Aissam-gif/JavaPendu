import java.util.Arrays;

import java.lang.Math;
public class Jeu {
	private int nbrEssaiErrone; 
	private final int MaxEssai; //nbr total des essais
	private String[] Words = {"pomme","framboise","fraise","melon","orange","pistache","datte",
			"tomate","avocat","pistache","amande","noix","kaki",
			"lime","banane","clementine","kiwi","mangue","abricot","pasteque"};
	private String WordGenerated; // Stockage mot générer de la liste
	private char[] Trait; 		  // Modifier l'etat du trait 
	private int hintnbr = 1;
	private int indexWordGenerated;
	public Jeu() {
		/*	 
		 *   A la création du jeu on génere Un mot au hasard
		 */  
		this.indexWordGenerated = (int) (Math.random()*(Words.length));
		this.WordGenerated = Words[indexWordGenerated];
		this.MaxEssai = 5;
		this.nbrEssaiErrone = 0;
	}
	
	
	/*
	 * Fonction qui permet de transferer un tableau des caractères minuscule
	 * 		en Majuscule
	 */
	public char[] LowerToUpper(char[] LowerWord) {
		char[] UpperCase = new char[LowerWord.length];
		for (int i=0;i<LowerWord.length;i++) {
			UpperCase[i] = Character.toUpperCase(LowerWord[i]);
		}
		return UpperCase;
	}
	
	/*
	 * Génerer les caractères du mot brouiller
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
		// Sorting Char array
		Arrays.sort(FinalString);
		// Get The Sorted Letters to Upper Case
		FinalString = LowerToUpper(FinalString);
		return FinalString;
	}
	
	
	/*
	 * Modifier les Trait avec le nouveau caractere entré
	 * 	- Si le caractère appartient au mot On le place dans son propre indice , 
	 * 		On retourne true
	 * 	- Sinon on incremente le nombre d'essai errone entré
	 * 		On retourne false
	 */
	public boolean Trait(char Input) {
		if (CharAppartientMot(Input, WordGenerated, Trait)) 
		{
			int indexChar = getIndexChar(Input, WordGenerated, Trait);
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
	 * 	Lors de l'appel d'un hint on place un caractere automatique pour l'utilisateur
	 */
	public void UseHint() {
		for (int i=0;i<Trait.length;i++) {
			if (Trait[i] == '_') {
				this.Trait[i] = Character.toUpperCase(WordGenerated.charAt(i));
				break;
			}
		} 
		hintnbr++;
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
	 * 	Verficiation Si le caractere entré (Input) se trouve bien dans le mot et
	 * 		il n'est pas déja present dans le tableau des traits
	 */
	public boolean CharAppartientMot(char Input,String WordGenerated,char[] trait) {
		char[] Word = WordGenerated.toCharArray();
		for (int i=0;i<Word.length;i++) {
			if ( (Word[i] == Input || Word[i] == Character.toLowerCase(Input)) && trait[i] == '_') {
				return true;
		}
	}
		return false;
}
	/*
	 * 		Pour avoir index d'un caractere entrée qui vérifie les conditions 
	 * 		sité au dessus
	 */
	public int getIndexChar(char Input,String WordGenerated,char[] trait) {
		char[] Word = WordGenerated.toCharArray();
		for (int i=0;i<Word.length;i++) {
			if ((Word[i] == Input || Word[i] == Character.toLowerCase(Input)) && trait[i] == '_') return i;
		}
		return -1;
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
