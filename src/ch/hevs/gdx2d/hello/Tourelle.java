package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle extends Defense {

	BitmapImage base;
	BitmapImage movingPart;
	float angle;
	float scale;
	float rangeSq = 1000000; // => range de 100
	
	
	public Tourelle(Point pos,float scale,BitmapImage base, BitmapImage movingPart,Vector<Ennemi> ennemi) {
		super(pos,ennemi);
		this.base = base;
		this.movingPart = movingPart;
		this.scale = scale;
		
	}
	
	public Ennemi findEnnemi() {	
		Ennemi target = null;
		for (int i = 0; i < ennemi.size(); i++) {
			if(pos.distanceSq(ennemi.get(i).pos) <= rangeSq) {
				target = ennemi.get(i);
				angle = getAngle(target.pos);
				break;
			}
		}
		return target;
	}
	
	public float getAngle(Point target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - pos.y, target.x - pos.x));

	    angle -= 90; //offset i dont know why :)
	    
	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	@Override
	public void draw(GdxGraphics g) {
		//draw the base
		g.drawTransformedPicture(pos.x, pos.y, 0,scale/2, base);
		
		//draw the "tourelle"
		g.drawTransformedPicture(pos.x, pos.y, angle, scale/2, movingPart);
	}

	@Override
	public void update(GdxGraphics g) {
		Ennemi target;
		//angle = (float) (angle + 5);

		// scan
		target = findEnnemi();
		// shot
		
		
	}

}
