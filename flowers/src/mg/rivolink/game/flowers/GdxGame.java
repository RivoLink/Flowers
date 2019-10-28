package mg.rivolink.game.flowers;

import com.badlogic.gdx.*;
import com.badlogic.gdx.physics.box2d.*;
import mg.rivolink.game.flowers.data.*;
import mg.rivolink.game.flowers.screens.*;

import static mg.rivolink.game.flowers.viewports.GameViewport.*;

public class GdxGame extends Game {
	
	public void create(){
		Data.load();
		getInstance().loadBackground();
		
		setScreen(new MainScreen(this));
		//setScreen(new LevelScreen(this));
	}

	@Override
	public void dispose(){
		super.dispose();
		getInstance().disposeBackground();
		
	}
	
	public static void createBorder(World world){

		PolygonShape shape=new PolygonShape();
		shape.setAsBox((WIDTH/2f)/PPM,1/PPM);

		FixtureDef fDef=new FixtureDef();
		fDef.density=0;
		fDef.shape=shape;

		BodyDef bDef=new BodyDef();
		bDef.type=BodyDef.BodyType.StaticBody;
		bDef.position.set((WIDTH/2f)/PPM,0);
		Body body=world.createBody(bDef);
		body.createFixture(fDef);

		bDef=new BodyDef();
		bDef.type=BodyDef.BodyType.StaticBody;
		bDef.position.set((WIDTH/2f)/PPM,HEIGHT/PPM);
		body=world.createBody(bDef);
		body.createFixture(fDef);

		shape.setAsBox(1/PPM,(HEIGHT/2f)/PPM);

		fDef=new FixtureDef();
		fDef.density=0;
		fDef.shape=shape;

		bDef=new BodyDef();
		bDef.type=BodyDef.BodyType.StaticBody;
		bDef.position.set(0,(HEIGHT/2f)/PPM);
		body=world.createBody(bDef);
		body.createFixture(fDef);

		bDef=new BodyDef();
		bDef.type=BodyDef.BodyType.StaticBody;
		bDef.position.set(WIDTH/PPM,(HEIGHT/2f)/PPM);
		body=world.createBody(bDef);
		body.createFixture(fDef);

		shape.dispose();
	}
}
