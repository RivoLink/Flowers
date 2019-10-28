package mg.rivolink.game.flowers.screens.ui;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import static mg.rivolink.game.flowers.data.Data.Style.*;
import mg.rivolink.game.flowers.viewports.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.graphics.*;
import static mg.rivolink.game.flowers.data.Data.FontLoader.*;

public class TimePannel extends Pannel{
	
	TimeListener timeListener;
	public interface TimeListener{
		public void onTimeLeft();
	}
	
	private int min=1;
	private int sec=59;
	
	private float time=0;
	private float interval=1f;
	
	private Label label;
	private Label labelUp;
	
	private boolean isOver=false;

	public TimePannel(int min,int sec){
		this.min=min;
		this.sec=sec;
		
		Label.LabelStyle style=new Label.LabelStyle();
		style.font=small;
		label=new Label("Time: "+min+"'"+sec,style);
		
		setSize(label.getPrefWidth(),label.getPrefHeight());
		setOrigin(label.getPrefWidth()/2,label.getPrefHeight()/2);
		setPosition(
			GameViewport.WIDTH-getWidth()-5,
			GameViewport.HEIGHT-getHeight()-5
		);
		
		addActor(label);
	}
	
	public void setListener(TimeListener listener){
		timeListener=listener;
	}
	
	public void stopTime(){
		isOver=true;
	}
	
	public void down(){
		if(9<sec)
			sec-=10;
		else{
			min--;
			sec=59-(10-sec);
		}
		
		if(!isOver)
			scale();
	}
	
	public void scale(){
		label.setColor(Color.RED);
		if(0<getActions().size)
			getActions().clear();
		addAction(Actions.sequence(
			Actions.scaleTo(1.1f,1.1f,0.2f),
			Actions.scaleTo(1,1,0.2f),
			Actions.scaleTo(1.1f,1.1f,0.2f),
			Actions.scaleTo(1,1,0.2f),
			Actions.run(new Runnable(){
				@Override
				public void run(){
					label.setColor(COLOR_GOLD);
				}
			})
		));
	}
	
	private void update(){
		label.setText("Time: "+min+"'"+sec);
	}
	
	public void update(float delta){
		if(!isOver && interval<(time+=delta)){
			time-=interval;
			
			if(--sec<0){
				min--;
				sec=59;
			}
			
			int time=min*100+sec;
			
			if(0<time)
				update();
			else{
				isOver=true;
				label.setText("Time: 0'0");
				if(timeListener!=null)
					timeListener.onTimeLeft();
			}
			
		}
	}
	
}
