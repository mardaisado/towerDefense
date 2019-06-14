package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * A screen for the end for the game
 */
public class EndScreen extends RenderingScreen {

	private TextButton menuGameButton;
	private Label lblMessage;

	private Skin skin;
	private Stage stage;

	private InputProcessor lastInputProcessor;

	@Override
	public void onInit() {
		int buttonWidth = Gdx.graphics.getWidth()/5;
		int buttonHeight = Gdx.graphics.getHeight()/15;

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

		skin = new Skin(Gdx.files.internal("data/ui/flat-earth/skin/flat-earth-ui.json"));

		lblMessage = new Label("Dommage ! Mais tu peux rejouer ! (Bientot tu pouras regarder une pub pour continuer) ", skin);
		lblMessage.setPosition(Gdx.graphics.getWidth() / 2 - lblMessage.getWidth()/2 , (int) (Gdx.graphics.getHeight() * .5 - lblMessage.getHeight()/2));
		lblMessage.setFontScale(Gdx.graphics.getWidth()/1200);

		menuGameButton = new TextButton("MENU", skin);
		menuGameButton.setWidth(buttonWidth);
		menuGameButton.setHeight(buttonHeight);
		menuGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);

		menuGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3 - buttonHeight/2));

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		parameter.color = Color.WHITE;
		
		stage.addActor(menuGameButton);
		stage.addActor(lblMessage);

		menuGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (menuGameButton.isChecked()) {
					MasterScreen.transition(0);
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
