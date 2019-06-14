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
public class SettingMenu extends RenderingScreen {
	Skin skin;
	Stage stage;
	TextButton quitGameButton;

	BitmapFont font;
	
	TextureAtlas buttonAtlas;
		
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		System.out.println("fddp");
		int buttonWidth = Gdx.graphics.getWidth()/5;
		int buttonHeight = Gdx.graphics.getHeight()/15;
		
		skin = new Skin(Gdx.files.internal("data/ui/flat-earth/skin/flat-earth-ui.json"));

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
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		
		quitGameButton = new TextButton("EXIT", skin); // Use the initialized skin
		//quitGameButton.setColor(buttonColor);
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);
		quitGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);
		
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3));
		
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
					MasterScreen.transition(0);
				}	
					
			}
		});		
		
	}

	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.DARK_GRAY);
		
		
		// This is required for having the GUI work properly
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
