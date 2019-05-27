package ch.hevs.gdx2d.hello;


import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Defense implements DrawableObject,UpdateObject {

	Point pos;
	Vector<Ennemi> ennemi;
	Vector<Projectile> projectile;
	int level = 1;
	int[] cost= {1000,2000,3000};
	int nbHits = 0;
	String defenseName;
	int price;
	
	public Defense(Point pos, Vector<Ennemi> ennemi,Vector<Projectile> projectile,String defenseName,int price) {
		this.pos = pos;
		this.ennemi = ennemi;
		this.projectile = projectile;
		this.defenseName = defenseName;
		this.price = price;
	}

	public int nextUpgradePrice() {
		return cost[level];
	}
	
	public void upgrade() {
		if(level<3) {
			Game.money.takeOffMoneyCount(nextUpgradePrice());
			level++;
		}
	}
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract void update(GdxGraphics g);
}
