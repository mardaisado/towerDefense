package ch.hevs.gdx2d.hello;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class Dragable implements DrawableObject {
	BitmapImage image;
	float posX;
	float posY;
	float width;
	DefenseProperties properties;
	
	BitmapFont font;
	
	public Dragable(DefenseProperties properties,float posX,float posY,float width) {
		this.properties = properties;
		image = new BitmapImage(properties.pickImage);
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (Gdx.graphics.getHeight()/50.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	}
	
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(posX,posY,0,width,width,image);
		g.drawString(posX, posY-Gdx.graphics.getHeight()/20, properties.price+"$",font, Align.center);
	}
	public boolean check(int x, int y) {
		if (x >= posX-width && x <= posX+width && y >= posY-width && y <= posY+width) {
			return true;
		}
		return false;
	}
}
