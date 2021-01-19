
/*
 * 		AISSAM BOUSSOUFIANE
 * 		BOUMAZZOURH	MARYAM
 *		EL KORTAS	WALID
 *		BOUTALEB	ZENEB
 *		ELMOUKTAFI	ABDELALI
 */

public class Main {
	public static void main(String[] args) {
		
		Jeu Game = new Jeu();
		Deroulement Der = new Deroulement(Game);
		Menu.menuTheme(Der,Game);
		
		
	}

}
