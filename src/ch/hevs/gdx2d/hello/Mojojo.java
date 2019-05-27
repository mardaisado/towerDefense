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
	
	int speed=2;
		
	TiledMapTileLayer tiledLayer;
	
	int progress=0;
	
	TiledMap map;
	
	float scale;
	
	public Mojojo(float scale, BitmapImage base, TiledMap map) {
		
		this.tiledLayer=(TiledMapTileLayer) map.getLayers().get(0);
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

	public int getProgress()
	{
		return progress;
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

			return (int) (test2);
		}
		

		
		return 0;
	}
	
	public void goStart()
	{
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if((Utils.getTile(new Point((int)((i+0.5)*64f),(int)((j+0.5)*64f)), tiledLayer).getProperties().get("start") != null))
				{					
					if((Utils.getTile(new Point((int)((i+0.5)*64f),(int)((j+0.5)*64f)), tiledLayer).getProperties().get("start",boolean.class)) == true)
					{	
						pos = new Point((int)((i)*scale*64f),(int)((j+0.5)*scale*64f));
				  		return;
					}
				}
			} 
			
		}
	}
	
	public void goNextPosition(int direction)
	{
		
		
		int speed = 2;
		switch (direction) {
		case 1:
				pos = new Point(pos.x + ((int)((0)*scale*speed)), pos.y + ((int)((1)*scale*speed)) );
			break;

		case 2:
				pos = new Point(pos.x + ((int)((0)*scale*speed)), pos.y + ((int)((-1)*scale*speed)) );
			break;
		case 3:
				pos = new Point(pos.x + ((int)((1)*scale*speed)), pos.y + ((int)((0)*scale*speed)) );
			break;
		case 4:
				pos = new Point(pos.x + ((int)((-1)*scale*speed)), pos.y + ((int)((0)*scale*speed)) );
			break;
		default:
			break;
		}
		
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

		TiledMapTile currentCell = Utils.getTile(new Point((int) (pos.x/scale),(int) (pos.y/scale)), (TiledMapTileLayer) (map.getLayers().get(0)) );
		int direction = 0;
		
			
		direction = getDirection(currentCell);
		progress++;
		System.out.println("Position update" + this );
		goNextPosition(direction);	
		
	}

}
