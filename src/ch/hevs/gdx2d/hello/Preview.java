package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Preview implements DrawableObject,UpdateObject {
	Point pos;
	
	private boolean visible = false;
	
	BitmapImage image = null;
	BitmapImage ringGrey;
	BitmapImage ringRed;
	
	float scale;
	float radius = 100f;
	float circleScale;
	
	boolean placeable = true;
	
	public Preview() {
		pos = new Point(0,0);
		ringGrey = new BitmapImage("data/images/greyCircle.png");
		ringRed = new BitmapImage("data/images/redCircle.png");
	}
	
	public void move(int x, int y) {
		pos.x = x;
		pos.y = y;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	
	public void setPlaceable(boolean b) {
		placeable = b;
	}
	
	public void setImage(BitmapImage img,float scale) {
		image = img;
		this.scale = scale;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
		circleScale = 2*radius*Game.tileSize/ringGrey.getImage().getHeight();
	}

	@Override
	public void update(GdxGraphics g) {
		
	}

	@Override
	public void draw(GdxGraphics g) {
		if(visible) {
			if (placeable) {
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringGrey);
			}
			else {
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringRed);
			}			
			g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, image);
		}
	}
}
