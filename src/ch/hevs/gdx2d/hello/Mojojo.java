package ch.hevs.gdx2d.hello;

import java.awt.Point;

import org.lwjgl.Sys;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Mojojo extends Ennemi {

	BitmapImage base;
	
	int speed=2;
		
	TiledMapTileLayer tiledLayer;
	
	int progress=0;
		
	TiledMap map;
	
	float scale;
	
	int directionSave;
	
	boolean changeDir=false;

	float angle = 0;
	
	Point posOld;
	
	public Mojojo(float scale, BitmapImage base, TiledMap map, int hp, int reward) {
				
		super(hp, reward);
		this.tiledLayer=(TiledMapTileLayer) map.getLayers().get(0);
		this.base = base;
		this.scale = scale;
		this.map=map;
		goStart();
 
		directionSave= (int) Utils.getTile(new Point((int) (pos.x/scale),(int) (pos.y/scale)), (TiledMapTileLayer) (map.getLayers().get(0)) ).getProperties().get("direction");		changeSpeedForScale();
	}
	
	private void changeSpeedForScale()
	{
		speed = (int) (speed/scale);
	}
		
	private int getDirection(TiledMapTile tile) {
		if (tile == null)
			return 0;
			
		if(tile.getProperties().get("direction")!=null)
		{
		
			Object test2 = tile.getProperties().get("direction");

			return (int) (test2);
		}
		
		return 0;
	}
	
	private Point adapatPos(int dir)
	{		
		switch (dir) {
		case 1:
				return new Point( ((int) ((pos.x/scale/speed -0.5) *scale*speed) ) , pos.y);
				
		case 2:
				return new Point( (pos.x ) ,(int) ((pos.y/scale -0.5) *scale));
		
		case 3:
				return new Point( ((int) ((pos.x/scale/speed +0.5) *scale*speed) ) , pos.y);

		case 4:
				return new Point( pos.x  ,(int) ((pos.y/scale/speed +0.5) *scale*speed));
		}
		
		return pos;
			
	}
	
	public void goStart()
	{
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if((Utils.getTile(new Point((int)((i+0.5)*64f),(int)((j+0.5)*64f)), tiledLayer).getProperties().get("start") != null))
				{					
					if((Utils.getTile(new Point((int)((i+0.5)*64f),(int)((j+0.5)*64f)), tiledLayer).getProperties().get("start",boolean.class)) == true)
					{	
						pos = new Point((int)((i+0.5)*scale*64f),(int)((j+0.5)*scale*64f));
				  		return;
					}
				}
			} 
			
		}
	
	}
	
	public Point goNextPosition(int direction, Point position)
	{
		switch (direction) {
		case 1:
				angle=90;
				return new Point(position.x + ((int)((0)*scale*speed)), position.y + ((int)((1)*scale*speed)) );
				
		case 2:
				angle=180;
				return new Point(position.x + ((int)((0)*scale*speed)), position.y + ((int)((-1)*scale*speed)) );
				
		case 3:
				angle=270;
				return new Point(position.x + ((int)((1)*scale*speed)), position.y + ((int)((0)*scale*speed)) );
					
		case 4:
				angle=360;
				return new Point(position.x + ((int)((-1)*scale*speed)), position.y + ((int)((0)*scale*speed)) );
					
		default:
			return position;
		}
		
	}

	public boolean checkDeath()
	{
		if(hp>0)
		{
			return true;
		}
		
		return false;
	}
	
	private Point getOffset(int direction, Point position)
	{
		switch (direction) {
		case 1:
				return new Point( (int) (position.x + (0.5 * scale * 64f)) , position.y);
				
		case 2:
				return new Point( (position.x ) ,(int) (position.y + (0.5 * scale * 64f)));
		
		case 3:
				return new Point( (int) (position.x - (0.5 * scale * 64f)) , position.y);

		case 4:
				return new Point( position.x  ,(int) (position.y - (0.5 * scale * 64f)));
		}
		
		return position;
	}
	
	public Point prediction(int updateInFutur)
	{
		Point output = pos;
		
		int directionSaveTemp=directionSave;
		int direction =0;
		
		for(int i=0;i<	updateInFutur;i++)
		{
			TiledMapTile currentCell = Utils.getTile(new Point( (int)(getOffset(directionSaveTemp,output).x/scale) ,(int) (getOffset(directionSaveTemp,output).y/scale) ), (TiledMapTileLayer) (map.getLayers().get(0)) );
						
			direction = getDirection(currentCell);
			
			//System.out.println("Position update" + this );
			
			directionSave=direction;
			
			output=goNextPosition(direction,output);	
		}
		
		return output ;
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(pos.x, pos.y, angle-270, scale/2, base);
		
	}

	@Override
	public boolean update(GdxGraphics g) {
		
		
		if(checkDeath())
		{		
			
			int direction = 0;

			TiledMapTile currentCell = Utils.getTile(new Point( (int)(getOffset(directionSave,pos).x/scale) ,(int) (getOffset(directionSave,pos).y/scale) ), (TiledMapTileLayer) (map.getLayers().get(0)) );
						
			direction = getDirection(currentCell);
			
			progress++;
			
			//System.out.println("Position update" + this );
			
			directionSave=direction;
			
			pos=goNextPosition(direction,pos);	
			
			return false;
			
		}
		else
		{
			return true;
		}
		
	}

}
