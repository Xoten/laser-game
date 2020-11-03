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




public Node Shoot(Node ShootEnd, String initialTrayect) {

	if(initialTrayect.equalsIgnoreCase("down")) {

		if(ShootEnd.getDown() != null) {

			if(ShootEnd.getDown().getMirror().equals("\\")) {


				initialTrayect = "right";
				ShootEnd = ShootEnd.getDown();
				return Shoot(ShootEnd, initialTrayect);
			}else if(ShootEnd.getDown().getMirror().equals("/")) {

				initialTrayect = "left";
				ShootEnd = ShootEnd.getDown();
				return Shoot(ShootEnd, initialTrayect);
			}else {



				ShootEnd = ShootEnd.getDown();
				return Shoot(ShootEnd, initialTrayect);
			}

		}

		return ShootEnd;

	}

	if(initialTrayect.equalsIgnoreCase("up")) {

		if(ShootEnd.getUp() != null) {

			if(ShootEnd.getUp().getMirror().equals("\\")) {


				initialTrayect = "left";
				ShootEnd = ShootEnd.getUp();
				return  Shoot(ShootEnd, initialTrayect);
			}else if(ShootEnd.getUp().getMirror().equals("/")) {

				initialTrayect = "right";
				ShootEnd = ShootEnd.getUp();
				Shoot(ShootEnd, initialTrayect);
			}else {

				ShootEnd = ShootEnd.getUp();
				return Shoot(ShootEnd, initialTrayect);

			}
		}

		return ShootEnd;

	}
	if(initialTrayect.equalsIgnoreCase("Right")) {

		if(ShootEnd.getNext() != null) {

			if(ShootEnd.getNext().getMirror().equals("\\")) {


				initialTrayect = "down";
				ShootEnd = ShootEnd.getNext();
				return Shoot(ShootEnd, initialTrayect);
			}else if(ShootEnd.getNext().getMirror().equals("/")) {

				initialTrayect = "up";
				ShootEnd = ShootEnd.getNext();
				return Shoot(ShootEnd, initialTrayect);
			}else {

				ShootEnd = ShootEnd.getNext();
				return Shoot(ShootEnd, initialTrayect);

			}

		}

		return ShootEnd;

	}
	if(initialTrayect.equalsIgnoreCase("Left")) {

		if(ShootEnd.getPrev() != null) {

			if(ShootEnd.getPrev().getMirror().equals("\\")) {


				initialTrayect = "up";
				ShootEnd = ShootEnd.getPrev();
				return Shoot(ShootEnd, initialTrayect);
			}else if(ShootEnd.getPrev().getMirror().equals("/")) {

				initialTrayect = "down";
				ShootEnd = ShootEnd.getPrev();
				return Shoot(ShootEnd, initialTrayect);


			}else {


				ShootEnd = ShootEnd.getPrev();
				return Shoot(ShootEnd, initialTrayect);

			}
		}
	}
	return ShootEnd;
}

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
