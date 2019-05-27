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

public class UpgradeButton implements DrawableObject{

	BitmapImage greyButton;
	BitmapImage greenButton;
	
	BitmapFont font;
	
	float overviewX;
	float overviewY;
	float x;
	float y;
	
	Defense defense;
	
	boolean notClickable = false;
	
	public UpgradeButton(float x, float y) {
		greyButton = new BitmapImage("data/images/upgradeButtonGrey.png");
		greenButton = new BitmapImage("data/images/upgradeButtonGreen.png");
		this.overviewX = x;
		this.overviewY = y;
		
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/10.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
		
		this.x = x*0.3f*greenButton.getImage().getHeight()/greenButton.getImage().getWidth();
		this.y = x*0.3f;
		
	}
	
	public void setDefense(Defense defense) {
		this.defense = defense;
		if (defense.level < 3) {
			notClickable = false;
		}
		else{
			notClickable = true;
		}
	}
	
	public void clicked(int x,int y) {
		if ((x >= (0.92f*overviewY-2*this.y) && x <= (0.92f*overviewY) && y >= overviewX/2-this.x && y <= overviewX/2+this.x)) {
			defense.upgrade();
			if (defense.level < 3) {
				notClickable = false;
			}
			else{
				notClickable = true;
			}
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		if (defense.level >= 3) {
			g.drawTransformedPicture((0.92f*overviewY-y),overviewX/2 ,0,y,x, greyButton);
			g.drawString((0.92f*overviewY-y),overviewX/2+overviewX/20,"max",font,1);
		}
		else {
			g.drawTransformedPicture((0.92f*overviewY-y),overviewX/2 ,0,y,x, greenButton);
			g.drawString((0.92f*overviewY-y),overviewX/2+overviewX/20,defense.nextUpgradePrice()+"$" ,font,1);
		}
		
		
	}
}
