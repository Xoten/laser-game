package ui;

import model.LinkedMatrix;

public class Main {

	public static void main(String[] args) {
		LinkedMatrix lm = new LinkedMatrix(3, 4);
		lm.generateRandomMirrors( 7, 3 , 4);
		lm.LocateMirror(3,2,"L");
		System.out.println(lm);
	}

}
