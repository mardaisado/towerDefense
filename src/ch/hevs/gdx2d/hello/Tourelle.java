package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle extends Defense {

	BitmapImage base;
	BitmapImage movingPart;
	float angle;
	float scale;
	
	static final float RADIUS = 200;
	float rangeSq = 100; // => range de 1000
	float radius = 100;
	float cooldown = 1;
	BitmapImage[] assets;
	float dt = 0;
	
	final static String DEFENSE_NAME = "Tourelle tr�s jolie";
	final static int PRICE = 200;
	final static int DAMAGE = 20;
	
	
	public Tourelle(Point pos,float scale,BitmapImage base, BitmapImage movingPart,BitmapImage[] assets,Vector<Ennemi> ennemi,Vector<Projectile> projectile) {
		super(pos,ennemi,projectile,DEFENSE_NAME,PRICE);
		this.base = base;
		this.movingPart = movingPart;
		this.scale = scale;
		this.assets = assets;
		radius = RADIUS;
		rangeSq = RADIUS*RADIUS*Game.tileSize*Game.tileSize;
	}
	
	public Ennemi findEnnemi() {	
		Ennemi target = null;
		Ennemi tmp = null;
		for (int i = 0; i < ennemi.size(); i++) {
			if(pos.distanceSq(ennemi.get(i).pos) <= rangeSq) {
				tmp = ennemi.get(i);
				if(target == null) {
					target = tmp;
					angle = getAngle(target.pos);
				}
				if(((Mojojo)tmp).getProgress() > ((Mojojo)target).getProgress()) {
					target = tmp;
					angle = getAngle(target.pos);
				}
			}
		}
		return target;
	}
	
	public float getAngle(Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - pos.y, target.x - pos.x));

	    angle -= 90; //offset i dont know why :)
	    
	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the base
		g.drawTransformedPicture(pos.x, pos.y, 0,scale/2, base);
		
		//draw the "tourelle"
		g.drawTransformedPicture(pos.x, pos.y, angle, scale/2, movingPart);
	}

	@Override
	public void update(GdxGraphics g) {
		Ennemi target = null;
		//angle = (float) (angle + 5);

		// scan
		target = findEnnemi();
		
		Mojojo hello;
		Point preshot;
		// shot
		dt += Gdx.graphics.getDeltaTime();
		if(target != null) {
			// Process update
			if (dt > cooldown) {
				dt = 0;
				
				//Here
				
				hello = (Mojojo)(target);
				//preshot = updatePoint(new Point(hello.pos.x,hello.pos.y), scale, hello.speed, 10);
				preshot = hello.prediction(10);
				projectile.add(new Projectile(new Point(pos.x, pos.y), new Point(preshot.x, preshot.y), scale, assets[251],10,target));
				nbHits++;
			}
			//System.out.println(projectile.capacity());
			//projectile.addElement(new Projectile(new Point(0, 0), new Point(0, 0), scale, base));
			//projectile.add(new Projectile(new Point(0, 0), new Point(0, 0), scale, base));
		}
	}
	
	public static Point updatePoint(Point pos,float scale, int speed, int n) {
		// Code dégeu, sorry auré, t'avais ka trouver comment mettre des propreties
			
		return pos;
	}
	
}
