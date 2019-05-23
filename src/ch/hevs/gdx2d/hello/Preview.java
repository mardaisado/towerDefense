package ch.hevs.gdx2d.hello;

import java.awt.Point;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Preview implements DrawableObject,UpdateObject {
	Point pos;
	private boolean visible = false;
	BitmapImage image = null;
	//BitmapImage ring = null;
	float scale;
	float radius = 100f;
	Color ringColor;
	
	public Preview() {
		pos = new Point(0,0);
		ringColor = new Color(0.5f, 0.5f, 0.5f, 0.4f);
		//ring = new BitmapImage("data/images/redRing.png");
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
	
	public void setImage(BitmapImage img,float scale) {
		image = img;
		this.scale = scale;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public void update(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		if(visible) {
			//g.drawFilledCircle(pos.x, pos.y, radius , ringColor) ;
			g.drawFilledCircle(pos.x, pos.y, radius , new Color(0.5f, 0.5f, 0.5f, 0.4f)) ;
			//g.drawAntiAliasedCircle(pos.x, pos.y, radius, new Color(0.5f, 0.5f, 0.5f, 0.4f));
			//g.drawAlphaPicture(pos.x,  pos.y, 0, scale, 0.4f, ring);
			g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, image);
		}
	}
}
