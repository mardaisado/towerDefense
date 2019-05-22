package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Dragable {
	BitmapImage image;
	float posX;
	float posY;
	float width;
	
	public Dragable(String url,float posX,float posY,float width) {
		image = new BitmapImage(url);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
	}
	
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(posX,posY,0,width,width,image);
	}
}
