package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Menu for "The Tower Defense"
 *
 */
public class DemoGUI extends RenderingScreen {
	Skin skin;
	Stage stage;
	TextButton newGameButton, quitGameButton;

	TextureAtlas buttonAtlas;
	
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		int buttonWidth = (int) (180);
		int buttonHeight = (int) (30);

		stage = new Stage();
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		// Load the default skin (which can be configured in the JSON file)
		//skin = new Skin(Gdx.files.internal("data/images/flat-earth/flat-earth-ui.json"));
		skin = new Skin(Gdx.files.internal("data/images/uiskin.json"));

		newGameButton = new TextButton("Play", skin); // Use the initialized skin
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
		
		quitGameButton = new TextButton("Quit", skin); // Use the initialized skin
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);

		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .6));

		
		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(quitGameButton);

		/**
		 * Register listener
		 */
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (newGameButton.isChecked()) {
					DemoScreen.transition(1);
				}			
					
			}
		});
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);

		// This is required for having the GUI work properly
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.input.setInputProcessor(lastInputProcessor);
		stage.dispose();
		skin.dispose();
	}
}
