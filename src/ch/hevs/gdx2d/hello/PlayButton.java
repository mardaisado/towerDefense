package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class PlayButton implements DrawableObject,UpdateObject{

	BitmapImage playButton;
	BitmapImage fastButton;
	BitmapImage fastButtonClicked;

	boolean play = false;

	boolean fast = false;
	private BitmapImage[] assets;


	float x;
	float y;
	float scale;

	Vector<Ennemi> ennemi;

	int [] need = {1,0,1,0,1,0,0};

	int index=0; 

	public PlayButton(Vector<Ennemi> ennemi) {
		// TODO Auto-generated constructor stub
		x = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		assets = Utils.loadAssets();
		playButton = new BitmapImage("data/images/playButton.png");
		fastButton = new BitmapImage("data/images/fastButton.png");
		fastButtonClicked = new BitmapImage("data/images/fastButtonClicked.png");

		this.ennemi=ennemi;

		scale =  0.1f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/playButton.getImage().getHeight();
	}

	@Override
	public void update(GdxGraphics g) {
		if(need[index]==1)
		{
			ennemi.add(new Mojojo(Game.tileSize,assets[299],Game.tiledMap,100,100));
		}
		index++;
		onTable();

		if(index>5)
		{
			index=need.length-1;
		}

	}

	public void onTable()
	{
		int[] array = null;


		BufferedInputStream bis;        
		try {
			bis = new BufferedInputStream(new FileInputStream(new File("data/txt/test.txt")));
			byte[] buf = new byte[8];

		

			while(bis.read(buf) != -1);


			bis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}     	


		//return array;

	}

	public void clicked(int x,int y) {
		if(x >= this.y-scale*playButton.getImage().getHeight()/2 && x <= this.y+scale*playButton.getImage().getHeight()/2  && y >= this.x-scale*playButton.getImage().getHeight()/2  && y <= this.x+scale*playButton.getImage().getHeight()/2 ) {
			if (play) {
				if (fast) {
					fast = false;
				}
				else {
					fast = true;
				}
			}
			else {
				play = true;
				// start wave
			}
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		if (play) {
			if (fast) {
				g.drawTransformedPicture(y, x, 0,scale, fastButtonClicked);
			}
			else {
				g.drawTransformedPicture(y, x, 0,scale, fastButton);
			}
		}
		else {
			g.drawTransformedPicture(y, x, 0,scale, playButton);
		}
	}
}
