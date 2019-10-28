package mg.rivolink.game.flowers.screens;

import static mg.rivolink.game.flowers.viewports.GameViewport.*;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import mg.rivolink.game.flowers.screens.ui.*;
import com.badlogic.gdx.*;
import mg.rivolink.game.flowers.data.*;

public class LevelScreen extends ScreenAdapter implements BoxPannel.SelectListener{

	public Stage stage;
	private Game game;

	private BoxPannel pannel;
	private InfoPannel infoPannel;

	private int[] positions={10,100};
	private Image[] levels=new Image[2];

	public LevelScreen(Game game){
		this.game=game;

		stage=new Stage();
		Gdx.input.setInputProcessor(stage);

		infoPannel=new InfoPannel();
		stage.addActor(infoPannel);
		infoPannel.show(
		"Please, select your level!",
		"Enjoy the game"
		);

		float[][] xy={{1/6f,1/4f},{2/6f,3/4f},{3/6f,1/4f},{4/6f,3/4f},{5/6f,1/4f}};

		int numero=2;
		for(int i=0;i<xy.length;i++){
			pannel=new BoxPannel(numero+=2);
			pannel.setListener(this);
			pannel.setPosition(
			WIDTH*xy[i][0]-pannel.getWidth()/2,
			HEIGHT*xy[i][1]-pannel.getHeight()/2
			);
			stage.addActor(pannel);
		}

	}

	public float[][] getXY(int numero){
		switch(numero){
			default:
			case 4:{
				float[][] xy_4={
					{1/4f,1/4f},{1/4f,3/4f},
					{3/4f,1/4f},{3/4f,3/4f}
				};
				return xy_4;
			}
			case 6:{
				float[][] xy_6={
					{1/6f,3/6f},{2/6f,1/6f},{2/6f,5/6f},
					{4/6f,1/6f},{4/6f,5/6f},{5/6f,3/6f}
				};
				return xy_6;
			}
			case 8:{
				float[][] xy_8={
					{1/6f,1/6f},{1/6f,5/6f},{2/6f,2/6f},{2/6f,4/6f},
					{4/6f,2/6f},{4/6f,4/6f},{5/6f,1/6f},{5/6f,5/6f}
				};
				return xy_8;
			}
			case 10:{
				float[][] xy_10={
					{1/6f,1/6f},{1/6f,3/6f},{1/6f,5/6f},{2/6f,2/6f},{2/6f,4/6f},
					{4/6f,2/6f},{4/6f,4/6f},{5/6f,1/6f},{5/6f,3/6f},{5/6f,5/6f}
				};
				return xy_10;
			}
			case 12:{
				float[][] xy_12={
					{1/6f,1/6f},{1/6f,3/6f},{1/6f,5/6f},{2/6f,2/6f},{2/6f,4/6f},{3/6f,1/6f},
					{3/6f,5/6f},{4/6f,2/6f},{4/6f,4/6f},{5/6f,1/6f},{5/6f,3/6f},{5/6f,5/6f}
				};
				return xy_12;
			}
		}
	}

	@Override
	public void onSelect(int numero){
		game.setScreen(new PlayScreen(game,getXY(numero)));
	}

	@Override
	public void render(float delta){
		clearAndUpdate();
		getInstance().drawBackground();

		stage.act(delta);
		stage.draw();

	}







}
