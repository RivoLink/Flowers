package mg.rivolink.game.flowers.screens.ui;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import static mg.rivolink.game.flowers.data.Data.FontLoader.*;
import static mg.rivolink.game.flowers.viewports.GameViewport.*;
import mg.rivolink.game.flowers.data.*;

public class InfoPannel extends Pannel{
	
	public static final String TEXT_WIN="Congratulations, you win!";
	public static final String TEXT_LOSE="Sorry, you lose!";
	
	private Label labelTop;
	private Label labelBottom;
	
	private boolean show=false;
	
	public InfoPannel(){
		super(); 
		setSize(WIDTH,HEIGHT);
		
		Label.LabelStyle style=new Label.LabelStyle();
		
		style.font=normal;
		labelTop=new Label("Information",style);
		labelTop.addAction(Actions.alpha(0));
		labelTop.setPosition(
			(getWidth()-labelTop.getPrefWidth())/2,
			getHeight()/2
		);
		
		style.font=tiny;
		labelBottom=new Label("Triple tap screen to restart.",style);
		labelBottom.addAction(Actions.alpha(0));
		labelBottom.setPosition(
			(getWidth()-labelBottom.getPrefWidth())/2,
			labelTop.getY()-labelBottom.getPrefHeight()
		);
		
		addActor(labelTop);
		addActor(labelBottom);
	}
	
	public boolean isShow() {
		return show;
	}

	public void show(String text){
		show=true;
		labelTop.setText(text);
		labelTop.setPosition(
			(getWidth()-labelTop.getPrefWidth())/2,
			getHeight()/2
		);
		labelTop.addAction(Actions.fadeIn(1f));
		labelBottom.addAction(Actions.fadeIn(1f));
	}
	
	public void show(String top,String bottom){
		show=true;
		labelTop.setText(top);
		labelTop.setPosition(
			(getWidth()-labelTop.getPrefWidth())/2,
			getHeight()/2
		);
		labelBottom.setText(bottom);
		labelBottom.setPosition(
			(getWidth()-labelBottom.getPrefWidth())/2,
			labelTop.getY()-labelBottom.getPrefHeight()
		);
		labelTop.addAction(Actions.fadeIn(1f));
		labelBottom.addAction(Actions.fadeIn(1f));
	}

}
