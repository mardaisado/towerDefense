package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
/**
 * Tourelle2 : the second type of defense, shoot only on one ennemy but with zone damage
 */
public class Tourelle2 extends Defense{

	// CONSTANT OF THE DEFENSE
	final static String DEFENSE_NAME = "Tourelle moins jolie";
	final static int PRICE = Game.defenseProperties[1].price;
	final static int DAMAGE = 5;
	final static float RADIUS = Game.defenseProperties[1].radius;
	final static float COOLDOWN = 0.6f;
	final static String BASE_URL = "data/assets/PNG/Retina/towerDefense_tile182.png";
	final static String MOVINGPART_URL = "data/assets/PNG/Retina/towerDefense_tile250.png";
	final static String PROJECTILE_URL = "data/assets/PNG/Retina/towerDefense_tile300.png";
	final static int[] UPGRADE_COST= {PRICE,2000,3000};
	
	// VARIABLES OF THIS DEFENSE
	private BitmapImage base;
	private BitmapImage movingPart;
	private BitmapImage projectileBtp;
	
	private float angle;
	
	public Tourelle2(Point pos,Vector<Ennemi> ennemi,Vector<Object> projectile) {
		super(pos,ennemi,projectile,DEFENSE_NAME,RADIUS,COOLDOWN,UPGRADE_COST,DAMAGE);
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
	public boolean update(GdxGraphics g) {
		// scan
		Ennemi target = findEnnemi();
		
		// shot
		dt += Game.FRAME_TIME;
		if(target != null) {	// NO ENNEMI FOUND
			// Process update
			if (dt > cooldown) { // COOLDOWN
				dt = 0;
				
				// SHOOT !!!
				projectile.add(new Missile(new Point(pos.x, pos.y), new Point(((EnnemiWalk) (target)).prediction(10)), Game.tileSize, projectileBtp,damage,80,ennemi));
				nbHits++;
			}
		}
		if (isSelled()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Func that find an ennemy to shoot
	 * 
	 * @return the target to shoot
	 */
	private Ennemi findEnnemi() {	
		Ennemi target = null;
		Ennemi tmp = null;
		for (int i = 0; i < ennemi.size(); i++) {
			if(pos.distanceSq(ennemi.get(i).getPos()) <= rangeSq) {
				tmp = ennemi.get(i);
				if(target == null) {
					target = tmp;
					angle = getAngle(target.getPos());
				}
				if(((EnnemiWalk)tmp).getProgress() > ((EnnemiWalk)target).getProgress()) {
					target = tmp;
					angle = getAngle(target.getPos());
				}
			}
		}
		return target;
	}

	/**
	 * Func that upgrade the defense
	 * 
	 * @param leve the level to upgrade the defense
	 */
	@Override
	protected void upgradeAction(int level) {
		switch (level) {
		//LEVEL 2
		case 2:
			radius = radius*1.2f;
			rangeSq = rangeSq*1.2f*1.2f;
			damage = (int)(damage * 1.2f);
			cooldown = cooldown*0.7f;
			break;
		//LEVEL 3
		case 3:
			radius = radius*1.2f;
			rangeSq = rangeSq*1.2f*1.2f;
			damage = (int)(damage * 1.2f);
			cooldown = cooldown*0.7f;
			break;

		default:
			break;
		}
	}
}
