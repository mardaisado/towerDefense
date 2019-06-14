package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
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
public class MainMenu extends RenderingScreen {
	Skin skin;
	Stage stage;
	TextButton newGameButton;
	TextButton quitGameButton;
	TextButton settingGameButton;

	BitmapFont font;
	TextureAtlas buttonAtlas;
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		int buttonWidth = Gdx.graphics.getWidth()/5;
		int buttonHeight = Gdx.graphics.getHeight()/15;
//		0.984, 0.721, 0.074
//		1f,0.58f,0.04f

		stage = new Stage();
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events
		
		skin = new Skin(Gdx.files.internal("data/ui/flat-earth/skin/flat-earth-ui.json"));
		//skin = new Skin(new TextureAtlas(Gdx.files.internal("data/ui/kenney-atlas/skin/ui-blue.atlas")));

		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		newGameButton = new TextButton("PLAY", skin); // Use the initialized skin
		//newGameButton.setColor(buttonColor);
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
		newGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);
	

		quitGameButton = new TextButton("EXIT", skin); // Use the initialized skin
		//quitGameButton.setColor(buttonColor);
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);
		quitGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);
		
		settingGameButton = new TextButton("SETTINGS", skin); // Use the initialized skin
		//settingGameButton.setColor(Color.ORANGE);
		settingGameButton.setWidth(buttonWidth);
		settingGameButton.setHeight(buttonHeight);
		settingGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);

		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));	
		settingGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .5));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3));
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		//parameter.size = generator.scaleForPixelHeight((int) (x/10.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
		
		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(settingGameButton);
		stage.addActor(quitGameButton);

		/**
		 * Register listener
		 */
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (newGameButton.isChecked()) {
					MasterScreen.transition(1);
				}			
					
			}
		});
		
		settingGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (settingGameButton.isChecked()) {
					MasterScreen.transition(4);
				}			
					
			}
		});
		
		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (quitGameButton.isChecked()) {
					Gdx.app.exit();
					System.out.println("Closed Game by user");
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
