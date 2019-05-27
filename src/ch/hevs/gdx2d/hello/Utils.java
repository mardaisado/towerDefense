package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;

public class Utils {
	public static BitmapImage[] loadAssets() {
		BitmapImage[] tmp = new BitmapImage[300];
		String tmp2 = "";
		for (int i = 0; i < tmp.length; i++) {
			tmp2 = String.format("%03d", i);
			tmp[i] = new BitmapImage("data/assets/PNG/Retina/towerDefense_tile" + tmp2 + ".png");
		}
		return tmp;
	}
	
	public static boolean isWalkable(TiledMapTile tile) {
		if (tile == null)
			return false;

//		Boolean test = tile.getProperties().get("walkable",Boolean.class);
//		
//		if (test == null) {
//			System.out.println("heyyyyyyyyyy");
//			return false;
//		}
		return true;
		//return Boolean.parseBoolean(test.toString());
	}
	
	public static TiledMapTile getTile(Point position,TiledMapTileLayer tiledLayer) {
		if (position.x < 0 || position.y < 0) {
			return null;
		}
		
		try {
			//private TiledMapTileLayer tiledLayer;
			//tiledLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
			int x = (int) ((double)position.x / (double)tiledLayer.getTileWidth()) ;
			int y = (int) ((double)position.y / (double)tiledLayer.getTileHeight()) ;

			return tiledLayer.getCell(x, y).getTile();
		} catch (Exception e) {

			return null;
		}
	}
	
	public static Defense getDefenseClicked(Vector<Defense> defense,int x,int y) {
		Defense def;
		int width = 40;
		for (int i = 0; i < defense.size(); i++) {
			def = defense.elementAt(i);
			if ((x >= def.pos.x-width && x <= def.pos.x+width && y >= def.pos.y-width && y <= def.pos.y+width)) {
				return def;
			}
		}
		return null;
	}
}
