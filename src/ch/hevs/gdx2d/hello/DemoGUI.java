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
public class DemoGUI extends RenderingScreen {
	Skin skin;
	Stage stage;
	ImageButton newGameButton, quitGameButton, settingGameButton;

	BitmapFont font;
	TextureAtlas buttonAtlas;
	InputProcessor lastInputProcessor;
	
	@Override
	public void onInit() {
		int buttonWidth = (int) (250 );
		int buttonHeight = (int) (40);

		stage = new Stage();
		lastInputProcessor = Gdx.input.getInputProcessor();
		Gdx.input.setInputProcessor(stage);// Make the stage consume events

		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		newGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown_pressed.png")))));
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
		
		settingGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown_pressed.png")))));
		settingGameButton.setWidth(buttonWidth);
		settingGameButton.setHeight(buttonHeight);

		quitGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown.png")))),new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/ui/buttonLong_brown_pressed.png")))));
		quitGameButton.setWidth(buttonWidth);
		quitGameButton.setHeight(buttonHeight);

		newGameButton.setPosition((Gdx.graphics.getWidth()/2 - 250/2), (int) (Gdx.graphics.getHeight() * .7));
		settingGameButton.setPosition((Gdx.graphics.getWidth()/2- 250/2), (int) (Gdx.graphics.getHeight() * .6));
		quitGameButton.setPosition((Gdx.graphics.getWidth()/2 - 250/2), (int) (Gdx.graphics.getHeight() * .5));

		
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
					DemoScreen.transition(1);
				}			
					
			}
		});
		
		settingGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if (settingGameButton.isChecked()) {
					DemoScreen.transition(3);
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
		g.clear(Color.BLACK);
		
		
		// This is required for having the GUI work properly
		stage.act();
		stage.draw();
		
		g.drawString(Gdx.graphics.getWidth() / 2  , (int) (Gdx.graphics.getHeight() * .7 + newGameButton.getHeight() /2), "Play" ,font,1);
		g.drawString(Gdx.graphics.getWidth() / 2  , (int) (Gdx.graphics.getHeight() * .6 + settingGameButton.getHeight() /2), "Settings" ,font,1);
		g.drawString(Gdx.graphics.getWidth() / 2  , (int) (Gdx.graphics.getHeight() * .5 + quitGameButton.getHeight() /2), "Quit" ,font,1);

	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.input.setInputProcessor(lastInputProcessor);
		stage.dispose();
	}
}
