package mg.rivolink.game.flowers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import mg.rivolink.game.flowers.GdxGame;

public class Main {
	
	public static void main(String[] args){
		
		LwjglApplicationConfiguration cfg=new LwjglApplicationConfiguration();
		cfg.title="Haynah";
		cfg.width=800;
		cfg.height=480;
		
		new LwjglApplication(new GdxGame(),cfg);
	}
}
