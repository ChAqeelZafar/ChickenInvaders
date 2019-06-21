package com.aqeelzafar.chickeninvaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aqeelzafar.chickeninvaders.ChickenInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Chicken Invaders";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new ChickenInvaders(), config);
	}
}
