package ch.hevs.gdx2d.hello;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Mojojo extends Ennemi {

	BitmapImage base;
	
	int temp=0;
	
	int speed=2;
		
	TiledMapTileLayer tiledLayer;
	
	TiledMap map;
	
	float scale;
	
	public Mojojo(Point pos,float scale, BitmapImage base, TiledMapTileLayer tiledLayer, TiledMap map) {
		super(pos);
		
		this.tiledLayer=tiledLayer;
		this.base = base;
		this.scale = scale;
		this.map=map;
	
		goStart();

		changeSpeedForScale();
	}
	private void changeSpeedForScale()
	{
		speed = (int) (speed/scale);
		
		
	}

	
	private boolean isWalkable(TiledMapTile tile) {
		if (tile == null)
			return false;

		Object test = tile.getProperties().get("walk");

		return Boolean.parseBoolean(test.toString());
	}
	
	private int getDirection(TiledMapTile tile) {
		if (tile == null)
			return 0;
			
		if(tile.getProperties().get("direction")!=null)
		{
			int test = (int) tile.getProperties().get("direction");
			
			Object test2 = tile.getProperties().get("direction");

			
			System.out.println(test2);
			return (int) (test2);
		}
		
		System.out.println("nul");
		
		return 0;
	}
	
	public void goStart()
	{
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				
				if((Utils.getTile(new Point((int)((i)*scale*64f),(int)((j)*scale*64f)), tiledLayer).getProperties().get("start") != null))
				{					
					System.out.println("tile start found ");
					if((boolean) (Utils.getTile(new Point((int)((i)*scale*64f),(int)((j)*scale*64f)), tiledLayer).getProperties().get("start")) == true)
					{	System.out.println("tile start REAL found ");	
					System.out.println("x vaut: " + i + " et y vaut : "+ j);	
				  		pos = new Point((int)((i)*scale*64f),(int)((j)*scale*64f));
						return;
					}
				}
			} 
			
		}
		System.out.println("tile start not found ");
	}
	
	public void goNextPosition(int direction)
	{
		
		System.out.println(pos);
		System.out.println(direction);
		switch (direction) {
		case 1:
				pos = new Point(pos.x + ((int)((0)*scale*64f)), pos.y + ((int)((1)*scale*64f)) );
			break;

		case 2:
				pos = new Point(pos.x + ((int)((0)*scale*64f)), pos.y + ((int)((-1)*scale*64f)) );
			break;
		case 3:
				pos = new Point(pos.x + ((int)((1)*scale*64f)), pos.y + ((int)((0)*scale*64f)) );
			break;
		case 4:
				pos = new Point(pos.x + ((int)((-1)*scale*64f)), pos.y + ((int)((0)*scale*64f)) );
			break;
		default:
			break;
		}
		
		System.out.println(pos);
	}
	
	private TiledMapTile getTile(Point pos, int offsetX, int offsetY) {
		try {
			int x = (int) (pos.x / tiledLayer.getTileWidth()) + offsetX;
			int y = (int) (pos.y / tiledLayer.getTileHeight()) + offsetY;

			return tiledLayer.getCell(x, y).getTile();
		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, base);
		
	}

	@Override
	public void update(GdxGraphics g) {

	
		/*
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 23; j++) {
				
				System.out.print(Utils.getTile(new Point((int)((i)*scale*64f),(int)((j)*scale*64f)), tiledLayer).getProperties().get("direction"));
				
				if( (Utils.getTile(new Point((int)((i)*scale*64f),(int)((j)*scale*64f)), tiledLayer).getProperties().get("direction")) != null)
						{
					System.out.println( " x :" + i + " y : " + j);	
						}
				
			} System.out.println("");
			
		}
		

		TiledMapTile nextCell = null;
		TiledMapTile currentCell = Utils.getTile(pos, (TiledMapTileLayer) (map.getLayers().get(0)) );
		int direction = 0;
		
		direction = getDirection(currentCell);
		
		System.out.println("oui"+direction);
		goNextPosition(direction);	

		*/
	}

}
