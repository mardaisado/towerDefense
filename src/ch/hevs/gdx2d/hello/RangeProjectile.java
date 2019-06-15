package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * RangeProjectile : this create an projectile that make damage at the ennemi the projectile is pearcing
 */
public class RangeProjectile implements DrawableObject,DeleteObject{

	private Point startPoint;

	private float rangeSq;
	private float radiusSq;
	static final float RADIUS_HITBOX = 30f;
	
	private Point pos;
	private float angle;
	
	private Sound sound= Gdx.audio.newSound(Gdx.files.internal("data/sound/chop2.mp3"));
	
	private BitmapImage image;
	private float scale;
	static final int ANIM = 5;
	
	private int power;
	
	private int offsetX;
	private int offsetY;
	
	private Vector<Ennemi> ennemi;
	
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

		offsetX = (int)( Math.cos(Math.toRadians(angle+90))*(Game.tileSize*32f));
		offsetY = (int)( Math.sin(Math.toRadians(angle+90))*(Game.tileSize*32f));

	}
	
	/**
	 * Func that check if there is an ennemi where the projectile is
	 */
	private void checkDamage() {
		for (int i = 0; i < ennemi.size(); i++) {
			Ennemi target = ennemi.elementAt(i);
			if (pos.distanceSq(target.getPos()) < radiusSq) {
				target.giveDamage(power);
				sound.play(1.0f);
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
