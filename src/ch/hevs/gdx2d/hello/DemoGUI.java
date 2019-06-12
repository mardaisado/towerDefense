package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.utils.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Very simple UI demonstration
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */
public class DemoGUI extends RenderingScreen {
	Skin skin;
	Stage stage;
	TextButton newGameButton, quitGameButton;
	TextField textArea;

	
	public static void main(String[] args) {
		
	}
	public DemoGUI()
	{
		
	}

	@Override
	public void onInit() {
		int buttonWidth = 180;
		int buttonHeight = 30;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		// Load the default skin (which can be configured in the JSON file)
		skin = new Skin(Gdx.files.internal("data/images/uiskin.json"));

		newGameButton = new TextButton("Play", skin); // Use the initialized skin
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);


		quitGameButton = new TextButton("Settings", skin); // Use the initialized skin
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);

		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .6));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));

		textArea = new TextField("Enter some text...", skin);
		textArea.setWidth(buttonWidth);
		textArea.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));

		textArea.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				textArea.setSelection(0, 0);

				// When you press 'enter', do something
				if (key == 13)
					Logger.log("You have typed " + textArea.getText());
			}
		});

		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(quitGameButton);
		stage.addActor(textArea);

		/**
		 * Register listener
		 */
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (newGameButton.isChecked()) {
					Logger.log("Button is checked");
			
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

		g.drawSchoolLogo();
		g.drawFPS();
	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
		skin.dispose();
	}
}
