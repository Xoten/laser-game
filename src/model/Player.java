package model;

public class Player {


	private int score;
	private String nickname;
	private Player father;
	private Player left;
	private Player  right;
	private int ptries;
	private int bsize;
	private int scolumns;
	private int srows;
	private int smir;


	public Player(String n, int s, int sco, int sr, int sm , int size, int t) {
		nickname = n;
		score = s;
		father = null; 
		left = null;
		right = null;
		ptries = t;
		bsize = size;
		srows = sr;
		scolumns = sco;
		smir = sm;


	}
	public Player getLeft() {
		return left;
	}


	public void setLeft(Player left) {
		this.left = left;
	}


	public Player getRight() {
		return right;
	}


	public void setRight(Player right) {
		this.right = right;
	}
	public Player getFather() {
		return father;
	}
	public void setFather(Player father) {
		this.father = father;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public int getScore() {
		return score;
	}
	public void setScore(int s) {
		score = s;
	}
	public String getNickname() {
		return nickname;
	}

	public int getPTries() {

		return ptries;
	}

	public int getBSize() {

		return bsize;
	}

	public int getuserRows() {

		return srows;
	}
	public int getuserCols() {
		return scolumns;

	}
	public int getuserMir() {
		return smir;
	 }












}
