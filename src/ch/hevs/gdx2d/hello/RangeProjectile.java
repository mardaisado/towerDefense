package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class RangeProjectile implements DrawableObject,DeleteObject{

	Point startPoint;
	Point pos;
	float scale;
	BitmapImage image;
	int offsetX;
	int offsetY;
	int steps = 10;
	boolean delete = false;
	float angle;
	Vector<Ennemi> ennemi;
	int power;
	float rangeSq;
	float radiusSq;
	static final float RADIUS_HITBOX = 30f;

	public RangeProjectile(Point startPoint,float angle,float scale,BitmapImage image,int power,float range,Vector<Ennemi> ennemi) {
		this.pos = startPoint;
		this.startPoint = new Point(startPoint);
		this.scale = scale;
		this.image = image;
		this.ennemi=ennemi;
		this.power=power;
		this.rangeSq = range*range*Game.tileSize*Game.tileSize;
		this.radiusSq = RADIUS_HITBOX*RADIUS_HITBOX*Game.tileSize*Game.tileSize;
		this.angle = angle;
//		steps = (int) (startPoint.distance(endPoint)/(Game.tileSize*32f));
		//System.out.println("steps : "+steps);
		offsetX = (int)( Math.cos(Math.toRadians(angle+90))*(Game.tileSize*32f));
		offsetY = (int)( Math.sin(Math.toRadians(angle+90))*(Game.tileSize*32f));

//	    angle = (float) Math.toDegrees(Math.atan2(endPoint.y - pos.y, endPoint.x - pos.x))-90;
//
//	    if(angle < 0){
//	        angle += 360;
//	    }
	}
	
	public void checkDamage() {
		for (int i = 0; i < ennemi.size(); i++) {
			Ennemi target = ennemi.elementAt(i);
			if (pos.distanceSq(target.getPos()) < radiusSq) {
				target.giveDamage(power);
			}
		}
	}

	public boolean update(GdxGraphics g) {
		pos.translate(offsetX, offsetY);
		if (pos.distanceSq(startPoint) < rangeSq) {
			checkDamage();
		}
		else {
			return true;
		}
		return false;
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the projectile
		g.drawTransformedPicture(pos.x, pos.y, angle,scale/2, image);
	}
}
