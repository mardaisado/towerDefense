package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Menu for "The Tower Defense"
 *
 */
public class MainMenu extends RenderingScreen {
	
	private Skin skin;
	private Stage stage;
	private TextButton newGameButton;
	private TextButton quitGameButton;
	private TextButton settingGameButton;

	private InputProcessor lastInputProcessor;

	@Override
	public void onInit() {
		int buttonWidth = Gdx.graphics.getWidth()/5;
		int buttonHeight = Gdx.graphics.getHeight()/15;
		
		stage = new Stage();
		
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);

		skin = new Skin(Gdx.files.internal("data/ui/flat-earth/skin/flat-earth-ui.json"));

		// BUTTONS
		newGameButton = new TextButton("PLAY", skin);
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
		newGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);


		quitGameButton = new TextButton("EXIT", skin);
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);
		quitGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);

		settingGameButton = new TextButton("SETTINGS", skin);
		settingGameButton.setWidth(buttonWidth);
		settingGameButton.setHeight(buttonHeight);
		settingGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);

		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));	
		settingGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .5));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3));

		// add to stage
		stage.addActor(newGameButton);
		stage.addActor(settingGameButton);
		stage.addActor(quitGameButton);

		// button listener
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				// transition to map selector
				if (newGameButton.isChecked()) {
					MasterScreen.transition(1);
				}			

			}
		});

		settingGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				// transition to settings
				if (settingGameButton.isChecked()) {
					MasterScreen.transition(4);
				}			

			}
		});

		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				// quit the game
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

		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.input.setInputProcessor(lastInputProcessor);	// recover the lastInput processor
		stage.dispose();
	}
}
