
public class Main {

	public static void main(String[] args) {
			
		Jeu Game = new Jeu();
		Deroulement Der = new Deroulement(Game);
		Menu.menu(Der,Game);
		//Der.start();
	
	}

}
