package model;

public class Node {
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;	
	private Node down;
	private String mirror;
	private String value;
	private boolean visibility;
	


	public Node(int r, int c) {
		row = r;
		col = c;
		mirror = "";
		value = "";
		
	}
	public boolean getVisibility() {
		return visibility;
	}


	public void setVisibility(boolean v) {
		visibility = v;
	}
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String v) {
		value = v;
	}
	public String getMirror() {
		return mirror;
	}

	public void setMirror(String mir) {
		mirror = mir;
	}
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getNameCol() {
		return (char)('A'+col);
	}
	
	public Node getNext() {
		return next;
	}

	public Node getPrev() {
		return prev;
	}

	public Node getUp() {
		return up;
	}

	public Node getDown() {
		return down;
	}

	public void setPrev(Node p) {
		prev = p;
	}

	public void setNext(Node n) {
		next = n;
	}

	public void setUp(Node u) {
		up = u;
	}

	public void setDown(Node d) {
		down = d;
	}
	
	public String toString() {
		return "[ "  +getValue()+ " ]";
	}
}
