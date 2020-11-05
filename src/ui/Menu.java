package ui;




import java.util.Scanner;
import model.Software;
import model.Player;




public class Menu {

	private Scanner sc;
	final static int EXIT_MENU = 3;
	Software sw = new Software();
	int mirrorsLeft;
	
	
	public Menu() {
		
		this.sw = startGame();
	}
	

	
	
	
	private String getMenu() {
		String menu;
		menu = "============================\n";
		menu = "*****Welcome******";
		menu = "============================\n";
		menu += "*****Laser Game*****\n";
		menu += "===========================\n";
		menu += "1. Start a new game\n";
		menu += "2. Show Scores\n";
		menu += "3. Exit Game\n";
		
		return menu;
	}
	private void executeOperation(int option) {
		switch (option) {
		case 1:
			System.out.println("******** LASER GAME ********");
			System.out.println("Do your best!");
			playGame();
			break;
		case 2:
            showPositions();
			break;
		case 3:
			exitProgram();
			break;
		default:
			System.out.println("Select a correct option");
			break;
		}
	}

	private void playGame() {
		System.out.println("You will start with 10 points");
		System.out.println("Every time you fire or guess, you will lost 1 point, as it will be count as a try, unless you guess the mirror position and inclination at first try");
		System.out.println("If you guess the mirror position correctly but not its inclination, you will lost 1 point");
		System.out.println("If you guess the mirror position and its inclination correctly, you will gain 10 points");
		System.out.println("If you fail both, you will lost 2 points\n");
		System.out.println("Please enter the Nickname, Number of columns, cumber of rows and number of mirrors in a line");
		String line = sc.nextLine();
		String [] parts = line.split(" ");
		String nickName = (parts[0]);
		int n = Integer.parseInt(parts[1]);
		int m = Integer.parseInt(parts[2]);
		int k = Integer.parseInt(parts[3]);
		mirrorsLeft = k;
		sw.toAddLinkedMatrix(m, n);
		
		
		
		if(k <= m*n) {
			sw.generateRandomMirrors(k, n, m);
			shootOption(false, m, n, nickName, k);
			
		} else {
			System.out.println("Mirrors must be minors than the matrix size");
		}
	}

	public void shootOption(boolean stop,int m, int n, String nickName, int k) {
		stop = false;
	
			
		
		if(mirrorsLeft == 0) {
			  
			System.out.println("You win, you are pro" + " Score: " + sw.getScore() + " total tries: " + sw.getTries());
			Player newUser = new Player(nickName, sw.getScore());
			sw.addPlayer(newUser);
			
			 stop = true;
			
			
			 recursiveMenu(1, getMenu());
			  
			}else {
		    System.out.println(sw.getLinkedM());
			System.out.println(nickName+": "+mirrorsLeft+" mirrors remaining" + " current Score: " +sw.getScore() + " current tries " +sw.getTries());
			System.out.println("Type menu to exit, L to locate or coordinate to fire");
			
			}
		
		String fire = sc.nextLine();
		String [] fParts = fire.split("");
		if(fire.equalsIgnoreCase("Menu") || stop == true ) {
			
			
			stop = true;
			Player newUser = new Player(nickName, sw.getScore());
			sw.addPlayer(newUser);
			System.out.println(nickName+": "+mirrorsLeft+"mirrors remaining" +" Score: "+ sw.getScore() + " total tries: " +sw.getTries());
			
			

		} else if(fParts[0].equalsIgnoreCase("L")){
			boolean mirrorFound = false;
			int rowFire = Integer.parseInt(fParts[1]);
			char colFireChar = fParts[2].charAt(0);
			int colFire = colFireChar;
			int colToFire = (char)(colFire-'A');
			String directorMirror = (fParts[3]);
			if(sw.toLocateMirror(rowFire-1, colToFire, directorMirror) == false) {
				System.out.println(sw.getLinkedM());
				sw.toFindPosition(sw.getLinkedM().getFirst(), rowFire-1, colToFire).setValue("");
			} else {
				mirrorFound = true;
				
			}
			if(mirrorFound == true) {
				mirrorsLeft--;
				System.out.println(sw.getLinkedM());
			}
			shootOption(stop, m, n,  nickName, k);

		} else {
			int rowFire = Integer.parseInt(fParts[0]);
			char colFireChar = fParts[1].charAt(0);
			int colFire = colFireChar;
			int colToFire = (char)(colFire-'A');
			String directorFire = "";
			if(fParts.length == 3) {
				directorFire = (fParts[2]);
			}
			if(rowFire-1 < m && colToFire < n) {
				if(sw.toShoot(rowFire-1, colToFire, directorFire) == false) {
					System.out.println("Fire can not be executed");
				} else {
					System.out.println("Fired!!");
					System.out.println(sw.getLinkedM());
					sw.verifyStateAtEnd(sw.toFindPosition(sw.getLinkedM().getFirst(), rowFire-1, colToFire), sw.getExitNode());
				}
			} else {
			
				System.out.println("Coordinates out of matrix!!");
			}
	
			shootOption(stop, m, n, nickName, k);
		}
	}
	public void recursiveMenu(int option, String menu) {
        if(option == EXIT_MENU) {
           
        } else {
            System.out.println(menu);
            option = readOption();
            executeOperation(option);
            recursiveMenu(option, menu);
        }
    }
	
	public void startMenu() {
        String menu = getMenu();
        int option = 0;
        recursiveMenu(option, menu);
    }
	
	private void exitProgram() {
		
		sc.close();
		System.out.println("Good Bye");
		
		
	}
	
	public Software startGame() {
		
		
		sc = new Scanner(System.in);
		return sw;
	}
	
	public int readOption() {
		
		int option;
		 option = Integer.parseInt(sc.nextLine());
		
		return option;
	}
	
	public void showPositions() {
		System.out.println("\n"+sw.showScore());
	}
	
	
	
	
	
	
	
	
	
}
