package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class RoundManager implements UpdateObject, DrawableObject{

	JSONArray listOfEnnemi;

	Vector<Ennemi> ennemi;

	int timeDelta=0;

	PlayButton playButton;
	boolean play_state = false;
	int round=1;
	int maxRound=0;

	JSONObject roundScript;

	float x;
	float y;

	BitmapFont font;

	public RoundManager(Vector<Ennemi> ennemi, PlayButton playButton) {

		this.ennemi=ennemi;
		this.playButton=playButton;

		roundScript = chargeObj("data/txt/test.json");

		maxRound=defineMax(roundScript);

		x = 0.06f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.02f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));

		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) ((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)/20.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	}

	private JSONObject chargeObj(String path)
	{
		JSONObject obj;

		try {
			obj = (JSONObject) (new JSONParser().parse(new FileReader(path)));
		} catch (IOException | ParseException e) {
			obj=null;
			e.printStackTrace();
		}

		return obj;
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

	private int defineMax(Object obj)
	{
		if(obj!=null)
		{
            JSONObject jo = (JSONObject) obj;
    		return jo.size();
		}
		return 0;
	}
	public void play() {
		play_state = true;
		timeDelta=0;
		listOfEnnemi=getTable(round,roundScript);
	}

	public JSONArray getTable(int round, Object obj)
	{

        if(obj != null)
        {
            JSONObject jo = (JSONObject) obj;
    		return (JSONArray) jo.get(round+"");
        }

        return null;

	}

	@Override
	public void update(GdxGraphics g) {
		if (play_state) {
			if(searchTime(timeDelta))
			{
				ennemi.add(new Mojojo(Game.tiledMap,100,100));
			}

			timeDelta++;

			if(listOfEnnemi.size()==0)
			{
				if(round<maxRound)
				{
					round++;
				}
				play_state=false;
				playButton.play=false;
			}
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawString(y, x,"ROUND "+round+"/"+maxRound, font);
	}


}
