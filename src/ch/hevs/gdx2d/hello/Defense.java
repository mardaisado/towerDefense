package ch.hevs.gdx2d.hello;


import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Defense implements DrawableObject,UpdateObject {

	Point pos;
	Vector<Ennemi> ennemi;
	
	public Defense(Point pos, Vector<Ennemi> ennemi) {
		this.pos = pos;
		this.ennemi = ennemi;
	}
	
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract void update(GdxGraphics g);
}
