package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Mojojo extends Ennemi {

	BitmapImage base;
	
	int[][] position = {
			{64,64},{120,120},{180,180},{240,240},{260,260},{120,120} 
	};
	
	public Mojojo(Point pos, BitmapImage base) {
		super(pos);
		this.base = base;
		
	}
	// Use for find next position of the ennemis (propreties of tile)
	public Point findNextPosition(int tempo)
	{
		
		return new Point((position[tempo][1]),(position[tempo][2]));
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(pos.x, pos.y, 0, (float) 0.2, base);
		
	}

	@Override
	public void update(GdxGraphics g) {
		
		
		
	}

}
