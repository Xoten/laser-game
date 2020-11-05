package model;

public class Player {

	
	private int score;
	private String nickname;
	private Player father;
	private Player left;
	private Player  right;


	public Player(String n, int s) {
		nickname = n;
		score = s;
		father = null; 
		left = null;
		right = null;

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














}
