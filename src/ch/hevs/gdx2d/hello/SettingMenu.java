package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Setting Menu
 *
 */
public class SettingMenu extends RenderingScreen {
	
	Skin skin;
	
	Stage stage;
	
	TextButton quitGameButton;
			
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		
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
		Gdx.input.setInputProcessor(stage);
		
		quitGameButton = new TextButton("EXIT", skin);
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);
		quitGameButton.getLabel().setFontScale(Gdx.graphics.getWidth()/1200);		
		quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .3));
		
		stage.addActor(quitGameButton);

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
