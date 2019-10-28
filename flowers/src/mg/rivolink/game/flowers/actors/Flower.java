package mg.rivolink.game.flowers.actors;

import static mg.rivolink.game.flowers.data.Data.AtlasLoader.*;
import static mg.rivolink.game.flowers.viewports.GameViewport.*;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class Flower extends Image {
	
	public static final String[] KEYS={"a","b","c","d","e","f","g"};
	
	private static final float FACTOR=8;
	private static final Random rnd=new Random();
	
	private float w;
	private float h;
	
	private Body body;
	private World world;
	
	private String key;

	public Flower(World world,String region,float x,float y){
		super(flowers.findRegion(region));
		this.key=region;
		this.world=world;
		
		createBody(world,x,y,false);
	}
	
	private Flower(World world,String region){
		super(flowers.findRegion(region));
		this.key=region;
		this.world=world;
		
		createBody(world,(WIDTH/2)/PPM,(HEIGHT/2)/PPM,true);
	}
	
	public Flower(World world,float x,float y){
		this(world,rand(),x,y);
	}
	
	private void createBody(World world,float x,float y,boolean king){
		w=HEIGHT/(PPM*FACTOR);
		h=HEIGHT/(PPM*FACTOR);
		
		if(king){
			w/=2f;
			h/=2f;
		}
		
		setSize(w*PPM,h*PPM);
		setOrigin(w*PPM/2f,h*PPM/2f);
		
		BodyDef bDef=new BodyDef();
		bDef.type=BodyDef.BodyType.DynamicBody;
		bDef.position.set(x,y);
		
		CircleShape shape=new CircleShape();
		shape.setRadius(w/2);
		
		FixtureDef fDef=new FixtureDef();
		fDef.shape=shape;
		fDef.density=0;
		fDef.friction=0;
		fDef.restitution=1f;
		
		body=world.createBody(bDef);
		body.createFixture(fDef);
		
		shape.dispose();
	}
	
	public String getKey(){
		return key;
	}
	
	public void setKey(String key){
		this.key=key;
	}
	
	public void destroy(){
		world.destroyBody(body);
		getStage().getActors().removeValue(this,true);
	}
	
	public boolean equals(Flower flower){
		return key.equals(flower.getKey());
	}
	
	public void dispose(){
		body.getWorld().destroyBody(body);
	}
	
	public static String rand(){
		int x=rnd.nextInt(KEYS.length);
		int y=rnd.nextInt(KEYS.length);
		
		return KEYS[x]+KEYS[y];
	}
	
	public static Flower queen(World world,Array<Flower> flowers){
		int idx=rnd.nextInt(flowers.size);
		return new Flower(world,flowers.get(idx).getKey());
	}
	
	@Override
	public void draw(Batch batch,float delta){
		Vector2 pos=body.getPosition();
		super.setPosition((pos.x-w/2f)*PPM,(pos.y-h/2f)*PPM);
		super.draw(batch,delta);
		
	}
	

}
