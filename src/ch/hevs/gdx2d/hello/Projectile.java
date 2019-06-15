package ch.hevs.gdx2d.hello;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * Projectile : this create an projectile that make damage only at one ennemi
 */
public class Projectile implements DrawableObject,DeleteObject {

	Ennemi target;
	
	private Point pos;
	private float angle;
	
	private Sound sound= Gdx.audio.newSound(Gdx.files.internal("data/sound/chop2.mp3"));
	
	private BitmapImage image;
	private float scale;
	static final int ANIM = 5;
	
	private int power;
	
	private int offsetX;
	private int offsetY;
	private int steps;
	private int index = 0;
	
	public Projectile(Point startPoint,Point endPoint,float scale,BitmapImage image,int power,Ennemi target) {
		this.pos = startPoint;
		this.scale = scale;
		this.image = image;
		this.target=target;
		this.power=power;
		steps = (int) (startPoint.distance(endPoint)/(Game.tileSize*32f));
		if(steps > 0) {
			offsetX = (endPoint.x-pos.x)/steps;
			offsetY = (endPoint.y-pos.y)/steps;
		}
		
	    angle = (float) Math.toDegrees(Math.atan2(endPoint.y - pos.y, endPoint.x - pos.x))-90;

	    if(angle < 0){
	        angle += 360;
	    }
	}

	public boolean update(GdxGraphics g) {
		if (index < steps) {
			pos.translate(offsetX, offsetY); // move projectile
		}
		else if(index < steps+ANIM){
			//animation
			image = new BitmapImage("data/assets/tank/PNG/Retina/explosion"+(index-steps+1)+".png");

		}
		else {
			target.giveDamage(power);
			
			sound.play(1.0f);
			
			return true;	// delete projectile
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
