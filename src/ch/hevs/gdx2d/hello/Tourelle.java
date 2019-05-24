package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Tourelle extends Defense {

	BitmapImage base;
	BitmapImage movingPart;
	float angle;
	float scale;
	float rangeSq = 1000000; // => range de 1000
	float radius = 1000;
	float cooldown = 1;
	BitmapImage[] assets;
	float dt = 0;
	
	
	public Tourelle(Point pos,float scale,BitmapImage base, BitmapImage movingPart,BitmapImage[] assets,Vector<Ennemi> ennemi,Vector<Projectile> projectile) {
		super(pos,ennemi,projectile);
		this.base = base;
		this.movingPart = movingPart;
		this.scale = scale;
		this.assets = assets;
		
	}
	
	public Ennemi findEnnemi() {	
		Ennemi target = null;
		Ennemi tmp = null;
		for (int i = 0; i < ennemi.size(); i++) {
			if(pos.distanceSq(ennemi.get(i).pos) <= rangeSq) {
				tmp = ennemi.get(i);
				if(target == null) {
					target = tmp;
					angle = getAngle(target.pos);
				}
				if(((Mojojo)tmp).getProgress() > ((Mojojo)target).getProgress()) {
					target = tmp;
					angle = getAngle(target.pos);
				}
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
		Ennemi target = null;
		//angle = (float) (angle + 5);

		// scan
		target = findEnnemi();
		
		Mojojo hello;
		Point preshot;
		// shot
		dt += Gdx.graphics.getDeltaTime();
		if(target != null) {
			// Process update
			if (dt > cooldown) {
				dt = 0;
				hello = (Mojojo)(target);
				preshot = updatePoint(new Point(hello.pos.x,hello.pos.y), scale, hello.speed, 10);
				projectile.add(new Projectile(new Point(pos.x, pos.y), new Point(preshot.x, preshot.y), scale, assets[251]));
			}
			//System.out.println(projectile.capacity());
			//projectile.addElement(new Projectile(new Point(0, 0), new Point(0, 0), scale, base));
			//projectile.add(new Projectile(new Point(0, 0), new Point(0, 0), scale, base));
		}
	}
	
	public static Point updatePoint(Point pos,float scale, int speed, int n) {
		// Code dégeu, sorry auré, t'avais ka trouver comment mettre des propreties
		int temp = 0;
		for(int i = 0; i < n; i++) {
			if(pos.x>=(int)((8.5)*scale*64f) )
			{
				temp=1;			
			} 
			if(pos.y<=(int)((7.5)*scale*64f))
			{
				temp=2;			
			}
	
			
			switch (temp) {
			
			case 0:
				pos.x=(pos.x+speed);
					
						
				break;
				
			case 1:
				pos.y=(pos.y-speed);		
						
				break;
	
			case 2:
				
				pos.x=(int) (pos.x+speed);
						
				break;
	
			default:
				break;
			}
		}
		
		return pos;
	}
	
}
