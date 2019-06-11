package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.utils.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Pierre-André Mudry on 07.05.2015.
 * HES-SO Valais, 2015
 */
public class Menu extends RenderingScreen {
	
	Skin skin;
	Stage stage;
	TextButton newGameButton, quitGameButton, settingGameButton;
	TextField textArea;
	static private ScreenManager s;
	
	@Override
	public void onInit() {
		//Logger.log();
		//System.out.println(String.format("%3d", 3));
		
		int buttonWidth = Gdx.graphics.getWidth()/10;
		int buttonHeight = Gdx.graphics.getHeight()/10;

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		// Load the default skin (which can be configured in the JSON file)
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));

		newGameButton = new TextButton("PLAY", skin); // Use the initialized skin
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
	

		quitGameButton = new TextButton("EXIT", skin); // Use the initialized skin
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);
		
		settingGameButton = new TextButton("SETTINGS", skin); // Use the initialized skin
		settingGameButton.setWidth(buttonWidth);
		settingGameButton.setHeight(buttonHeight);
	

	
		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));
		settingGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .1));
//		textArea = new TextField("Enter some text...", skin);
//		textArea.setWidth(buttonWidth);
//		textArea.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));

//		textArea.setTextFieldListener(new TextFieldListener() {
//			public void keyTyped(TextField textField, char key) {
//				textArea.setSelection(0, 0);
//
//				// When you press 'enter', do something
//				if (key == 13)
//					Logger.log("You have typed " + textArea.getText());
//			}
//		});

		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(quitGameButton);
		stage.addActor(settingGameButton);
		//stage.addActor(textArea);

		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (quitGameButton.isChecked())
					//Logger.log("Button is checked");
					Gdx.app.exit();
				else
					Logger.log("Button is not checked");
			}
		});
		
		/**
		 * Register listener
		 */
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (newGameButton.isChecked())
					//Logger.log("Button is checked");
					s.activateScreen(1);
				else
					Logger.log("Button is not checked");
			}
		});
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);

		// This is required for having the GUI work properly
		stage.act();
		stage.draw();

		//g.drawStringCentered(Gdx.graphics.getHeight() / 4, "Button status " + newGameButton.isChecked());
		g.drawSchoolLogo();
		g.drawFPS();
	}
	
	@Override
	public void onKeyDown(int keycode) {
		// TODO Auto-generated method stub
		super.onKeyDown(keycode);
		
		
	}

	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
		skin.dispose();
	}
	
	static public void addScreenManager(ScreenManager s1) {
		s = s1;
	}
}



