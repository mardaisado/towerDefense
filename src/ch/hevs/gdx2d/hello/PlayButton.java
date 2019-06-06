package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	JSONArray listOfEnnemi;
	int timeDelta=0;

	double frame_time;
	public PlayButton(Vector<Ennemi> ennemi,double frame_time) {
		// TODO Auto-generated constructor stub
		x = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		assets = Utils.loadAssets();
		playButton = new BitmapImage("data/images/playButton.png");
		fastButton = new BitmapImage("data/images/fastButton.png");
		fastButtonClicked = new BitmapImage("data/images/fastButtonClicked.png");
		this.frame_time=frame_time;
		this.ennemi=ennemi;
		listOfEnnemi=GetTable(1); // Parse JSON file for find the first round
		scale =  0.1f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/playButton.getImage().getHeight();
	}

	@Override
	public void update(GdxGraphics g) {
		System.out.println(timeDelta);
		System.out.println(listOfEnnemi);
		if(searchTime(timeDelta))
		{
			ennemi.add(new Mojojo(Game.tileSize,assets[299],Game.tiledMap,100,100));
		}
		
		
		timeDelta++;

	}
	
	private boolean searchTime(int timeToFound)
	{
		System.out.println("search mode");
		for (int i = 0; i < listOfEnnemi.size(); i++) {
			if( (long) (((JSONObject) listOfEnnemi.get(i) ).get("delay")) == (long)timeToFound)
			{
				listOfEnnemi.remove(i);
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public JSONArray GetTable(int round)
	{
        Object obj=null;
        
		try {
			obj = new JSONParser().parse(new FileReader("data/txt/test.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        if(obj != null)
        {
            JSONObject jo = (JSONObject) obj; 
    		return (JSONArray) jo.get(round+"");
        }

        return null;
        // typecasting obj to JSONObject 

	}

	public void onTable()
	{
		int[] array = null;
/*

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
*/
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
