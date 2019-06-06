package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;


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
	
	/**
	 * Func for return the boolean proprety on a position or a tile
	 *
	 * @param Pos
	 *            Position of the proprety
	 * @param tile
	 *            Tile of the proprety (null if you would give the position)
	 * @param proprety
	 *            name of the proprety
	 * @param map
	 * 			  
	 * @return True/false if the proprety is true/flase, false if the tile, proprety doesnt exist
	 */
	public static boolean returnStateForBool(Point pos,TiledMapTile tile, String proprety, TiledMap map)
	{
		
		//	float screenHeigth = Gdx.graphics.getHeight();
		//	float tileSize = (((int)(screenHeigth/(map.getProperties().get("width",Integer.class)))/64f));
		if(tile==null)
		{
			tile = getTile(new Point((int) (pos.x),(int) ((pos.y))),(TiledMapTileLayer) map.getLayers().get(0));
		}
		
		if(tile!=null)
		{
			if((tile).getProperties().get(proprety)!=null)
			{			
				Object tpe = tile.getProperties().get(proprety);
				
				return (boolean) tpe;						
			}
		}
		return false;			
	}
	
	/**
	 * Func for return the integer proprety on a position or a tile
	 *
	 * @param Pos
	 *            Position of the proprety
	 * @param tile
	 *            Tile of the proprety (null if you would give the position)
	 * @param proprety
	 *            name of the proprety
	 * @param map
	 * 			  
	 * @return True/false if the proprety is true/flase, false if the tile, proprety doesnt exist
	 */
	public static int returnStateForInt(Point pos,TiledMapTile tile, String proprety, TiledMap map)
	{
		
		//	float screenHeigth = Gdx.graphics.getHeight();
		//	float tileSize = (((int)(screenHeigth/(map.getProperties().get("width",Integer.class)))/64f));
		if(tile==null)
		{
			tile = getTile(new Point((int) (pos.x),(int) ((pos.y))),(TiledMapTileLayer) map.getLayers().get(0));
		}
		
		if(tile!=null)
		{
			if((tile).getProperties().get(proprety)!=null)
			{			
				Object tpe = tile.getProperties().get(proprety);
				
				return (int) tpe;						
			}
		}
		
		return 0;			
	}
	
	/**
	 * Func for return the string proprety on a position or a tile
	 *
	 * @param Pos
	 *            Position of the proprety
	 * @param tile
	 *            Tile of the proprety (null if you would give the position)
	 * @param proprety
	 *            name of the proprety
	 * @param map
	 * 			  
	 * @return True/false if the proprety is true/flase, false if the tile, proprety doesnt exist
	 */
	public static String returnStateForString(Point pos,TiledMapTile tile, String proprety, TiledMap map)
	{
		
	//	float screenHeigth = Gdx.graphics.getHeight();
	//	float tileSize = (((int)(screenHeigth/(map.getProperties().get("width",Integer.class)))/64f));
		if(tile==null)
		{
			tile = getTile(new Point((int) (pos.x),(int) ((pos.y))),(TiledMapTileLayer) map.getLayers().get(0));
		}
		
		if(tile!=null)
		{
			if((tile).getProperties().get(proprety)!=null)
			{			
				Object tpe = tile.getProperties().get(proprety);
				
				return (String) tpe;						
			}
		}
		return "";			
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
	
	public static boolean checkDefenseCollision(Vector<Defense> defense,int x,int y,int radius) {
		for (int i = 0; i < defense.size(); i++) {
			if (defense.elementAt(i).checkCollision(x, y, radius)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static Defense createDefense(String url,Point p,Vector<Ennemi> ennemi,Vector<Object> projectile) {
		//Class<?> c = Class.forName("ch.hevs.gdx2d.hello.Tourelle2");
		Defense s = null;
		try {
			Class<?> c = Class.forName(url);
			Constructor<?> cons = c.getConstructor( Point.class,Vector.class,Vector.class); // the args here are the expected types for the constructor that you require on the class
			s = (Defense) cons.newInstance(p,ennemi,projectile);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
}
