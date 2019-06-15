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
 * Overview when the player click on a defense
 */
public class OverviewGUI implements DrawableObject,UpdateObject {
	
	private BitmapImage image;
	private BitmapImage ringGrey;
	private BitmapFont font;
	
	private Defense defense;
	
	private float x;
	private float y;
	
	private boolean visible = false;

	private float circleScale;
	
	private UpgradeButton upgradeButton;
	private SellButton sellButton;
	
	public OverviewGUI() { 
		image = new BitmapImage("data/images/backGUI.png");
		
		x = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)*image.getImage().getHeight()/image.getImage().getWidth());
		y = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
	
		// FONT
		FileHandle optimusF = Gdx.files.internal("data/font/Fonts/Kenney Pixel Square.ttf");
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(optimusF);
		parameter.size = generator.scaleForPixelHeight((int) (x/10.0));
		parameter.color = Color.WHITE;
		font = generator.generateFont(parameter);
	
		ringGrey = new BitmapImage("data/images/greyCircle.png");
		
		// BUTTONS
		upgradeButton = new UpgradeButton(x,y);
		sellButton = new SellButton(x,y,this);
	}
	
	/**
	 * @param b : set the visible state
	 */
	public void setVisible(boolean b) {
		visible = b;
	}
	
	/**
	 * @return visible
	 */
	public boolean getVisible() {
		return visible;
	}
	
	/**
	 * Func that check if an important part has been clicked
	 *
	 *	 * @param x, y : mouse position
	 */
	public void clicked(int x,int y) {
		if (!(x >= 0 && x <= this.y && y >= 0 && y <= this.x)) {
			setVisible(false);	//close the overview
		}
		
		// button clicked ?
		upgradeButton.clicked(x, y);
		sellButton.clicked(x, y);
	}
	
	/**
	 * Func that set the defense you want to show informations
	 *
	 * @param def : set the defense you want to show informations
	 */
	public void setDefense(Defense def) {
		this.defense = def;
		upgradeButton.setDefense(def);
		sellButton.setDefense(def);
	}
	
	@Override
	public void update(GdxGraphics g) {
		upgradeButton.update(g);
		sellButton.update(g);
		if (defense != null) {
			circleScale = 2*defense.radius*Game.tileSize/ringGrey.getImage().getHeight();
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		if(visible) {
			g.drawAlphaPicture(defense.pos.x,  defense.pos.y, 0, circleScale, 0.4f, ringGrey);	// draw a circle (thats a picture because the transparency is not working with drawCircle)
			g.drawTransformedPicture(y/2, x/2 ,0,y/2, x/2, image);
			g.drawString(0.05f*y, 0.8f*x, defense.defenseName+"\n\n"+"Level : "+defense.level +"\nHits : "+defense.nbHits+"\nDamage : "+ defense.damage,font);
			upgradeButton.draw(g);
			sellButton.draw(g);
		}
	}



}
