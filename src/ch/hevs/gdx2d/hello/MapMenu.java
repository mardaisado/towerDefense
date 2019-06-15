package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * A screen for choose the map to play
 */
public class MapMenu extends RenderingScreen {

	private Stage stage;

	private Vector<ImageButton> mapButton = new Vector<ImageButton>(9);

	private InputProcessor lastInputProcessor;

	final static int BUTTONWIDTH = 50 ;

	final static int BUTTONHEIGHT = 50;

	@Override
	public void onInit() {

		stage = new Stage() {
			@Override
			public boolean keyDown(int keyCode) {

				if (keyCode == Input.Keys.ESCAPE) {
					MasterScreen.transition(0);
				}
				return super.keyDown(keyCode);
			}
		};
		
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);

		loadMapButton(mapButton,"data/tilemap");

		fillVector(mapButton);

		setPosition(mapButton);

		for (int i = 0; i < mapButton.size(); i++) {
			stage.addActor(mapButton.get(i));
		}

		checkClick(mapButton);


	}

	/**
	 * Func for fill the ImageButton vector with the correct image for each map on the path
	 *
	 *	 * @param Vector ImageButton map
	 *	 * @param String path      
	 * @return ImageButton Vector with the preview of the map
	 */
	private Vector<ImageButton> loadMapButton(Vector<ImageButton> map,String path)
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

	/**
	 * Func for fill the ImageButton vector (the free place) with the soon image
	 *
	 *	 * @param Vector ImageButton map   
	 * @return ImageButton Vector with the "soon" image
	 */
	private Vector<ImageButton> fillVector(Vector<ImageButton> map)
	{
		for (int i = map.size(); i < map.capacity(); i++) {

			map.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/998.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/998.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/tilemap/preview/998.png"))))));
			map.get(i).setName("0");
		}		

		return map;
	}

	/**
	 * Func for set the ImageButton vector position and size
	 *
	 *	 * @param Vector ImageButton map
	 * @return ImageButton Vector with the position and size
	 */
	private Vector<ImageButton> setPosition(Vector<ImageButton> map)
	{

		for (int i = 0; i < map.size(); i++) {

			map.get(i).getImage().setScale(Gdx.graphics.getWidth()/500);

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

	/**
	 * Func for check which button is clicked
	 *
	 *	 * @param Vector ImageButton map    
	 * 
	 * Launch the game with the correct map (mapSelector on MasterScreen class)
	 */
	private void checkClick(Vector<ImageButton> map)
	{		
		for (int i = 0; i < map.size(); i++) {
			final int temp = i;
			mapButton.get(temp).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					if (mapButton.get(temp).isChecked() && mapButton.get(temp).getName() != "0") {
						MasterScreen.mapSelector=temp;
						MasterScreen.transition(2);
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
			MasterScreen.mapSelector=0;
			MasterScreen.transition(2);

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
