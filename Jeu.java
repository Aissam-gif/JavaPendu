import java.util.Arrays;



import java.lang.Math;
public class Jeu {
	private int nbrEssaiErrone; 
	private final int MaxEssai; //nbr total des essais
	private String[] Words = {"pomme","framboise","fraise","melon","orange","pistache","datte",
			"tomate","avocat","pistache","amande","noix","kaki",
			"lime","banane","clementine","kiwi","mangue","abricot","pasteque"};
	private String WordGenerated; //Stockage mot générer de la liste
	private char[] Trait;
	private int hintnbr = 1;
	private int indexWordGenerated;
	public Jeu() {
		/*
		 * A la création du jeu on doit génerer Un mot au hasard
		 */
		this.indexWordGenerated = (int) (Math.random()*(Words.length));
		this.WordGenerated = Words[indexWordGenerated];
		this.MaxEssai = 5;
		this.nbrEssaiErrone = 0;
	}
	
	public int getnbrEssaiErrone() {
		return nbrEssaiErrone;
	}
	public void setnbrEssaiErrone(int nbrEssaiErrone) {
		this.nbrEssaiErrone = nbrEssaiErrone;
	}
	public int getMaxEssai() {
		return MaxEssai;
	}
	// Index du mot pris au hasard
	public int IndexMotHasard() {
		int index = (int) (Math.random()*(Words.length));
		this.setWordGenerated(Words[index]);
		return index; 
		
	}
	public String getWordGenerated() {
		return WordGenerated;
	}

	public void setWordGenerated(String wordGenerated) {
		WordGenerated = wordGenerated;
	}

	// Change Word from lower to upper case
	public char[] LowerToUpper(char[] LowerWord) {
		char[] UpperCase = new char[LowerWord.length];
		for (int i=0;i<LowerWord.length;i++) {
			UpperCase[i] = Character.toUpperCase(LowerWord[i]);
		}
		return UpperCase;
	}
	/*
	 * Génerer les caractères du mot brouiller
	 */
	public char[] GenerateChar() {
		String Mot  = Words[indexWordGenerated];
		/*
		String Mot  = Words[IndexMotHasard()];
		setWordGenerated(Mot); */
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
		 *  Affichage Des Traits
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
			//On prend un mot aleatoire et on le place
			nbrEssaiErrone++;
			/*
			for (int i=0;i<Trait.length;i++) {
				if (Trait[i] == '_') {
					this.Trait[i] = Character.toUpperCase(WordGenerated.charAt(i));
					break;
				}
			} */
		}
		return false;
	}
	public void UseHint() {
		//nbrEssaiErrone++;
		
		for (int i=0;i<Trait.length;i++) {
			if (Trait[i] == '_') {
				this.Trait[i] = Character.toUpperCase(WordGenerated.charAt(i));
				break;
			}
		} 
		hintnbr++;
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
	public int Traitleftnbr() {
		int compteur = 0;
		for (int i=0;i<Trait.length;i++) {
			if (Trait[i] == '_') {
				compteur++;
			}
		}
		return compteur;
	}
	//Initialiser Trait
	public char[] InitTrait() {
		char[] trait = new char[WordGenerated.length()];
		for (int i=0;i<trait.length;i++) trait[i] = '_';
		return trait;
	}
	//Test Si le Caractère se trouve dans le Mot
	public boolean CharAppartientMot(char Input,String WordGenerated,char[] trait) {
		char[] Word = WordGenerated.toCharArray();
		for (int i=0;i<WordGenerated.length();i++) {
			if ( (Word[i] == Input || Word[i] == Character.toLowerCase(Input)) && trait[i] == '_') {
				return true;
		}
	}
		return false;
}
	public int getIndexChar(char Input,String WordGenerated,char[] trait) {
		char[] Word = WordGenerated.toCharArray();
		for (int i=0;i<Word.length;i++) {
			if ((Word[i] == Input || Word[i] == Character.toLowerCase(Input)) && trait[i] == '_') return i;
		}
		return -1;
	}
}
