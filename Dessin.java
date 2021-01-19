
public class Dessin {
	public static int count = 0;
	public static void draw(){
        count++;
        int choix = count;
        switch(choix){
            case 1:
            	System.out.println(" ____");
            	System.out.print("|");
            	System.out.println("/");
            	for (int i=0;i<4;i++) System.out.println("|");
            	System.out.println("-");
            	System.out.println("********************");
            	break;
            case 2:
              System.out.println(" ____");
            	System.out.print("|");
            	System.out.print("/");
            	System.out.println("  |");
            	for (int i=0;i<4;i++) System.out.println("|");
            	System.out.println("-");
            	System.out.println("********************");
            	break;
            case 3:
            	 System.out.println(" ____");
             	System.out.print("|");
             	System.out.print("/");
             	System.out.println("  |");
             	System.out.print("|");
         		System.out.println("   O");
             	for (int i=0;i<3;i++)  	System.out.println("|");            	
             	System.out.println("-");
             	System.out.println("********************");
             	break;
            case 4:
           	 System.out.println(" ____");
            	System.out.print("|");
            	System.out.print("/");
            	System.out.println("  |");
            	System.out.print("|");
        		System.out.println("   O");
        		System.out.print("|");
        		System.out.println("  /|\\");
            	for (int i=0;i<2;i++)  	System.out.println("|");            	
            	System.out.println("-");
            	System.out.println("********************");
            	break;
            case 5:
              	 System.out.println(" ____");
               	System.out.print("|");
               	System.out.print("/");
               	System.out.println("  |");
               	System.out.print("|");
           		System.out.println("   O");
           		System.out.print("|");
           		System.out.println("  /|\\");
           		System.out.print("|");
           		System.out.println("  / \\");
               	for (int i=0;i<1;i++)  	System.out.println("|");            	
               	System.out.println("-");
               	System.out.println("********************");
               	break;
        }
	}
}
