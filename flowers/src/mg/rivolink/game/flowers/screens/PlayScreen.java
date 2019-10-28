package mg.rivolink.game.flowers.screens;

import static mg.rivolink.game.flowers.data.Data.FontLoader.*;
import static mg.rivolink.game.flowers.viewports.GameViewport.*;

import java.util.Random;

import mg.rivolink.game.flowers.actors.Flower;
import mg.rivolink.game.flowers.data.Data.SoundLoader;
import mg.rivolink.game.flowers.screens.ui.InfoPannel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import mg.rivolink.game.flowers.data.*;
import mg.rivolink.game.flowers.*;
import mg.rivolink.game.flowers.screens.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;

public class PlayScreen extends ScreenAdapter implements TimePannel.TimeListener {
	
	private Stage stage;
	private Score score;
	
	private World world;
	private Gravity gravity;
	private Box2DDebugRenderer boxDebug;
	
	private boolean pause=false;
	
	private Flower flower;
	private static Flower queen;
	private Array<Flower> flowers;
	
	private Pannel pannel;
	private InfoPannel infoPannel;
	private TimePannel timePannel;
	
	private final float[][] xy;
	
	public PlayScreen(final Game game,float[][] xy){
		SoundLoader.background.loop();
		
		this.xy=xy;
		
		Box2D.init();
		boxDebug=new Box2DDebugRenderer();
		
		gravity=Gravity.getInstance();
		world=new World(gravity,true);
		
		GdxGame.createBorder(world);
		
		stage=new Stage();
		Gdx.input.setInputProcessor(stage);
		
		stage.getViewport().setCamera(getInstance().getStageCamera());
		
		pannel=new Pannel(true);
		pannel.setSize(WIDTH,HEIGHT);
		stage.addActor(pannel);
		
		flowers=new Array<Flower>();
		for(int i=0;i<xy.length;i++){
			stage.addActor(flower=new Flower(world,(WIDTH*xy[i][0])/PPM,(HEIGHT*xy[i][1])/PPM));
			
			flowers.add(flower);
			
			flower.addAction(Actions.forever(Actions.rotateBy(180,1)));
			flower.addListener(new ClickListener(){
				
				Flower f=flower;
				
				@Override
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button){
					if(f.equals(queen)){
						score.up();
						flowers.removeValue(f,true);
						
						if(0<flowers.size){
							flowers.shuffle();
							queen.setKey(flowers.get(0).getKey());
							queen.setDrawable(flowers.get(0).getDrawable());
						}
						else{
							onWin();
						}
						
						f.destroy();
					}
					
					return super.touchDown(event,x,y,pointer,button);
				}
			});
		}
		
		stage.addActor(queen=Flower.queen(world,flowers));
		createUI(game);
	}
	
	private void onWin(){
		infoPannel.show(InfoPannel.TEXT_WIN);
		timePannel.stopTime();
		
		SoundLoader.background.pause();
		
	}

	@Override
	public void onTimeLeft(){
		if(!infoPannel.isShow())
			infoPannel.show(InfoPannel.TEXT_LOSE);
	}
	
	private void createUI(final Game game){
		pannel.addListener(new ActorGestureListener(){
			@Override
			public void tap(InputEvent event,float x,float y,int count,int button){
				super.tap(event,x,y,count,button);
				
				if(count==1)
					timePannel.down();
					
				if(count==3){
					if(true || infoPannel.isShow())
						game.setScreen(new LevelScreen(game));
					else
						pause=!pause;
				}

			}
		});
		
		Label.LabelStyle style=new Label.LabelStyle();
		style.font=small;
		score=new Score(style);
		score.setPosition(0,HEIGHT-score.getPrefHeight());
		score.addAction(Actions.alpha(0.8f));
		stage.addActor(score);
		
		infoPannel=new InfoPannel();
		stage.addActor(infoPannel);
		
		timePannel=new TimePannel(1,59);
		timePannel.setListener(this);
		stage.addActor(timePannel);
		
	}
	
	@Override
	public void dispose(){
		SoundLoader.background.dispose();
	}
	
	@Override
	public void render(float delta){
		clear();
		getInstance().drawBackground();
		

		timePannel.update(delta);

		stage.act(delta);
	    stage.draw();
	    
		
		if(pause)
			delta=0;
		
	    gravity.update(delta);
	    world.setGravity(gravity);
	    world.step(delta,6,2);
//	    boxDebug.render(world,getInstance().getBox2DCamera().combined);
	    
	}
	
	static class Gravity extends Vector2{
		private static final long serialVersionUID=1L;
		
		private static Random rnd=new Random();
		private static Gravity instance=new Gravity();
		
		private float time=0;
		private float interval=2f;
		
		private Gravity(){
			x=0;y=-3;
		}
		
		public static Gravity getInstance(){
			return instance;
		}
		
		public void update(float delta){
			if(interval<(time+=delta)){
				time-=interval;
				x=3*(2*rnd.nextFloat()-1);
				y=3*(2*rnd.nextFloat()-1);
			}
		}
	}
	
	class Score extends Label {
		
		private int value=0;
		
		Score(Label.LabelStyle style){
			super(" Score: 0",style);
		}
		
		@Override
		public void setText(CharSequence text){
			//TODO nothing
		}
		
		public void up(){
			this.value++;
			super.setText(" Score: "+this.value);
		}
		
		public void setValue(int value){
			this.value=value;
			super.setText(" Score: "+this.value);
		}
		
		public int getValue(){
			return value;
		}
		
	}

}
