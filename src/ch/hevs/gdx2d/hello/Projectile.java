package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Projectile implements DrawableObject {

	Point endPoint;
	Point pos;
	float scale;
	BitmapImage image;
	int offsetX;
	int offsetY;
	int steps = 10;
	int anim = 5;
	int index = 0;
	boolean delete = false;
	float angle;

	public Projectile(Point startPoint,Point endPoint,float scale,BitmapImage image) {
		this.pos = startPoint;
		this.endPoint = endPoint;
		this.scale = scale;
		this.image = image;
		offsetX = (endPoint.x-pos.x)/steps;
		offsetY = (endPoint.y-pos.y)/steps;
	    angle = (float) Math.toDegrees(Math.atan2(endPoint.y - pos.y, endPoint.x - pos.x))-90;
	    
	    if(angle < 0){
	        angle += 360;
	    }
	}

	public boolean update(GdxGraphics g) {
		if (index < steps) {
			pos.translate(offsetX, offsetY);
		}
		else if(index < steps+anim){
			//animation
			image = new BitmapImage("data/assets/tank/PNG/Retina/explosion"+(index-steps+1)+".png");
		}
		else {
			// delete objects
			return true;
		}
		index++;
		return false;
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the projectile
		g.drawTransformedPicture(pos.x, pos.y, angle,scale/2, image);
	}
}
