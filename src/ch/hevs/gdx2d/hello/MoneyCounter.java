package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * Money counter on the left upper corner
 */
public class MoneyCounter implements DrawableObject,UpdateObject{

	private float x;
	private float y;
	
	private BitmapFont font;
	
	private int moneyCount;
	
	public MoneyCounter(int startMoney) {
		moneyCount = startMoney;		
		x = 0.98f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.02f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		
		// FONT
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/20.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	}
	
	/**
	 * Func that add money
	 *
	 * @param moneyToAdd : the money to add
	 */
	public void addMoneyCount(int moneyToAdd) {
		moneyCount += moneyToAdd;
	}
	
	/**
	 * Func that remove money
	 *
	 * @param moneyToTakeOff : the money to remove
	 */
	public void takeOffMoneyCount(int moneyToTakeOff) {
		moneyCount -= moneyToTakeOff;
	}
	
	/**
	 * Func that get the amount of money
	 *
	 * @return money amount
	 */
	public int getMoneyCount() {
		return moneyCount;
	}

	@Override
	public void update(GdxGraphics g) {
		//nothing
	}

	@Override
	public void draw(GdxGraphics g) {
		g.drawString(y, x,moneyCount + "$", font);
	}
	
	
}
