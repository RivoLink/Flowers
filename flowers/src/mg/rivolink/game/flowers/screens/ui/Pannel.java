package mg.rivolink.game.flowers.screens.ui;

import com.badlogic.gdx.scenes.scene2d.*;

public class Pannel extends Group{
	
	public Pannel(){
		super();
		setTouchable(Touchable.disabled);
	}
	
	public Pannel(boolean touchable){
		super();
		setTouchable(touchable?Touchable.enabled:Touchable.disabled);
	}
}
