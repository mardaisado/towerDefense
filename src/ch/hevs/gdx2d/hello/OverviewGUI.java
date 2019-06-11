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

public class OverviewGUI implements DrawableObject,UpdateObject {
	BitmapImage image;
	BitmapImage ringGrey;
	float x;
	float y;
	float facteur;
	boolean visible = false;
	Defense defense;
	//Color ringGrey;
	float circleScale;
	
	
	BitmapFont font;
	
	UpgradeButton upgradeButton;
	
	public OverviewGUI() { 
		//this.assets = assets;
		image = new BitmapImage("data/images/backGUI.png");
//		x = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
//		y = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)*image.getImage().getWidth()/image.getImage().getHeight());
		x = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)*image.getImage().getHeight()/image.getImage().getWidth());
		y = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		facteur = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/(float)image.getImage().getHeight();
	
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/10.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	
		//ringGrey = new Color(0.5f, 0.5f, 0.5f, 0.4f);
		ringGrey = new BitmapImage("data/images/greyCircle.png");
		
		upgradeButton = new UpgradeButton(x,y);
		
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public void clicked(int x,int y) {
		if (!(x >= 0 && x <= this.y && y >= 0 && y <= this.x)) {
			setVisible(false);
		}
		upgradeButton.clicked(x, y);
	}
	
	public void setDefense(Defense def) {
		this.defense = def;
		upgradeButton.setDefense(def);
	}
	
	@Override
	public void update(GdxGraphics g) {
		// TODO Auto-generated method stub
		upgradeButton.update(g);
		if (defense != null) {
			circleScale = 2*defense.radius*Game.tileSize/ringGrey.getImage().getHeight();
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		if(visible) {
			g.drawAlphaPicture(defense.pos.x,  defense.pos.y, 0, circleScale, 0.4f, ringGrey);
			//g.drawFilledCircle(defense.pos.x, defense.pos.y, ((Tourelle)defense).radius*Game.tileSize , ringGrey);
			g.drawTransformedPicture(y/2, x/2 ,0,y/2, x/2, image);
			g.drawString(0.05f*y, 0.8f*x, defense.defenseName+"\n\n"+"Level : "+defense.level +"\nHits : "+defense.nbHits+"\nDamage : "+ defense.damage,font);
			upgradeButton.draw(g);
		}
	}

}
