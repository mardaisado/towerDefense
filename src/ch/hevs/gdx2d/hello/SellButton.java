package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;
/**
 * SellButton : a button in the overviewGui. can sell a defense
 */
public class SellButton implements DrawableObject,UpdateObject{

	private BitmapImage redButton;
	
	private BitmapFont font;
	
	private float overviewX;
	private float overviewY;
	private float x;
	private float y;
	
	private Defense defense;
	
	private boolean notClickable = false;
	
	final float X = 0.92f;
	final float Y = 0.3f;
	
	private OverviewGUI overview;
	
	public SellButton(float x, float y,OverviewGUI overview) {
		this.overview = overview;
		this.redButton = new BitmapImage("data/images/sellButtonRed.png");
		this.overviewX = x;
		this.overviewY = y;
		
		// FONT
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/10.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
		
		this.x = x*0.2f*redButton.getImage().getHeight()/redButton.getImage().getWidth();
		this.y = x*0.4f;
	}
	
	/**
	 * Func that set the defense to sell
	 *
	 * @param defense : the defense to sell
	 */
	public void setDefense(Defense defense) {
		this.defense = defense;
	}
	
	/**
	 * Func that check if the button is clicked
	 *
	 * @param x, y : mouse position
	 */
	public void clicked(int x,int y) {
		if ((x >= (X*overviewY-2*this.y) && x <= (X*overviewY) && y >= overviewX*Y-this.x && y <= overviewX*Y+this.x)&&!notClickable) {
			if (defense != null) {
				defense.sell();
				overview.setVisible(false);
			}
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture((X*overviewY-y),overviewX*Y,0,y,x, redButton);
		g.drawString((X*overviewY-y),overviewX*Y+overviewX/20,"sell",font,1);
	}

	@Override
	public void update(GdxGraphics g) {
	}
}
