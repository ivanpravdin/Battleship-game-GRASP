package com.mygdx.game;

public class Field {
	private int h;
	private int w;
	private int[][] field;
	private Player owner;

	public Field(int h, int w, Player owner) {
		this.h = h;
		this.w = w;
		this.field = new int[h][w];
		this.owner = owner;
	}

	public int getH() {
		return this.h;
	}

	public int getW() {
		return this.w;
	}

	public Player getOwner() {
		return this.owner;
	}

	public void updateField(int x, int y, int newValue) {
		this.field[y][x] = newValue;
	}

	public int getCoordValue(int x, int y) {
		return this.field[y][x];
	}

}
