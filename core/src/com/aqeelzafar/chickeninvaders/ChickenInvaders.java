package com.aqeelzafar.chickeninvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.GameController;

public class ChickenInvaders extends ApplicationAdapter {

	GameController gameController;
	int WIDTH = 1600;
	int HEIGHT = 960;
	
	@Override
	public void create () {
		gameController = new GameController(WIDTH, HEIGHT);
	}

	float timeTotal = 0;
	float timeSec = 0;
	@Override
	public void render () {


		timeSec += Gdx.graphics.getDeltaTime();

		if(timeSec >= 1){
			System.out.println(++timeTotal);
			timeSec = 0;
		}


		gameController.update();
		gameController.draw();
		if(Gdx.input.isTouched()){
			gameController.onTouched();

		}
		if(Gdx.input.justTouched()){
			gameController.onFire();

		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			gameController.moveLeft();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			gameController.moveRight();
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			gameController.onFire();
		}



		if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()){
			gameController.startNewGame();
		}

	}
	
	@Override
	public void dispose () {

	}


}
