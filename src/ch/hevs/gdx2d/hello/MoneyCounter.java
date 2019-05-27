package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class MoneyCounter implements DrawableObject,UpdateObject{

	float x;
	float y;
	
	BitmapFont font;
	
	private int moneyCount;
	
	public MoneyCounter(int startMoney) {
		moneyCount = startMoney;		
		x = 0.98f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.02f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/20.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	}
	
	public void setMoneyCount(int moneyCount) {
		this.moneyCount = moneyCount;
	}
	
	public int getMoneyCount() {
		return moneyCount;
	}

	@Override
	public void update(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		g.drawString(y, x,moneyCount + "$", font);
	}
	
	
}
