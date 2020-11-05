package model;


public class Software {

	private Node exitNode;
	LinkedMatrix LinkedM;
	Player root;
	int score;
	int tries;



	public Software() {

	}

	public Node getExitNode() {
		return exitNode;
	}

	public LinkedMatrix getLinkedM() {
		return LinkedM;
	}

	/**
	 * This method set values to start and exit node
	 * @param rowShoot is the row where the user wants to shoot
	 * @param colShoot is the column where the user wants to shoot
	 * @param orientation is the orientation of the shot, it will be "" if the user is not firing at the corners
	 * @return isFound, true if the start node exists, false if not
	 */
	public boolean toShoot(int rowShoot, int colShoot , String orientation ) {

		boolean isFound = !false;
		String StartDirection = "";
		String StartAtCorner = "";


		Node toStart = toFindPosition(LinkedM.getFirst(), rowShoot, colShoot );

		if(toStart != null) {


			toStart.setValue("S");
			if(!orientation.equalsIgnoreCase("")) {

				StartAtCorner = toDetermineInitialDirectionatCorner(orientation, toStart);
				exitNode = Shoot(toStart, StartAtCorner);


			}else {

				StartDirection = toDetermineInitialDirection(toStart);
				exitNode = Shoot(toStart, StartDirection);
			}

			exitNode.setValue("E");
			isFound = true;
		} else {

			isFound = false;
		}

		tries++;
		return isFound; 

	}


	/**
	 * This method determines the initial direction that the laser goes when the user its shooting from the borders but not the corners
	 * @param ToLocateStart is the node where the user stars firing.
	 * @return the initial Direction that the laser goes.
	 */


	public String toDetermineInitialDirection(Node ToLocateStart) {

		String InitialD = "";
		if(ToLocateStart.getUp() == null) {

			if(!ToLocateStart.getMirror().equals("")) {

				if(ToLocateStart.getMirror().equals("/")) {

					InitialD = "left";
				}else {

					InitialD = "right";
				}


			}else {

				InitialD = "down";
			}

		}else if(ToLocateStart.getDown() == null) {
			if(!ToLocateStart.getMirror().equals("")) {

				if(ToLocateStart.getMirror().equals("/")) {

					InitialD = "right";

				}else {

					InitialD = "left";
				}


			}else {

				InitialD = "up";
			}

		}else if(ToLocateStart.getPrev() == null) {
			if(!ToLocateStart.getMirror().equals("")) {

				if(ToLocateStart.getMirror().equals("/")) {

					InitialD = "up";
				}else {

					InitialD = "down";
				}

			}else {

				InitialD = "right";
			}

		}else if(ToLocateStart.getNext() == null) {

			if(!ToLocateStart.getMirror().equals("")) {

				if(ToLocateStart.getMirror().equals("/")) {
					InitialD = "down";
				}else {
					InitialD = "up";
				}

			}else {
				InitialD = "left";
			}
		}
		return InitialD;
	}

	/**
	 * This method determine the initial direction when the user is firing on a corner, using the orientation and the node where the user starts firing
	 * @param orientation that must be horizontal or vertical
	 * @param ToLocateStart is the node where the user starts firing.
	 * @return the initial direction that the laser takes.
	 */

	public String toDetermineInitialDirectionatCorner(String orientation, Node ToLocateStart) {

		String Direction = "";

		if(ToLocateStart.getUp() == null ) {


			if(orientation.equalsIgnoreCase("H") && ToLocateStart.getNext() == null ) {

				if(!ToLocateStart.getMirror().equals("")) {

					if(ToLocateStart.getMirror().equals("/")) {

						Direction = "down";
					}else {

						Direction = "up";
					}
				}else {

					Direction = "left";
				}

			}else if(orientation.equalsIgnoreCase("H") && ToLocateStart.getPrev() == null ) {

				if(!ToLocateStart.getMirror().equals("")){

					if(ToLocateStart.getMirror().equals("/")){

						Direction = "up";
					}else {

						Direction = "down";
					}

				}else {

					Direction = "right";
				}


			}if(orientation.equalsIgnoreCase("V")){


				if(ToLocateStart.getMirror().equals("")) {


					Direction = "down";
				}else if(ToLocateStart.getMirror().equals("/")) {


					Direction = "left";
				}else {

					Direction = "right";
				}
			}
		}

		if(ToLocateStart.getDown() == null) {

			if(orientation.equalsIgnoreCase("H") && ToLocateStart.getNext() == null ) {

				if(!ToLocateStart.getMirror().equals("")) {

					if(ToLocateStart.getMirror().equals("/")) {

						Direction = "down";
					}else {
						Direction = "up";
					}
				}else {

					Direction = "left";
				}

			}else if(orientation.equalsIgnoreCase("H") && ToLocateStart.getPrev() == null ) {

				if(!ToLocateStart.getMirror().equals("")){

					if(ToLocateStart.getMirror().equals("/")){


						Direction = "up";
					}else {
						Direction = "down";
					}

				}else {

					Direction = "right";
				}

			}if(orientation.equalsIgnoreCase("V")){


				if(ToLocateStart.getMirror().equals("")) {


					Direction = "up";
				}else if(ToLocateStart.getMirror().equals("/")) {


					Direction = "right";
				}else {

					Direction = "left";
				}
			}
		}

		return Direction;

	}

	/**
	 * This method obtains the node where the laser goes out of the matrix.
	 * @param ShootEnd is the current position of the laser, at first its the start node
	 * @param initialWay is the initial course that the laser goes
	 * @return the end node
	 */


	public Node Shoot(Node ShootEnd, String initialWay) {

		if(initialWay.equalsIgnoreCase("down")) {

			if(ShootEnd.getDown() != null) {

				if(ShootEnd.getDown().getMirror().equals("\\")) {


					initialWay = "right";
					ShootEnd = ShootEnd.getDown();
					return Shoot(ShootEnd, initialWay);
				}else if(ShootEnd.getDown().getMirror().equals("/")) {

					initialWay = "left";
					ShootEnd = ShootEnd.getDown();
					return Shoot(ShootEnd, initialWay);
				}else {



					ShootEnd = ShootEnd.getDown();
					return Shoot(ShootEnd, initialWay);
				}

			}

			return ShootEnd;

		}

		if(initialWay.equalsIgnoreCase("up")) {

			if(ShootEnd.getUp() != null) {

				if(ShootEnd.getUp().getMirror().equals("\\")) {


					initialWay = "left";
					ShootEnd = ShootEnd.getUp();
					return  Shoot(ShootEnd, initialWay);
				}else if(ShootEnd.getUp().getMirror().equals("/")) {

					initialWay = "right";
					ShootEnd = ShootEnd.getUp();
					return Shoot(ShootEnd, initialWay);
				}else {

					ShootEnd = ShootEnd.getUp();
					return Shoot(ShootEnd, initialWay);

				}
			}

			return ShootEnd;

		}
		if(initialWay.equalsIgnoreCase("Right")) {

			if(ShootEnd.getNext() != null) {

				if(ShootEnd.getNext().getMirror().equals("\\")) {


					initialWay = "down";
					ShootEnd = ShootEnd.getNext();
					return Shoot(ShootEnd, initialWay);
				}else if(ShootEnd.getNext().getMirror().equals("/")) {

					initialWay = "up";
					ShootEnd = ShootEnd.getNext();
					return Shoot(ShootEnd, initialWay);
				}else {

					ShootEnd = ShootEnd.getNext();
					return Shoot(ShootEnd, initialWay);

				}

			}

			return ShootEnd;

		}
		if(initialWay.equalsIgnoreCase("Left")) {

			if(ShootEnd.getPrev() != null) {

				if(ShootEnd.getPrev().getMirror().equals("\\")) {


					initialWay = "up";
					ShootEnd = ShootEnd.getPrev();
					return Shoot(ShootEnd, initialWay);
				}else if(ShootEnd.getPrev().getMirror().equals("/")) {

					initialWay = "down";
					ShootEnd = ShootEnd.getPrev();
					return Shoot(ShootEnd, initialWay);


				}else {


					ShootEnd = ShootEnd.getPrev();
					return Shoot(ShootEnd, initialWay);

				}
			}
		}
		return ShootEnd;
	}

	/**
	 * This methods search a node by its row number and column number, it calls the method that search in a row
	 * @param currentNode is the current node
	 * @param toSearchRow is the row where  the node i'm looking for its located
	 * @param toSearchColum is the column where the node  i'm looking for its located
	 * @return the node searched
	 */
	public Node toFindPosition(Node currentNode, int toSearchRow, int toSearchColum) {
		Node toSearch = null;
		if(currentNode!=null) {
			if(currentNode.getRow() == toSearchRow && currentNode.getCol() == toSearchColum) {
				return currentNode;
			}else {
				toSearch = goThroughRows(currentNode.getNext(),toSearchRow,toSearchColum);
			}
			if(toSearch==null) {
				toSearch = toFindPosition(currentNode.getDown(),toSearchRow,toSearchColum);
			}
		}
		return toSearch; 
	}


	/**
	 * This method search a node in a row by its row number and column number
	 * @param currentN is the current node
	 * @param toFindRow is the row where the node its located
	 * @param toFindColumn is the column where the node its located
	 * @return the searched node or the final node in a row.
	 */
	public Node goThroughRows(Node currentN, int toFindRow, int toFindColumn) {
		Node toSearch = null;
		if(currentN!=null) {
			if(currentN.getRow() == toFindRow && currentN.getCol() == toFindColumn) {
				return currentN;
			}else {
				toSearch = goThroughRows(currentN.getNext(), toFindRow, toFindColumn);
			}
		}
		return toSearch;
	}

	/**
	 * This method determine if the guess of the user is correct or not
	 * @param rowFire is the row of the node where the user thinks a mirror is situated
	 * @param ColFire is the column of the node where the user thinks a mirror is situated
	 * @param inclination is the inclination that the user supposes the mirror has
	 * @return true if the guess is correct, false if not
	 */
	public boolean toLocateMirror(int rowFire, int ColFire, String inclination) {

		boolean guessed;
		String mirrorinc = "";
		if(inclination.equalsIgnoreCase("L")) {

			mirrorinc = "\\";

		}else {

			mirrorinc = "/";
		}
		Node toLocate = toFindPosition(LinkedM.getFirst(), rowFire, ColFire);

		if(toLocate.getMirror() != null) {

			if(toLocate.getMirror().equalsIgnoreCase(mirrorinc)){	

				toLocate.setValue(toLocate.getMirror());	
				guessed = true;
				toLocate.setVisibility(true);
				score = score + 10;

				if(tries > 0) {
					tries++;
				}


			}else {
				toLocate.setValue("X");	
				guessed = false;
				score= score - 1;
				tries++;
			}

		}else {
			toLocate.setValue("X");
			guessed = false;
			score = score -2;
			tries++;
		}

		return guessed;


	}

	/**
	 * This method verify if the start node and the end node have been guessed already , if they have, mirrors are shown again, if some of them haven't, that node value is set as "" again
	 * @param StartToVerify is the node where the user starts firing
	 * @param endToVerify is the node where the laser goes out of the matrix
	 */

	public void verifyStateAtEnd(Node StartToVerify, Node endToVerify) {

		if(StartToVerify.getVisibility() == true ) {

			StartToVerify.setValue(StartToVerify.getMirror());

		}else {

			StartToVerify.setValue("");

		} if(endToVerify.getVisibility() == true){


			endToVerify.setValue(endToVerify.getMirror());

		}else {

			endToVerify.setValue("");
		}

	}

	/**
	 * This method generates mirroN quantity of mirrors randomly
	 * @param mirrorN is the amount of mirrors
	 * @param n is the amount of columns of the matrix
	 * @param m is the amount of rows of the matrix
	 */

	public void generateRandomMirrors(int mirrorN, int n, int m) {
		score = 10;
		tries = 0;
		if(mirrorN > 0) {
			int RandomM = (int) (Math.random() * m); 
			int RandomN = (int) (Math.random() * n);
			Node choosen = toFindPosition(LinkedM.getFirst(), RandomM, RandomN);
			if(choosen.getMirror().equals("")) {
				int random = (int)(Math.random()*2);
				if(random == 0) {
					choosen.setMirror("/");
				}
				if(random == 1) {
					choosen.setMirror("\\");
				}
				generateRandomMirrors(mirrorN-1, n , m);
			}else {
				generateRandomMirrors(mirrorN, n, m);
			}

		}

	}

	/**
	 * This method create a linked matrix with m rows and n columns
	 * @param m is the amount of rows of the matrix to be created
	 * @param n is the amount of columns of the matrix to be created
	 */
	
	/**
	 * This method create a Linked Matrix with m rows and n columns
	 * @param m is the number of rows to create
	 * @param n is the number of columns of the to create
	 */
	public void toAddLinkedMatrix(int m, int n) {


		LinkedM = new LinkedMatrix(m,n);


	}
	
	/**
	 * This method calls the recursive method to add a new player
	 * @param newPlayer is the user to create
	 */

	public void addPlayer(Player newPlayer) {
		if(root== null) {
			root = newPlayer;
		}else {
			addPlayer(root,newPlayer);
		}
	}
    
	/**
	 *  This method add a new player to the tree, using inorder type of search
	 * @param current is the current player
	 * @param newPlayer is the player to add to the tree
	 */
	public void addPlayer(Player current,Player newPlayer) {
		if(newPlayer.getScore()<=current.getScore()&&current.getLeft()==null) {
			current.setLeft(newPlayer);
			newPlayer.setFather(current);
		}else if(newPlayer.getScore()>current.getScore()&&current.getRight()==null) {
			current.setRight(newPlayer);
			newPlayer.setFather(current);
		}else {
			if(newPlayer.getScore()<=current.getScore() && current.getLeft()!= null) {
				addPlayer(current.getLeft(),newPlayer);
			}else {
				addPlayer(current.getRight(),newPlayer);
			}
		}
	}
/**
 * This method calls the recursive method to show all users total score
 * @return all users score
 */
	public String showScore() {
		return showScore(root);
	}

	public String showScore(Player current) {
		String scores = "";
		if(current!=null) {
			scores += showScore(current.getLeft());
			scores += current.getNickname() + " ----------- " + current.getScore() + "\n";
			scores += showScore(current.getRight());
		}
		return scores;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int t) {
		tries = t;
	}

	public int getScore() {

		return score - getTries();
	}












}
