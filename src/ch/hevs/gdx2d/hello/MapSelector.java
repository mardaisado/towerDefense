package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by Pierre-André Mudry on 07.05.2015.
 * HES-SO Valais, 2015
 */
public class MapSelector extends RenderingScreen {

	Skin skin;
	Stage stage;

	/* Vector of initial capacity(size) of 2 */
	Vector<ImageButton> mapButton = new Vector<ImageButton>(9);

	BitmapFont font;

	TextureAtlas buttonAtlas;

	InputProcessor lastInputProcessor;

	final static int BUTTONWIDTH = 50 ;

	final static int BUTTONHEIGHT = 50;

	@Override
	public void onInit() {


		stage = new Stage() {
			@Override
			public boolean keyDown(int keyCode) {

				if (keyCode == Input.Keys.ESCAPE) {
					DemoScreen.transition(0);
				}
				return super.keyDown(keyCode);
			}
		};
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		loadMapButton(mapButton,"data/tilemap");

		fillVector(mapButton);

		setPosition(mapButton);

		/**
		 * Adds the buttons to the stage
		 */
		for (int i = 0; i < mapButton.size(); i++) {
			stage.addActor(mapButton.get(i));
		}

		checkClick(mapButton);


	}

	Vector<ImageButton> loadMapButton(Vector<ImageButton> map,String path)
	{
		int indexFile=0;
		List<String> results = new ArrayList<String>();

		File[] files = new File(path).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}

		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).contains(".tmx")) {

				map.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/"+indexFile+".png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/"+indexFile+"1.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/"+indexFile+"1.png"))))));

				indexFile++;
			}
		}

		return map;
	}

	Vector<ImageButton> fillVector(Vector<ImageButton> map)
	{
		for (int i = map.size(); i < map.capacity(); i++) {

			map.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/998.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/998.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/999.png"))))));
			map.get(i).setName("0");
		}		

		return map;
	}

	Vector<ImageButton> setPosition(Vector<ImageButton> map)
	{
		int baseWidth=(Gdx.graphics.getWidth()-(3*50)-(3*75))/2;
	
		int baseHeight=(Gdx.graphics.getHeight()-(3*50)-(3*75))/2;
		for (int i = 0; i < map.size(); i++) {
			
//			map.get(0).setHeight(150);
//			map.get(0).setWidth(150);
//			map.get(0).setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	
			map.get(i).getImage().setScale(Gdx.graphics.getWidth()/500);
			
			// A FAIRE 
			map.get(i).setHeight(BUTTONHEIGHT);
			map.get(i).setWidth(BUTTONWIDTH);
			if(i>=0 && i<=2)
			{
				map.get(i).setPosition(Gdx.graphics.getWidth()/2+(i%3-1)*Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*.6f);
			} else if(i>=3 && i<=5)
			{
				map.get(i).setPosition(Gdx.graphics.getWidth()/2+(i%3-1)*Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*.4f);
			} else
			{
				map.get(i).setPosition(Gdx.graphics.getWidth()/2+(i%3-1)*Gdx.graphics.getWidth()*0.1f, Gdx.graphics.getHeight()*.2f);
			}

		}		

		return map;
	}

	private void checkClick(Vector<ImageButton> map)
	{		
		for (int i = 0; i < map.size(); i++) {
			final int temp = i;
			mapButton.get(temp).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					if (mapButton.get(temp).isChecked() && mapButton.get(temp).getName() != "0") {
						DemoScreen.mapSelector=temp;
						DemoScreen.transition(2);
					}
				}
			});			
		}
	}

	@Override
	public void onClick(int x, int y, int button) {
		super.onClick(x, y, button);

		if(mapButton.get(0).isPressed())
		{
			DemoScreen.mapSelector=0;
			DemoScreen.transition(2);

		}
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.DARK_GRAY);
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.input.setInputProcessor(lastInputProcessor);
		stage.dispose();
	}
}
