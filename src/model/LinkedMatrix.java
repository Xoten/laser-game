package model;

public class LinkedMatrix {
	private Node first;
	private int numRows;
	private int numCols;




	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * This method is the constructor of the Linked Matrix
	 * @param m is the number of rows
	 * @param n is the number of columns
	 */
	public LinkedMatrix(int m, int n) {
		numRows = m;
		numCols = n;
		createMatrix(); 

	}
	public int getNumRows() {
		return numRows;
	}

	/**
	 * This method create a new Linked matrix
	 */
	private void createMatrix() {

		first = new Node(0,0);
		createRow(0,0,first);
	}
	/**
	 * This method create a new row
	 * @param i is the current row 
	 * @param j is the current column you 
	 * @param currentFirstRow is the current node at the start of the row
	 */

	private void createRow(int i, int j, Node currentFirstRow) {

		createCol(i,j+1,currentFirstRow,currentFirstRow.getUp());
		if(i+1<numRows) {
			Node downFirstRow = new Node(i+1,j);
			downFirstRow.setUp(currentFirstRow);
			currentFirstRow.setDown(downFirstRow);
			createRow(i+1,j,downFirstRow);
		}
	}
	/**
	 * This method create a new columns
	 * @param i is the current row 
	 * @param j is the current column
	 * @param prev is the previous node to current node
	 * @param rowPrev is the node located above to current node
	 */
	private void createCol(int i, int j, Node prev, Node rowPrev) {
		if(j<numCols) {

			Node current = new Node(i, j);
			current.setPrev(prev);
			prev.setNext(current);

			if(rowPrev!=null) {
				rowPrev = rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}

			createCol(i,j+1,current,rowPrev);
		}
	}
	
	/**
	 *This method obtains the info of all the matrix
	 * @return the info
	 */
     
	public String toString() {
		String msg;
		msg = toStringRow(first);
		return msg;
	}
    
	/**
	 *This method obtains the info of the nodes in the rows of the matrix 
	 * @param current is the current node
	 * @return the info of the rows
	 */
	
	private String toStringRow(Node firstRow) {
		String msg = "";
		if(firstRow!=null) {
			msg = toStringCol(firstRow) + "\n";
			msg += toStringRow(firstRow.getDown());
		}
		return msg;
	}
	
	/**
	 *This method obtains the info of the nodes in the columns of the matrix 
	 * @param current is the current node
	 * @return the info of the columns
	 */
	
	private String toStringCol(Node current) {
		String msg = "";
		if(current!=null) {
			msg = current.toString();
			msg += toStringCol(current.getNext());
		}
		return msg;
	}

	public String toString2() {
		String msg = "";

		return msg;
	}


	public Node getFirst() {

		return first;
	}


}
