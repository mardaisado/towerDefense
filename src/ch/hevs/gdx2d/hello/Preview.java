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
	BitmapImage ringGrey;
	BitmapImage ringRed;
	//BitmapImage ring = null;
	float scale;
	float radius = 100f;
	float circleScale;
//	Color ringGrey;
//	Color ringRed;
	boolean placeable = true;
	
	public Preview() {
		pos = new Point(0,0);
//		ringGrey = new Color(0.5f, 0.5f, 0.5f, 0.4f);
//		ringRed = new Color(1f, 0f, 0f, 0.4f);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		if(visible) {
			//g.drawFilledCircle(pos.x, pos.y, radius , ringColor) ;
			if (placeable) {
				//g.drawFilledCircle(pos.x, pos.y, radius*Game.tileSize , ringGrey);
				//g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, image);
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringGrey);
			}
			else {
				//g.drawFilledCircle(pos.x, pos.y, radius*Game.tileSize , ringRed);
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringRed);
			}
			
			//g.drawAntiAliasedCircle(pos.x, pos.y, radius, new Color(0.5f, 0.5f, 0.5f, 0.4f));
			//g.drawAlphaPicture(pos.x,  pos.y, 0, scale, 0.4f, ring);
			g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, image);
		}
	}
}
