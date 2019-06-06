package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle4 extends Defense{

	// CONSTANT OF THE DEFENSE
	final static String DEFENSE_NAME = "Tourelle qui tire beaucoup";
	final static int PRICE = 400;
	final static int DAMAGE = 20;
	final static float RADIUS = 200;
	final static float COOLDOWN = 0.3f;
	final static String BASE_URL = "data/assets/PNG/Retina/towerDefense_tile180.png";
	//final static String MOVINGPART_URL = "data/assets/PNG/Retina/towerDefense_tile249.png";
	final static String PROJECTILE_URL = "data/assets/PNG/Retina/towerDefense_tile273.png";
	
	// VARIABLES OF THIS DEFENSE
	BitmapImage base;
	BitmapImage movingPart;
	BitmapImage projectileBtp;
	
	float angle;
	
	public Tourelle4(Point pos,Vector<Ennemi> ennemi,Vector<Object> projectile) {
		super(pos,ennemi,projectile,DEFENSE_NAME,PRICE,RADIUS,COOLDOWN);
		
		//SET IMAGE
		base = new BitmapImage(BASE_URL);
		//movingPart = new BitmapImage(MOVINGPART_URL);
		projectileBtp = new BitmapImage(PROJECTILE_URL);
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

	@Override
	public void draw(GdxGraphics g) {
		//draw the base
		g.drawTransformedPicture(pos.x, pos.y, 0,Game.tileSize/2, base);
		
		//draw the "tourelle"
		//g.drawTransformedPicture(pos.x, pos.y, angle, Game.tileSize/2, movingPart);
	}

	@Override
	public void update(GdxGraphics g) {
		// scan
		Ennemi target = findEnnemi();
		
		// shot
		dt += Game.FRAME_TIME;
		if(target != null) {	// NO ENNEMI FOUND
			// Process update
			if (dt > cooldown) { // COOLDOWN
				dt = 0;
				
				// SHOOT !!!
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 0, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 45, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 90, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 135, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 180, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 225, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 270, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				projectile.add(new RangeProjectile(new Point(pos.x, pos.y), 315, Game.tileSize, projectileBtp,10,RADIUS,ennemi));
				//projectile.add(new Projectile(new Point(pos.x, pos.y), new Point(((Mojojo) (target)).prediction(10)), Game.tileSize, projectileBtp,10,(Mojojo)(target)));
				nbHits++;
			}
		}
	}
}
