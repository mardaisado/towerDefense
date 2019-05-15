package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle extends Defense {

	BitmapImage base;
	BitmapImage movingPart;
	float angle;
	float scale ;
	
	
	public Tourelle(Point pos,float scale,BitmapImage base, BitmapImage movingPart) {
		super(pos);
		this.base = base;
		this.movingPart = movingPart;
		this.scale = scale;
		
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the base
		g.drawTransformedPicture(pos.x, pos.y, 0,scale/2, base);
		
		//draw the "tourelle"
		g.drawTransformedPicture(pos.x, pos.y, angle, scale/2, movingPart);
	}

	@Override
	public void update(GdxGraphics g) {
		angle = (float) (angle + 5);

		
	}

}
