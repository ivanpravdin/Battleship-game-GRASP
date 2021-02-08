package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	private static final int PLAY_BUTTON_WIDTH = 300;
	private static final int PLAY_BUTTON_HEIGHT = 150;

	BattleshipGame game;

	Texture playButtonInactive;
	Texture playButtonActive;

	public MainMenuScreen(BattleshipGame game) {
		this.game = game;
		playButtonInactive = new Texture("PlayButton.png");
		playButtonActive = new Texture("PlayButtonActive.png");
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1);
		game.batch.begin();

		int x = game.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2;
		int y = game.HEIGHT / 2;
		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x
				&& game.HEIGHT - Gdx.input.getY() < y + PLAY_BUTTON_HEIGHT && game.HEIGHT - Gdx.input.getY() > y) {
			game.batch.draw(playButtonActive, game.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, game.HEIGHT / 2,
					PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new GameScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive, game.WIDTH / 2 - PLAY_BUTTON_WIDTH / 2, game.HEIGHT / 2,
					PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
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
