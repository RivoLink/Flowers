package mg.rivolink.game.flowers.viewports;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameViewport {
	
	public static final float PPM=100f;
	public static final int WIDTH=Gdx.graphics.getWidth();
	public static final int HEIGHT=Gdx.graphics.getHeight();
	
	private static final GameViewport instance=new GameViewport();
	
	private Batch batch;
	private TextureRegion bg;
	private int bgX=0,bgY=0;
	
	private OrthographicCamera stageCam;
	private OrthographicCamera box2DCam;
	
	
	private GameViewport(){
		batch=new SpriteBatch();
		
		stageCam=new OrthographicCamera();
		stageCam.setToOrtho(false,WIDTH,HEIGHT);
		stageCam.update();
		
		box2DCam=new OrthographicCamera();
		box2DCam.setToOrtho(false,WIDTH/PPM,HEIGHT/PPM);
		box2DCam.update();
	}
	
	public static GameViewport getInstance(){
		return instance;
	}
	
	public void loadBackground(){
		bg=new TextureRegion(new Texture("data/img/sky.png"));
		bgX=WIDTH-bg.getRegionWidth();
		bgY=HEIGHT-bg.getRegionHeight();
	}
	
	public void drawBackground(){
		batch.begin();
		batch.draw(bg,bgX,bgY);
		batch.end();
	}
	
	public void disposeBackground(){
		bg.getTexture().dispose();
	}
	
	public static void clear(){
		Gdx.gl.glClearColor(1,1,1,1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    Gdx.gl.glViewport(0,0,WIDTH,HEIGHT);
	}
	
	public static void clearAndUpdate(){
		Gdx.gl.glClearColor(1,1,1,1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    Gdx.gl.glViewport(0,0,WIDTH,HEIGHT);
	    
	    getInstance().getStageCamera().update();
	    getInstance().getBox2DCamera().update();
	}
	
	public Camera getStageCamera() {
		return stageCam;
	}

	public Camera getBox2DCamera() {
		return box2DCam;
	}

}
