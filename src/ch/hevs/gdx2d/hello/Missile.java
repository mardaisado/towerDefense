package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Missile implements DrawableObject,DeleteObject {

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
	Vector<Ennemi> ennemi;
	int power;
	float radiusSq;

	Sound sound= Gdx.audio.newSound(Gdx.files.internal("data/sound/chop2.mp3"));
	
	public Missile(Point startPoint,Point endPoint,float scale,BitmapImage image,int power,float radius,Vector<Ennemi> ennemi) {
		this.pos = startPoint;
		this.endPoint = endPoint;
		this.scale = scale;
		this.image = image;
		this.ennemi=ennemi;
		this.power=power;
		this.radiusSq = radius*radius;
		steps = (int) (startPoint.distance(endPoint)/(Game.tileSize*32f));
		//System.out.println("steps : "+steps);
		if(steps > 0) {
			offsetX = (endPoint.x-pos.x)/steps;
			offsetY = (endPoint.y-pos.y)/steps;
		}

	    angle = (float) Math.toDegrees(Math.atan2(endPoint.y - pos.y, endPoint.x - pos.x))-90;

	    if(angle < 0){
	        angle += 360;
	    }
	}
	
	
	public void zoneDamage() {
		for (int i = 0; i < ennemi.size(); i++) {
			Ennemi target = ennemi.elementAt(i);
			if (pos.distanceSq(target.getPos()) < radiusSq) {
				target.giveDamage(power);
				sound.play(1.0f);
			}
		}
	}
	

	public boolean update(GdxGraphics g) {
		if (index < steps) {
			pos.translate(offsetX, offsetY);
		}
		else if(index < steps+anim){
			if (index == steps) {
				// explosion degats
				zoneDamage();
			}
			
			//animation
			image = new BitmapImage("data/assets/tank/PNG/Retina/explosion"+(index-steps+1)+".png");

		}
		else {
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
