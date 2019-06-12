package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Menu for "The Tower Defense"
 *
 */
public class SettingGUI extends RenderingScreen {
	Skin skin;
	Stage stage;
	ImageButton quitGameButton;

	BitmapFont font;
	
	TextureAtlas buttonAtlas;
		
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		int buttonWidth = (int) (250 );
		int buttonHeight = (int) (40);

		stage = new Stage() {
			@Override
			public boolean keyDown(int keyCode) {

		        if (keyCode == Input.Keys.ESCAPE) {
		        	System.out.println("pressed");
		        	DemoScreen.transition(0);
		        }
				return super.keyDown(keyCode);
			}
		};
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		
		quitGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown_pressed.png")))));
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);

		quitGameButton.setPosition((Gdx.graphics.getWidth()/2 - 250/2), (int) (Gdx.graphics.getHeight() * .5));

		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
		
		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(quitGameButton);

		/**
		 * Register listener
		 */
		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				if (quitGameButton.isChecked()) {
					DemoScreen.transition(0);
				}	
					
			}
		});		
		
	}
	/*
	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);

		System.out.println("test");
	}

	 @Override
	public void onKeyUp(int keycode) {
	        super.onKeyUp(keycode);

	        System.out.println("presseddd");
	        if (keycode == Input.Keys.ESCAPE) {
	        	System.out.println("pressed");
	        	DemoScreen.transition(0);
	        }
	    }*/
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);
		
		
		// This is required for having the GUI work properly
		stage.act();
		stage.draw();
		
		g.drawString(Gdx.graphics.getWidth() / 2  , (int) (Gdx.graphics.getHeight() * .5 + quitGameButton.getHeight() /2), "Quit" ,font,1);

	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.input.setInputProcessor(lastInputProcessor);
		stage.dispose();
	}
}
