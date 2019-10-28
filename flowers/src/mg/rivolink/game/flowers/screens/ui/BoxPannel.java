package mg.rivolink.game.flowers.screens.ui;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.graphics.*;
import mg.rivolink.game.flowers.data.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import mg.rivolink.game.flowers.screens.ui.BoxPannel.*;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class BoxPannel extends Pannel{

	private BoxPannel.SelectListener selectListener;
	
	public interface SelectListener{
		public void onSelect(int numero);
	}
	
	private Label label1;
	private Label label2;
	
	public final int numero;
	
	public BoxPannel(int numero){
		super(true);
		this.numero=numero;
		this.setSize(130,130);
		this.setOrigin(65,65);
		
		this.addListener(new ClickListener(){
			@Override
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button){
				BoxPannel.this.addAction(Actions.sequence(
					Actions.scaleTo(1.1f,1.1f,0.2f),
					Actions.scaleTo(1,1,0.2f),
					Actions.run(new Runnable(){
						@Override
						public void run(){
							if(selectListener!=null)
								selectListener.onSelect(BoxPannel.this.numero);
						}
					})
				));
				
				return super.touchDown(event,x,y,pointer,button);
			}
				
		});
		
		int size=(int)getWidth();
		
		Pixmap pix=new Pixmap(size,size,Pixmap.Format.RGBA8888);
		pix.setColor(Data.Style.COLOR_GOLD);
		pix.drawCircle(size/2,size/2,size/2-1);
		pix.drawCircle(size/2,size/2,size/2-4);
		
		Image image=new Image(new Texture(pix));
		addActor(image);
		
		pix.dispose();
		
		Label.LabelStyle style=new Label.LabelStyle();
		style.font=Data.FontLoader.normal;
		label1=new Label(""+numero,style);
		label1.setPosition(
			(size-label1.getPrefWidth())/2,
			size/2
		);
		addActor(label1);
		
		style.font=Data.FontLoader.small;
		label2=new Label("Flowers",style);
		label2.setPosition(
			(size-label2.getPrefWidth())/2,
			size/2-label2.getPrefHeight()
		);
		addActor(label2);
		
	}
	
	public void setListener(SelectListener listener){
		this.selectListener=listener;
	}
	
}
