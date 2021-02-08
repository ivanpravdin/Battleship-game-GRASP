package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleshipGame extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 900;
	public static final int GRID_COL = 8;
	public static final int GRID_ROW = 8;
	public static int STATUS = 0; // 0 - first player places ships, 1 - second player places ships, 2 - first
									// player plays, 3 - second player plays, 4 - first player won, 5 - second
									// player won.
	public static final int NUMOFPL = 2;

	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}
}
