package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.util.Random;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Mojojo extends Ennemi {

	private BitmapImage base;
	
	private float scale, angle=0;
	
	private int directionSave;
	
	public int speed=2;
	
	private boolean changeDir=false;
	
	private Point posOld;
	
	private TiledMap map;
	
	private TiledMapTileLayer tiledLayer;
	
	public Mojojo(float scale, BitmapImage base, TiledMap map, int hp, int reward) {
				
		super(hp, reward);
		this.tiledLayer=(TiledMapTileLayer) map.getLayers().get(0);
		//this.base = base;
		this.scale = scale;
		this.map=map;
		pos=goStart();
		this.base = loadAssets()[new Random().nextInt(27)];
 
		directionSave= (int) Utils.returnStateForInt(new Point((int) (pos.x/scale),(int) (pos.y/scale)), null, "direction", map);		
		
		speed = changeSpeedForScale(speed);	
		
	}
	
	public static BitmapImage[] loadAssets() {
		BitmapImage[] tmp = new BitmapImage[27];
		String tmp2 = "";
		for (int i = 0; i < tmp.length; i++) {
			tmp2 = String.format("%03d", i);
			tmp[i] = new BitmapImage("data/assets/PNG/ANIMAL/" + i + ".png");
		}
		return tmp;
	}
	
	/**
	 * exemple : changeSpeedForScale(speed) return temp with the scale 
	 *
	 * @param temp
	 *            to be scaled
	 * @return scaled temp
	 */
	private int changeSpeedForScale(int temp)
	{
		return (int) (temp/scale);
	}
	
	/**
	 * exemple : goStart() return the position of the start
	 *
	 * @return The start position 
	 */
	private Point goStart()
	{
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if((Utils.returnStateForBool(new Point((int)((i+0.5)*64f),(int)((j+0.5)*64f)), null, "start", map)))
				{	
					return new Point((int)((i+0.5)*scale*64f),(int)((j+0.5)*scale*64f));
			  		
					
				}
			} 
			
		}
		
		return new Point(0,0);
	
	}
	
	/**
	 * exemple : goNextPosition(direction,pos) return new position 
	 *
	 * @param direction
	 *            the direction of the tile
	 * @param position
	 *            the actual position.
	 * @return The next positon 
	 */
	private Point goNextPosition(int direction, Point position)
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

	/**
	 * Func for the state of the ennmi
	 *
	 * @return true is ennemi is alive, false if not
	 */
	public boolean checkDeath()
	{
		if(hp>0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * exemple : getOffset(direction,pos) get postion with an offset
	 *
	 * @param direction
	 *            the direction of the tile
	 * @param position
	 *            the actual position.
	 * @return The position with the offset
	 */
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
	
	/**
	 * exemple : prediction(x) get the position in x update
	 *
	 * @param updateInFutur
	 *            The number of update for the prediction
	 * @return The predicted position
	 */
	public Point prediction(int updateInFutur)
	{
		Point output = pos;
		
		int directionSaveTemp=directionSave;
		int direction =0;
		
		TiledMapTile currentCell;
		
		for(int i=0;i<	updateInFutur;i++)
		{
			currentCell = Utils.getTile(new Point( (int)(getOffset(directionSaveTemp,output).x/scale) ,(int) (getOffset(directionSaveTemp,output).y/scale) ), (TiledMapTileLayer) (map.getLayers().get(0)) );
						
			direction = Utils.returnStateForInt(null,currentCell, "direction", map);
			
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
						
			direction = Utils.returnStateForInt(null,currentCell, "direction", map);
			
			progress++;
			
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
