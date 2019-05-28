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
	Ennemi target;
	int power;

	public Projectile(Point startPoint,Point endPoint,float scale,BitmapImage image,int power,Ennemi target) {
		this.pos = startPoint;
		this.endPoint = endPoint;
		this.scale = scale;
		this.image = image;
		this.target=target;
		this.power=power;
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

	public boolean update(GdxGraphics g) {
		if (index < steps) {
			pos.translate(offsetX, offsetY);
		}
		else if(index < steps+anim){
			//animation
			image = new BitmapImage("data/assets/tank/PNG/Retina/explosion"+(index-steps+1)+".png");

		}
		else {
			System.out.println("shooted ");
			System.out.println(target.getHP());
			if(!target.giveDamage(power))
			{
				Game.money.addMoneyCount(target.reward);
			}
			
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
