package ch.hevs.gdx2d.hello;


import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Defense implements DrawableObject,UpdateObject {

	Point pos;
	Vector<Ennemi> ennemi;
	Vector<Object> projectile;
	int level = 1;
	int[] cost;
	int nbHits = 0;
	String defenseName;
	int price;
	int damage;
	
	float rangeSq;
	float radius;
	
	float dt = 0;
	float cooldown;
	
	int radiusCollision = 30;
	
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

	public int nextUpgradePrice() {
		return cost[level];
	}
	
	public void upgrade() {
		if(level<Game.MAX_LEVEL) {
			Game.money.takeOffMoneyCount(nextUpgradePrice());
			level++;
			upgradeAction(level);
		}
	}
	
	public boolean checkCollision(int x,int y,int radius) {
		
		if (pos.distance(x, y)< ((radius + radiusCollision)*Game.tileSize)) {
			return true;
		}
		
		return false;
	}
	
	public float getAngle(Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - pos.y, target.x - pos.x));

	    angle -= 90; //offset i dont know why :)
	    
	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	public abstract void upgradeAction(int level);
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract void update(GdxGraphics g);
}
