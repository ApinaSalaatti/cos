package com.dogshitempire.cos.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dogshitempire.cos.GameApplication;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 900;
                config.height = 600;
                config.title = "Can't Hug Every Cat";
		new LwjglApplication(new GameApplication(), config);
	}
}
