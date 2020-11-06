package ui;




import java.util.Scanner;
import model.Software;
import model.Player;




public class Menu {

	private Scanner sc;
	final static int EXIT_MENU = 3;
	Software sw = new Software();
	int mirrorsLeft;

	/**
	 * This method is the constructor of Menu
	 */
	public Menu() {

		this.sw = startGame();
	}




	/**
	 * This method shows the Menu
	 * @return the menu
	 */
	private String getMenu() {
		String menu;
		menu = "============================\n";
		menu = "*****Welcome to******";
		menu = "============================\n";
		menu += "*****Laser Game*****\n";
		menu += "===========================\n";
		menu += "1. Start a new game\n";
		menu += "2. Show Scores\n";
		menu += "3. Exit Game\n";

		return menu;
	}
	
	/**
	 * This method receives the user petition and executes its respective associated method
	 * @param option is the user petition
	 */
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
			System.out.println("Please select a correct option!");
			break;
		}
	}
  
	/**
	 * This method display the score instructions to the user, it also receive the basic data to start the game
	 */
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
        sw.setTries(0);


		if(k <= m*n && n <= 26) {
			sw.generateRandomMirrors(k, n, m);
			shootOption(false, m, n, nickName, k);

		} else if(k> m*n || n>26) {

			if(k> m*n && n>26) {
				System.out.println("\nerror, please remember that mirrors must be minors than the matrix size");
                System.out.println("error, columns can't be more than 26");
			}else if(k>m*n) {
				System.out.println("\nerror, please remember that mirrors must be minors than the matrix size");
				
			}else if(n>26) {
				System.out.println("\nerror, columns can't be more than 26");
				
			}


		}
	}
	/**
	 * This method execute the game
	 * @param stop is the condition to continue the game
	 * @param m is the number of rows of the matrix
	 * @param n is the number of columns of the matrix
	 * @param nickName is the player nickname
	 * @param k is the number of mirrors
	 */

	public void shootOption(boolean stop,int m, int n, String nickName, int k) {
		stop = false;



		if(mirrorsLeft == 0) {

			System.out.println("You win, you are pro player" + " Score: " + sw.getScore() + " total tries: " + sw.getTries());
			Player newUser = new Player(nickName, sw.getScore(),n,m,k,n*m, sw.getTries());
			sw.addPlayer(newUser);

			stop = true;

		}else {
			System.out.println(sw.getLinkedM());
			System.out.println(nickName+": "+mirrorsLeft+" mirrors remaining" + " current Score: " +sw.getScore() + " current tries: " +sw.getTries());
			System.out.println("Type menu to exit, L to locate or coordinate to fire");

			String fire = sc.nextLine();


			if(fire.equalsIgnoreCase("Menu") || stop == true ) {


				stop = true;
				Player newUser = new Player(nickName, sw.getScore(),n,m,k,n*m, sw.getTries());
				sw.addPlayer(newUser);
				System.out.println(nickName+": "+mirrorsLeft+" mirrors remaining" +" Score: "+ sw.getScore() + " total tries: " +sw.getTries());



			} else if(String.valueOf(fire.charAt(0)).equalsIgnoreCase("L")){
				boolean mirrorFound = false;
				String directorMirror = String.valueOf(fire.charAt(fire.length()-1));
				char coL = fire.charAt(fire.length()-2);
				int colL = coL;
				int colToLocate = (char)(colL-'A');
				String rowL = fire.substring(1,fire.length()-2);
				int rowToLocate = Integer.parseInt(rowL);

				if(sw.toLocateMirror(rowToLocate-1, colToLocate, directorMirror) == false) {
					System.out.println(sw.getLinkedM());
					sw.toFindPosition(sw.getLinkedM().getFirst(), rowToLocate-1, colToLocate).setValue("");
				} else {
					mirrorFound = true;

				}
				if(mirrorFound == true) {
					mirrorsLeft--;
					System.out.println(sw.getLinkedM());
				}
				shootOption(stop, m, n,  nickName, k);

			} else {
				String rowF;
				int rowFire = 0;
				char colFireChar;
				int colFire;
				int colToFire= 0;
				String directorFire = "";
				if(Character.isLetter(fire.charAt(fire.length()-2))) {
					directorFire = String.valueOf(fire.charAt(fire.length()-1));
					rowF = fire.substring(0 , fire.length()-2);
					rowFire = Integer.parseInt(rowF);
					colFireChar = fire.charAt(fire.length()-2);
					colFire = colFireChar;
					colToFire = (char)(colFire - 'A');

				}else {
					rowF = fire.substring(0 , fire.length()-1);
					rowFire = Integer.parseInt(rowF);
					colFireChar = fire.charAt(fire.length()-1);
					colFire = colFireChar;
					colToFire = (char)(colFire - 'A');
				}
				if(rowFire-1 < m && colToFire < n) {
					if(sw.toShoot(rowFire-1, colToFire, directorFire) == false) {
						System.out.println("laser can not be fired");
					} else {
						System.out.println("Laser's been fired!!");
						System.out.println(sw.getLinkedM());
						sw.verifyStateAtEnd(sw.toFindPosition(sw.getLinkedM().getFirst(), rowFire-1, colToFire), sw.getExitNode());
					}
				} else {

					System.out.println("please type a valid option, coordinates are out of matrix!!");
				}

				shootOption(stop, m, n, nickName, k);
			}
		}
	}

	/**
	 * This method displays the menu depending on user choice
	 * @param option is the option the user selects
	 * @param menu 
	 */
	public void recursiveMenu(int option, String menu) {
		if(option == EXIT_MENU) {

		} else {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);
			recursiveMenu(option, menu);
		}
	}
	
	/**
	 * This method starts the menu
	 */

	public void startMenu() {
		String menu = getMenu();
		int option = 0;
		recursiveMenu(option, menu);
	}
   
	
	/**
	 * This method close the scanner
	 */
	private void exitProgram() {

		sc.close();
		System.out.println("Thanks for playing, let's do even better next time! ");


	}
	
	/**
	 * This method instance the Scanner 
	 * @return software object
	 */

	public Software startGame() {


		sc = new Scanner(System.in);
		return sw;
	}
    
	/**
	 * This method receives the petition of the user
	 * @return the petition
	 */
	public int readOption() {

		int option;
		option = Integer.parseInt(sc.nextLine());

		return option;
	}
	/**
	 * This method calls the recursive method of Software in charge of displaying the scores
	 */

	public void showPositions() {
		System.out.println("\n"+sw.showScore());
	}









}
