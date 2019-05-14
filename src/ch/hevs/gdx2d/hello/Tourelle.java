package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle extends Defense {

	BitmapImage base;
	
	public Tourelle(Point pos, BitmapImage base) {
		super(pos);
		this.base = base;
		
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(pos.x, pos.y, 0, (float) 0.2, base);
		
	}

	@Override
	public void update(GdxGraphics g) {
		
		
	}

}
