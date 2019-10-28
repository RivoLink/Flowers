package mg.rivolink.game.flowers.screens;

import static mg.rivolink.game.flowers.data.Data.*;
import static mg.rivolink.game.flowers.viewports.GameViewport.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen extends ScreenAdapter {
	
	private Stage stage;
	private Label label1,label2;
	
	public MainScreen(final Game game){
		stage=new Stage();
		stage.getViewport().setCamera(getInstance().getStageCamera());
		stage.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button){
				game.setScreen(new LevelScreen(game));
				return super.touchDown(event,x,y,pointer,button);
			}
		});
		Gdx.input.setInputProcessor(stage);
		
		Label.LabelStyle style=new Label.LabelStyle();
		
		style.font=FontLoader.big;
		label1=new Label("Flowers",style);
		label1.setPosition((WIDTH-label1.getPrefWidth())/2,HEIGHT/2);
		label1.addAction(Actions.alpha(0.8f));
		stage.addActor(label1);
		
		style.font=FontLoader.normal;
		label2=new Label(" The bouncing",style);
		label2.setPosition(label1.getX(),HEIGHT/2+label1.getPrefHeight());
		label2.addAction(Actions.alpha(0.8f));
		stage.addActor(label2);
		
		style.font=FontLoader.small;
		label2=new Label("Inspired by HaynaH",style);
		float x=label1.getX()+label1.getPrefWidth()-label2.getPrefWidth();
		label2.setPosition(x,(HEIGHT-label2.getPrefHeight())/2);
		label2.addAction(Actions.alpha(0.8f));
		stage.addActor(label2);
		
		style.font=FontLoader.normal;
		label2=new Label("Touch screen",style);
		label2.setPosition((WIDTH-label2.getPrefWidth())/2,HEIGHT/4);
		label2.addAction(Actions.forever(Actions.sequence(
			Actions.fadeOut(1f),
			Actions.delay(0.3f),
			Actions.fadeIn(1f),
			Actions.delay(0.5f)
		)));
		stage.addActor(label2);
	}
	
	@Override
	public void render(float delta){
		clear();
		getInstance().drawBackground();
		//getInstance().getStageCamera().update();
		
		stage.act(delta);
	    stage.draw();
	}
}
