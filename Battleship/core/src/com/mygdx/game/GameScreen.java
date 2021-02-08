package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

	public static final int STATUS_WIDTH = 700;
	public static final int STATUS_HEIGHT = 50;
	public static final int SQUARE_WIDTH = 100;
	public static final int SQUARE_HEIGHT = 100;
	public static final int LINE_THICKNESS = 50;
	private static ShapeRenderer shapeRenderer;
	private static Player[] players;
	private static int[] ships;

	BattleshipGame game;

	Texture Status0;
	Texture Status1;
	Texture Status2;
	Texture Status3;
	Texture Status4;
	Texture Status5;

	public GameScreen(BattleshipGame game) {
		this.ships = new int[] { 2, 3, 4, 5 };
		this.shapeRenderer = new ShapeRenderer();
		this.game = game;
		this.players = new Player[game.NUMOFPL];
		for (int i = 0; i < game.NUMOFPL; i++) {
			this.players[i] = new Player(i, game.GRID_ROW, game.GRID_COL);
		}
		Status0 = new Texture("status0.png");
		Status1 = new Texture("status1.png");
		Status2 = new Texture("status2.png");
		Status3 = new Texture("status3.png");
		Status4 = new Texture("status4.png");
		Status5 = new Texture("status5.png");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
		game.batch.begin();
		switch (game.STATUS) {
		case 0:
			for (int i = 0; i < game.GRID_ROW; i++) {
				for (int j = 0; j < game.GRID_COL; j++) {
					if ((Gdx.input.getX() < SQUARE_WIDTH * i + SQUARE_WIDTH && Gdx.input.getX() > SQUARE_WIDTH * i
							&& game.HEIGHT - Gdx.input.getY() < SQUARE_HEIGHT * j + SQUARE_HEIGHT
							&& game.HEIGHT - Gdx.input.getY() > SQUARE_HEIGHT * j)
							|| this.players[0].getShips().getCoordValue(j, i) != 0) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GREEN);
						if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
							int pos = 0;
							int x = j;
							int y = i;
							this.players[0].placeShip(x, y, pos);
						} else if (Gdx.input.isButtonJustPressed(Buttons.RIGHT)) {
							int pos = 1;
							int x = j;
							int y = i;
							this.players[0].placeShip(x, y, pos);
						}
					} else {
						shapeRenderer.begin(ShapeType.Line);
						shapeRenderer.setColor(Color.BLACK);
					}
					shapeRenderer.rect(SQUARE_WIDTH * i, SQUARE_HEIGHT * j, SQUARE_WIDTH, SQUARE_HEIGHT);
					shapeRenderer.end();
				}
			}
			if (this.players[0].getNumOfShips() == this.ships.length) {
				game.STATUS++;
			}
			break;
		case 1:
			for (int i = 0; i < game.GRID_ROW; i++) {
				for (int j = 0; j < game.GRID_COL; j++) {
					if ((Gdx.input.getX() < SQUARE_WIDTH * i + SQUARE_WIDTH && Gdx.input.getX() > SQUARE_WIDTH * i
							&& game.HEIGHT - Gdx.input.getY() < SQUARE_HEIGHT * j + SQUARE_HEIGHT
							&& game.HEIGHT - Gdx.input.getY() > SQUARE_HEIGHT * j)
							|| this.players[1].getShips().getCoordValue(j, i) != 0) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GREEN);
						if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
							int pos = 0;
							int x = j;
							int y = i;
							this.players[1].placeShip(x, y, pos);
						} else if (Gdx.input.isButtonJustPressed(Buttons.RIGHT)) {
							int pos = 1;
							int x = j;
							int y = i;
							this.players[1].placeShip(x, y, pos);
						}
					} else {
						shapeRenderer.begin(ShapeType.Line);
						shapeRenderer.setColor(Color.BLACK);
					}
					shapeRenderer.rect(SQUARE_WIDTH * i, SQUARE_HEIGHT * j, SQUARE_WIDTH, SQUARE_HEIGHT);
					shapeRenderer.end();
				}
			}
			if (this.players[1].getNumOfShips() == this.ships.length) {
				game.STATUS++;
			}
			break;
		case 2:
			// this.players[0]; // plays
			// this.players[1]; // rival
			for (int i = 0; i < game.GRID_ROW; i++) {
				for (int j = 0; j < game.GRID_COL; j++) {
					if ((Gdx.input.getX() < SQUARE_WIDTH * i + SQUARE_WIDTH && Gdx.input.getX() > SQUARE_WIDTH * i
							&& game.HEIGHT - Gdx.input.getY() < SQUARE_HEIGHT * j + SQUARE_HEIGHT
							&& game.HEIGHT - Gdx.input.getY() > SQUARE_HEIGHT * j)
							|| this.players[0].getMoves().getCoordValue(j, i)
									* this.players[1].getShips().getCoordValue(j, i) == 1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GREEN);
						if (Gdx.input.isButtonJustPressed(Buttons.LEFT)
								|| Gdx.input.isButtonJustPressed(Buttons.RIGHT)) {
							int move_x = j;
							int move_y = i;
							if (this.players[0].getMoves().getCoordValue(move_x, move_y) != 1) {
								this.players[0].makeMove(move_x, move_y);
								this.players[1].checkShips(this.players[0].getMoves());
								if (this.players[1].isDefeated(this.players[0].getMoves())) {
									game.STATUS = 4;
								} else {
									if (this.players[1].getShips().getCoordValue(move_x, move_y) != 0) {
										game.STATUS = 2;
									} else {
										game.STATUS = 3;
									}
								}
							}
						}
					} else if (this.players[0].getMoves().getCoordValue(j, i)
							* this.players[1].getShips().getCoordValue(j, i) == -1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.RED);
					} else if (this.players[0].getMoves().getCoordValue(j, i) == 1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GRAY);
					} else {
						shapeRenderer.begin(ShapeType.Line);
						shapeRenderer.setColor(Color.BLACK);
					}
					shapeRenderer.rect(SQUARE_WIDTH * i, SQUARE_HEIGHT * j, SQUARE_WIDTH, SQUARE_HEIGHT);
					shapeRenderer.end();
				}
			}
			break;
		case 3:
			// this.players[0]; // plays
			// this.players[1]; // rival
			for (int i = 0; i < game.GRID_ROW; i++) {
				for (int j = 0; j < game.GRID_COL; j++) {
					if ((Gdx.input.getX() < SQUARE_WIDTH * i + SQUARE_WIDTH && Gdx.input.getX() > SQUARE_WIDTH * i
							&& game.HEIGHT - Gdx.input.getY() < SQUARE_HEIGHT * j + SQUARE_HEIGHT
							&& game.HEIGHT - Gdx.input.getY() > SQUARE_HEIGHT * j)
							|| this.players[1].getMoves().getCoordValue(j, i)
									* this.players[0].getShips().getCoordValue(j, i) == 1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GREEN);
						if (Gdx.input.isButtonJustPressed(Buttons.LEFT)
								|| Gdx.input.isButtonJustPressed(Buttons.RIGHT)) {
							int move_x = j;
							int move_y = i;
							if (this.players[1].getMoves().getCoordValue(move_x, move_y) != 1) {
								this.players[1].makeMove(move_x, move_y);
								this.players[0].checkShips(this.players[1].getMoves());
								if (this.players[0].isDefeated(this.players[1].getMoves())) {
									game.STATUS = 5;
								} else {
									if (this.players[0].getShips().getCoordValue(move_x, move_y) != 0) {
										game.STATUS = 3;
									} else {
										game.STATUS = 2;
									}
								}
							}
						}
					} else if (this.players[1].getMoves().getCoordValue(j, i)
							* this.players[0].getShips().getCoordValue(j, i) == -1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.RED);
					} else if (this.players[1].getMoves().getCoordValue(j, i) == 1) {
						shapeRenderer.begin(ShapeType.Filled);
						shapeRenderer.setColor(Color.GRAY);
					} else {
						shapeRenderer.begin(ShapeType.Line);
						shapeRenderer.setColor(Color.BLACK);
					}
					shapeRenderer.rect(SQUARE_WIDTH * i, SQUARE_HEIGHT * j, SQUARE_WIDTH, SQUARE_HEIGHT);
					shapeRenderer.end();
				}
			}
			break;
		}
		game.batch.end();
		game.batch.begin();
		switch (game.STATUS) {
		case 0:
			game.batch.draw(Status0, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		case 1:
			game.batch.draw(Status1, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		case 2:
			game.batch.draw(Status2, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		case 3:
			game.batch.draw(Status3, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		case 4:
			game.batch.draw(Status4, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		case 5:
			game.batch.draw(Status5, game.WIDTH / 2 - STATUS_WIDTH / 2, game.HEIGHT - game.HEIGHT / 10, STATUS_WIDTH,
					STATUS_HEIGHT);
			break;
		}

		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
