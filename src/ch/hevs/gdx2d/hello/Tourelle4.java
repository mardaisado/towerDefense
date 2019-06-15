package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
/**
 * Tourelle4 : the fourth type of defense, shoot on 8 direction when an ennemy is detected
 */
public class Tourelle4 extends Defense{

	// CONSTANT OF THE DEFENSE
	final static String DEFENSE_NAME = "Tourelle qui tire beaucoup";
	final static int PRICE = Game.defenseProperties[3].price;
	final static int DAMAGE = 3;
	final static float RADIUS = Game.defenseProperties[3].radius;
	final static float COOLDOWN = 0.3f;
	final static String BASE_URL = "data/assets/PNG/Retina/towerDefense_tile180.png";
	final static String PROJECTILE_URL = "data/assets/PNG/Retina/towerDefense_tile273.png";
	final static int[] UPGRADE_COST= {PRICE,2000,3000};
	
	// VARIABLES OF THIS DEFENSE
	private BitmapImage base;
	private BitmapImage projectileBtp;
	
	
	public Tourelle4(Point pos,Vector<Ennemi> ennemi,Vector<Object> projectile) {
		super(pos,ennemi,projectile,DEFENSE_NAME,RADIUS,COOLDOWN,UPGRADE_COST,DAMAGE);
		
		base = new BitmapImage(BASE_URL);
		projectileBtp = new BitmapImage(PROJECTILE_URL);
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
				}
				if(((EnnemiWalk)tmp).getProgress() > ((EnnemiWalk)target).getProgress()) {
					target = tmp;
				}
			}
		}
		return target;
	}

	@Override
	public void draw(GdxGraphics g) {
		
		g.drawTransformedPicture(pos.x, pos.y, 0,Game.tileSize/2, base);
	}

	@Override
	public boolean update(GdxGraphics g) {
		Ennemi target = findEnnemi();
		
		dt += Game.FRAME_TIME;
		if(target != null) {	// NO ENNEMI FOUND
			// Process update
			if (dt > cooldown) { // COOLDOWN
				dt = 0;
				
				// SHOOT !!!
				for (int i = 0; i < 360; i=i+45) {
					projectile.add(new RangeProjectile(new Point(pos.x, pos.y), i, Game.tileSize, projectileBtp,damage,RADIUS,ennemi));
					
				}

				nbHits++;
			}
		}
		if (isSelled()) {
			return true;
		}
		return false;
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
