package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Pierre-André Mudry on 07.05.2015.
 * HES-SO Valais, 2015
 */
public class CreditsScreen extends RenderingScreen {
	
	TextButton menuGameButton;
	Label lblMessage;
	
	Skin skin;
	Stage stage;

	BitmapFont font;
	
	TextureAtlas buttonAtlas;
		
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		int buttonWidth = Gdx.graphics.getWidth()/5;
		int buttonHeight = Gdx.graphics.getHeight()/15;

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

		skin = new Skin(Gdx.files.internal("data/ui/flat-earth/skin/flat-earth-ui.json"));
		
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		lblMessage = new Label("oui bravo tu as perdu !!!", skin);
		lblMessage.setPosition(Gdx.graphics.getWidth() / 2 , (int) (Gdx.graphics.getHeight() * .5));
		lblMessage.setAlignment(Align.center, Align.center);
		lblMessage.setAlignment(Align.center);
		lblMessage.setFontScale(Gdx.graphics.getWidth()/1200);
		
		
		menuGameButton = new TextButton("MENU", skin); // Use the initialized skin
		//settingGameButton.setColor(Color.ORANGE);
		menuGameButton.setWidth(buttonWidth);
		menuGameButton.setHeight(buttonHeight);
		menuGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);
		
		
		menuGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3));

		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
		
		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(menuGameButton);
		stage.addActor(lblMessage);

		/**
		 * Register listener
		 */
		menuGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				if (menuGameButton.isChecked()) {
					DemoScreen.transition(0);
				}	
					
			}
		});		
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.DARK_GRAY);
		
		stage.act();
		stage.draw();		
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(lastInputProcessor);
		super.dispose();
	}
}
