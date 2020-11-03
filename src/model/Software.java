package model;


public class Software {

	private Node exitNode;
	LinkedMatrix LinkedM;

	public Software() {

	}

	public Node getExitNode() {
		return exitNode;
	}

	public LinkedMatrix getLinkedM() {
		return LinkedM;
	}

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

		return isFound;

	}

	
	/**
	 * This method determines the initial direction that the bullet goes when the user its shooting from the borders but not the corners
	 * @param ToLocateStart is the node where the user stars firing.
	 * @return the initial Direction that the bullet goes.
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
	 * @return the initial direction that the bullet takes.
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
     * This method obtains the node where the bullet goes out of the matrix.
     * @param ShootEnd is the current position of the laser, at first its the start node
     * @param initialWay is the initial course that the bullet goes
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
	 * This methods search a node by its row number and column number
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

			}else {
				toLocate.setValue("X");	
				guessed = false;
			}

		}else {
			toLocate.setValue("X");
			guessed = false;
		}

		return guessed;


	}

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

	public void generateRandomMirrors(int mirrorN, int n, int m) {
		if(mirrorN!=0) {
			int aleatoryN = (int)(Math.random()*n+1)-1;
			int aleatoryM = (int)(Math.random()*m+1)-1;
			Node toSearch = toFindPosition(LinkedM.getFirst(), aleatoryN, aleatoryM);
			if(toSearch.getMirror().equals("")) {
				int mirrorRandom = (int)(Math.random()*2+1);
				if(mirrorRandom==1) {
					toSearch.setMirror("/");
				}else if(mirrorRandom==2) {
					toSearch.setMirror("\\");
				}
				generateRandomMirrors(mirrorN-1,n,m);
			}else {
				generateRandomMirrors(mirrorN, n, m);
			}
		}
	}

	public void toAddLinkedMatrix(int m, int n) {


		LinkedM = new LinkedMatrix(m,n);

	}











}
