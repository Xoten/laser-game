package model;

public class User {

	
	private int score;
	private String nickname;
	private User father;
	private User left;
	private User  right;


	public User(String n, int s) {
		nickname = n;
		score = s;
		father = null;
		left = null;
		right = null;

	}
	public User getLeft() {
		return left;
	}


	public void setLeft(User left) {
		this.left = left;
	}


	public User getRight() {
		return right;
	}


	public void setRight(User right) {
		this.right = right;
	}
	public User getFather() {
		return father;
	}
	public void setFather(User father) {
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
