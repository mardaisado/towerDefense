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
		
		mapButton.get(0).addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if (mapButton.get(0).isChecked()) {
					DemoScreen.mapSelector=0;
					System.out.println("GOOO");
					DemoScreen.transition(2);
				}	
					
			}
		});

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

				map.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/"+indexFile+".png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/"+indexFile+""+1+".png"))))));

				indexFile++;
			}
		}

		return map;
	}

	Vector<ImageButton> fillVector(Vector<ImageButton> map)
	{
		for (int i = map.size(); i < map.capacity(); i++) {
			
			map.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/999.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/999.png"))))));

		}		
		
		return map;
	}

	Vector<ImageButton> setPosition(Vector<ImageButton> map)
	{
		for (int i = 0; i < map.size(); i++) {
			map.get(i).setHeight(BUTTONHEIGHT);
			map.get(i).setWidth(BUTTONWIDTH);
			map.get(i).setPosition((float) (Gdx.graphics.getWidth()*0.1*i),(int) (Gdx.graphics.getHeight() * .5));
		}		
		
		return map;
	}
	
	
	
	@Override
	public void onClick(int x, int y, int button) {
		super.onClick(x, y, button);
		
		if(mapButton.get(0).isPressed())
		{
			DemoScreen.mapSelector=0;
			System.out.println("GOOO");
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
	}
}
