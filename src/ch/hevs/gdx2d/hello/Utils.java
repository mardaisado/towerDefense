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
	
	/**
	 * Func for return the boolean property on a position or a tile
	 *
	 * @param Pos
	 *            Position of the property
	 * @param tile
	 *            Tile of the property (null if you would give the position)
	 * @param proprety
	 *            name of the property
	 * @param map
	 * 			  
	 * @return True/false if the property is true/false, false if the tile, property doesn't exist
	 */
	public static boolean returnStateForBool(Point pos,TiledMapTile tile, String proprety, TiledMap map)
	{
		
		//	float screenHeigth = Gdx.graphics.getHeight();
		//	float tileSize = (((int)(screenHeigth/(map.getProperties().get("width",Integer.class)))/64f));
		if(tile==null )
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
	 * Func for return the integer property on a position or a tile
	 *
	 * @param Pos
	 *            Position of the property
	 * @param tile
	 *            Tile of the property (null if you would give the position)
	 * @param proprety
	 *            name of the property
	 * @param map
	 * 			  
	 * @return True/false if the property is true/false, false if the tile, property doesn't exist
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
	 * Func for return the string property on a position or a tile
	 *
	 * @param Pos
	 *            Position of the property
	 * @param tile
	 *            Tile of the property (null if you would give the position)
	 * @param proprety
	 *            name of the property
	 * @param map
	 * 			  
	 * @return True/false if the property is true/false, false if the tile, property doesn't exist
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
	
	/**
	 * Func for return the tile at one position
	 *
	 * @param Pos
	 *            Position of the tile
	 * @param tiledLayer
	 *            Layer where is yout tile
	 * 			  
	 * @return the tile
	 */
	public static TiledMapTile getTile(Point position,TiledMapTileLayer tiledLayer) {
		if (position.x < 0 || position.y < 0) {
			return null;
		}
		
		try {
			int x = (int) ((double)position.x / (double)tiledLayer.getTileWidth()) ;
			int y = (int) ((double)position.y / (double)tiledLayer.getTileHeight()) ;

			return tiledLayer.getCell(x, y).getTile();
		} catch (Exception e) {

			return null;
		}
	}
	
	/**
	 * Func that check if a defense is clicked and which one
	 *
	 * @param defense
	 *            vector with all defense existing
	 * @param x, y
	 *            mouse position
	 * 			  
	 * @return the defense that is clicked, null if no defense is clicked
	 */
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
	
	/**
	 * Func that check if there is collision between defense
	 *
	 * @param defense
	 *            vector with all defense existing
	 * @param x, y
	 *            mouse position
	 * @param radius
	 * 			  radius for the collision
	 * 			  
	 * @return true if there is a collision / false otherwise
	 */
	public static boolean checkDefenseCollision(Vector<Defense> defense,int x,int y,int radius) {
		for (int i = 0; i < defense.size(); i++) {
			if (defense.elementAt(i).checkCollision(x, y, radius)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Func that create a defense with only the name of the defense class
	 *
	 * @param url
	 *            url of the class
	 * @param p
	 *            start position of the defenseS
	 * @param ennemi
	 * 			  vector of ennemy
	 * @param projectile
	 * 			  vector of projectile
	 * 			  
	 * @return the defense created
	 */
	public static Defense createDefense(String url,Point p,Vector<Ennemi> ennemi,Vector<Object> projectile) {
		Defense s = null;
		try {
			Class<?> c = Class.forName(url);
			Constructor<?> cons = c.getConstructor( Point.class,Vector.class,Vector.class); // the args here are the expected types for the constructor that you require on the class
			s = (Defense) cons.newInstance(p,ennemi,projectile);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	/**
	 * Func that place dragable in the right holder
	 *
	 * @param defenseProperties
	 *            tab of defense properties
	 * @param pickGui
	 *            holder where to put dragables
	 * @param dragable
	 * 			  vector of dragable (where to create it)
	 */
	public static void placeDrawable(DefenseProperties[] defenseProperties,PickDefenseGUI pickGui,Vector<Dragable> dragable) {
		for (int i = 0; i < (defenseProperties.length / 2 + defenseProperties.length % 2); i++) {
			for (int j = 0; j < 2; j++) {
				if (i * 2 + j < defenseProperties.length) {
					dragable.add(new Dragable(defenseProperties[i * 2 + j], pickGui.x + (95 + 110 * j) * pickGui.facteur,
							pickGui.x - (95 + 110 * i) * pickGui.facteur, 90 * pickGui.facteur / 2));
				}
			}
		}
	}

}
