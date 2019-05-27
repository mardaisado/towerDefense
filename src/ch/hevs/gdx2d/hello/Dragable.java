package ch.hevs.gdx2d.hello;

import java.awt.Point;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Dragable implements DrawableObject {
	BitmapImage image;
	float posX;
	float posY;
	float width;
	Object[] defense;
	
	public Dragable(Object[] defense,float posX,float posY,float width) {
		this.defense = defense;
		image = new BitmapImage((String)defense[0]);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
	}
	
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(posX,posY,0,width,width,image);
	}
	public boolean check(int x, int y) {
		if (x >= posX-width && x <= posX+width && y >= posY-width && y <= posY+width) {
			return true;
		}
		return false;
	}
}
