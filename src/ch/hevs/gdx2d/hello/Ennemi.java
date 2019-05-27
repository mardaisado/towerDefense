package ch.hevs.gdx2d.hello;


import java.awt.Point;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Ennemi implements DrawableObject,UpdateObject {

	Point pos;
	
	public Ennemi(Point pos) {
		
	}
	
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract void update(GdxGraphics g);
}