package com.mygdx.game;

public class Ship {

	private int length;
	private Player owner;
	private int[][] coords;
	private boolean defeated;

	// pos 0-horizontal
	// pos 1-vertical

	public Ship(int x, int y, int length, int pos, Player owner) {
		this.defeated = false;
		this.length = length;
		this.owner = owner;
		this.coords = new int[length][2];
		setCoords(x, y, pos);
	}

	private void setCoords(int x, int y, int pos) {
		for (int i = 0; i < this.length; i++) {
			this.coords[i][0] = y + i * pos;
			this.coords[i][1] = x + i * (1 - pos);
		}
	}

	public int getLength() {
		return this.length;
	}

	public int[][] getCoords() {
		return this.coords;
	}

	public Player getOwner() {
		return this.owner;
	}

	public boolean isDefeated(Field moves) {
		if (this.defeated)
			return this.defeated;
		boolean defeated = true;
		for (int i = 0; i < this.length; i++) {
			if (moves.getCoordValue(this.coords[i][1], this.coords[i][0]) == 0)
				defeated = false;
		}
		if (defeated)
			this.defeated = defeated;
		return defeated;
	}

}
