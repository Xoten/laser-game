package ui;


import java.util.Scanner;
import model.Software;

public class Menu {

	private Scanner sc;
	final static int EXIT_MENU = 3;
	Software sw = new Software();
	
	public Menu() {
		
		this.sw = startGame();
	}
	

	
	
	
	private String getMenu() {
		String menu;
		menu = "============================\n";
		menu = "===========Welcome User==========\n";
		menu = "============================\n";
		menu += "=====Laser Game====\n";
		menu += "===========================\n";
		menu += "1. Play\n";
		menu += "2. Show Scores\n";
		menu += "3. Finish Game\n";
		
		return menu;
	}
	private void executeOperation(int option) {
		switch (option) {
		case 1:
			System.out.println("~~~~~~~~~~ PLAYING ~~~~~~~~~~");
			playGame();
			break;
		case 2:

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
		System.out.println("Please enter the Nickname, Number of columns, cumber of rows and number of mirrors in a line");
		String line = sc.nextLine();
		String [] parts = line.split(" ");
		String nickName = (parts[0]);
		int n = Integer.parseInt(parts[1]);
		int m = Integer.parseInt(parts[2]);
		int k = Integer.parseInt(parts[3]);
		sw.toAddLinkedMatrix(m, n);
		if(k <= m*n) {
			sw.generateRandomMirrors(m, n, k);
			shootOption(false, m, n, 1, nickName, k);
		} else {
			System.out.println("Mirrors must be minors than the matrix size");
		}
	}

	public void shootOption(boolean stop,int m, int n, int count, String nickName, int k) {
		stop = false;
		if (count > 0) {
			System.out.println("--------- LASER MATRIX ---------");
			System.out.println(nickName+": "+k+"mirrors remaining");
			System.out.println(sw.getLinkedM());
			System.out.println("Type menu to exit, L to locate or coordinate to fire");
		} else {
			System.out.println(nickName+": "+k+"mirrors remaining");
			System.out.println("Type menu to exit, L to locate or coordinate to fire");
		}
		String fire = sc.nextLine();
		String [] fParts = fire.split("");
		if(fire.equalsIgnoreCase("Menu") || stop == true) {
			//exit
			stop = true;

		} else if(fParts[0].equalsIgnoreCase("L")){
			boolean mirrorFound = false;
			int kRest = 0;
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
				kRest = 1;
			}
			System.out.println(sw.getLinkedM());
			shootOption(stop, m, n, count-1, nickName, k-kRest);

		} else {
			//*********** Fire ****************************
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
					sw.toFindPosition(sw.getLinkedM().getFirst(), rowFire-1, colToFire).setValue("");
					sw.getExitNode().setValue("");
				}
			} else {
				System.out.println("Coordinates out of matrix!!");
			}
			System.out.println("--------- LASER MATRIX ---------");
			System.out.println(nickName+": "+k+"mirrors remaining");
			System.out.println(sw.getLinkedM());
			shootOption(stop, m, n, count-1, nickName, k);
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
	
	
	
	
	
	
	
	
	
	
	
}
