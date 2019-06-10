package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RoundManager implements UpdateObject, DrawableObject{

	
	JSONArray listOfEnnemi;
	
	private BitmapImage[] assets;
	
	Vector<Ennemi> ennemi;
	
	int timeDelta=0;
	
	PlayButton playButton;
	
	public RoundManager(Vector<Ennemi> ennemi, PlayButton playButton) {
		
		this.ennemi=ennemi;
		this.playButton=playButton;
		listOfEnnemi=GetTable(1); // Parse JSON file for find the first round
	}
	
	private boolean searchTime(int timeToFound)
	{
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

	}
	
	@Override
	public void update(GdxGraphics g) {
		
		System.out.println("added try");
		if(searchTime(timeDelta))
		{
			System.out.println("added done");
			ennemi.add(new Mojojo(assets[299],Game.tiledMap,100,100));
		}	
		
		timeDelta++;
		
		if(listOfEnnemi.size()==0)
		{
			playButton.play=true;
		}

	}

	@Override
	public void draw(GdxGraphics arg0) {
		// TODO Auto-generated method stub
		
	}


}
