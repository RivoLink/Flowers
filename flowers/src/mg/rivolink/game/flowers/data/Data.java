package mg.rivolink.game.flowers.data;

import static mg.rivolink.game.flowers.viewports.GameViewport.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Data {
	
	public static void load(){
		SoundLoader.load();
		FontLoader.load();
		AtlasLoader.load();
	}
	
	public static class Style{
		
		public static final Color COLOR_BLEU=Color.valueOf("#001630");
		public static final Color COLOR_GOLD=Color.valueOf("#DAD5A8");
		
	}
	
	public static class SoundLoader {
		public static Sound background,truth,wrong;
		
		public static void load(){
			truth=Gdx.audio.newSound(Gdx.files.internal("data/sound/truth.mp3"));
			wrong=Gdx.audio.newSound(Gdx.files.internal("data/sound/wrong.mp3"));
			background=Gdx.audio.newSound(Gdx.files.internal("data/sound/background.mp3"));
		}
	}
	
	public static class AtlasLoader {
		public static TextureAtlas flowers;
		
		public static void load(){
			flowers=new TextureAtlas("data/atlas/flowers.atlas");
		}
	}
	
	public static class FontLoader {
		
		public static BitmapFont tiny,small,normal,big;
		
		public static void load(){
			FileHandle file=Gdx.files.internal("data/font/supercell.ttf");
			FreeTypeFontGenerator gen=new FreeTypeFontGenerator(file);
			FreeTypeFontGenerator.FreeTypeFontParameter param=new FreeTypeFontGenerator.FreeTypeFontParameter();
			
			param.spaceX=1;
			param.borderWidth=2f;
			param.color=Style.COLOR_BLEU;
			param.borderColor=Style.COLOR_GOLD;
			param.size=8*(WIDTH/800);
			tiny=gen.generateFont(param);
			
			param.size=12*(WIDTH/800);
			small=gen.generateFont(param);
			
			param.size=20*(WIDTH/800);
			normal=gen.generateFont(param);
			
			param.spaceX=-5;
			param.borderWidth=5f;
			param.size=100*(WIDTH/800);
			big=gen.generateFont(param);
			
			gen.dispose();
			
		}
		
		
	}

}
