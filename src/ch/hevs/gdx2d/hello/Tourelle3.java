package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle3 extends Defense{
	// CONSTANT OF THE DEFENSE
	final static String DEFENSE_NAME = "Tourelle qui tire loin";
	final static int PRICE = 300;
	final static int DAMAGE = 5;
	final static float RADIUS = 300;
	final static float COOLDOWN = 0.4f;
	final static String BASE_URL = "data/assets/PNG/Retina/towerDefense_tile181.png";
	final static String MOVINGPART_URL = "data/assets/PNG/Retina/towerDefense_tile203.png";
	final static String PROJECTILE_URL = "data/assets/PNG/Retina/towerDefense_tile275.png";
	
	// VARIABLES OF THIS DEFENSE
	BitmapImage base;
	BitmapImage movingPart;
	BitmapImage projectileBtp;
	
	float angle;
	
	public Tourelle3(Point pos,Vector<Ennemi> ennemi,Vector<Projectile> projectile) {
		super(pos,ennemi,projectile,DEFENSE_NAME,PRICE,RADIUS,COOLDOWN);
		base = new BitmapImage(BASE_URL);
		movingPart = new BitmapImage(MOVINGPART_URL);
		projectileBtp = new BitmapImage(PROJECTILE_URL);
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the base
		g.drawTransformedPicture(pos.x, pos.y, 0,Game.tileSize/2, base);
		
		//draw the "tourelle"
		g.drawTransformedPicture(pos.x, pos.y, angle, Game.tileSize/2, movingPart);
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
				projectile.add(new Projectile(new Point(pos.x, pos.y), new Point(((Mojojo) (target)).prediction(10)), Game.tileSize, projectileBtp,10,(Mojojo)(target)));
				nbHits++;
			}
		}
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
}
