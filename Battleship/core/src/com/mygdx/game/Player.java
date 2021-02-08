package com.mygdx.game;

import java.util.ArrayList;

public class Player {

	// Ships Field
	// 0 - Not a ship
	// 1 - Alive ship
	// -1 - Dead ship

	// Moves Field
	// 0 - Is not open
	// 1 - Is open

	private int id; // 0 or 1
	private Field ships;
	private Field moves;
	private ArrayList<Ship> allShips;

	public Player(int id, int h, int w) {
		this.id = id;
		this.ships = new Field(h, w, this);
		this.moves = new Field(h, w, this);
		this.allShips = new ArrayList<>();
	}

	public int getId() {
		return this.id;
	}

	public Field getShips() {
		return this.ships;
	}

	public Field getMoves() {
		return this.moves;
	}

	public void placeShip(int x, int y, int pos) {
		int length = this.allShips.size() + 2;
		int status = 1;
		for (int i = 0; i < length; i++) {
			try {
				if (this.ships.getCoordValue(x + (1 - pos) * i, y + pos * i) != 0)
					status = 0;
			} catch (Exception e) {
				status = 0;
			}
		}
		if (status == 1) {
			this.allShips.add(new Ship(x, y, length, pos, this));
			for (int i = 0; i < length; i++) {
				this.ships.updateField(x + (1 - pos) * i, y + pos * i, 1);
			}
		}
	}

	public void makeMove(int x, int y) {
		if (this.moves.getCoordValue(x, y) == 0) {
			this.moves.updateField(x, y, 1);
		}
	}

	public void checkShips(Field move) {
		for (Ship ship : this.allShips) {
			if (ship.isDefeated(move)) {
				for (int i = 0; i < ship.getLength(); i++) {
					this.ships.updateField(ship.getCoords()[i][1], ship.getCoords()[i][0], -1);
				}
			}
		}
	}

	public int getNumOfShips() {
		return this.allShips.size();
	}

	public boolean isDefeated(Field move) {
		boolean defeated = true;
		loop: for (int i = 0; i < this.ships.getH(); i++) {
			for (int j = 0; j < this.ships.getW(); j++) {
				if (this.ships.getCoordValue(j, i) == 1) {
					defeated = false;
					break loop;
				}
			}
		}
		return defeated;
	}

}
