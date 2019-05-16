package ch.hevs.gdx2d.hello;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Mojojo extends Ennemi {

	BitmapImage base;
	
	int temp=0;
	
	int speed=2;
	
	
	
	
	float scale;
	int[][] position = {
			{64,64},{120,120},{180,180},{240,240},{260,260},{120,120} 
	};
	
	public Mojojo(Point pos,float scale, BitmapImage base) {
		super(pos);
		this.base = base;
		this.scale = scale;
		
		changeSpeedForScale();
	}
	private void changeSpeedForScale()
	{
		speed = (int) (speed/scale);
		
		
	}
	// Use for find next position of the ennemis (propreties of tile)
	// La fonction va regarder autour d'elle les pixels walkable et prendre le suivant 
	public Point findNextPosition(int tempo)
	{		
		return new Point((position[tempo][0]),(position[tempo][1]));
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, base);
		
	}

	@Override
	public void update(GdxGraphics g) {
		
		// Code dégeu, sorry auré, t'avais ka trouver comment mettre des propreties
		
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

}
