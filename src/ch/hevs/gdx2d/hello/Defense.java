package ch.hevs.gdx2d.hello;


import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * Class for all the defense
 */
public abstract class Defense implements DrawableObject,DeleteObject {

	protected Point pos;
	
	protected Vector<Ennemi> ennemi;
	protected Vector<Object> projectile;
	
	protected int level = 1;
	protected int[] cost;		// cost of every levels
	protected String defenseName;
	protected int price;
	protected int damage;
	
	protected int nbHits = 0;
	
	private boolean selled = false;
	
	protected float rangeSq;	// range square (take less time to check)
	protected float radius;
	
	protected int radiusCollision = 30;
	
	protected float dt = 0;
	protected float cooldown;
	
	public Defense(Point pos, Vector<Ennemi> ennemi,Vector<Object> projectile,String defenseName,float radius,float cooldown,int[] cost,int damage) {
		this.pos = pos;
		this.ennemi = ennemi;
		this.projectile = projectile;
		this.defenseName = defenseName;
		this.price = cost[0];
		this.radius = radius;
		this.rangeSq = radius*radius*Game.tileSize*Game.tileSize;
		this.cooldown = cooldown;
		this.cost = cost;
		this.damage = damage;
	}
	
	/**
	 * func that return the next upgrade price
	 *
	 * @return The next upgrade price
	 */
	public int nextUpgradePrice() {
		return cost[level];
	}
	
	/**
	 * func that update the level and remove the defense price
	 * in the money counter. 
	 */
	public void upgrade() {
		if(level<Game.MAX_LEVEL) {
			Game.money.takeOffMoneyCount(nextUpgradePrice());
			level++;
			upgradeAction(level);
		}
	}
	
	/**
	 * func that add an amount of the purchase price in the money counter and
	 * tell to the update func to delete the defense.
	 */
	public void sell() {
		Game.money.addMoneyCount((int) (price*Game.PERCENTAGEOFREWARD));
		selled = true;	// tell to the update func to delete the defense
	}
	
	/**
	 * Func that read the selled state
	 *
	 * @return True if the defense have to be delete / false otherwise
	 */
	public boolean isSelled() {
		return selled;
	}
	
	/**
	 * Func that check if an other defense can be put near this defense
	 *
	 * @param x, y : mouse position
	 * @param radius : radius of the other defense
	 *
	 * @return True if the other defense has the good distance / false otherwise
	 */
	public boolean checkCollision(int x,int y,int radius) {
		
		if (pos.distance(x, y)< ((radius + radiusCollision)*Game.tileSize)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Func that return the angle between this defense and a target.
	 *
	 * @param target : the target the func compare to.
	 *
	 * @return True if the other defense has the good distance / false otherwise
	 */
	protected float getAngle(Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - pos.y, target.x - pos.x));

	    angle -= 90; //fix the false orientation
	    
	    // no negativ angle
	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	/**
	 * Func that change the defense when an upgrade is made (defined in every defense)
	 *
	 * @param level : the level to upgrade to.
	 */
	protected abstract void upgradeAction(int level);
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract boolean update(GdxGraphics g);
}
