package ch.hevs.gdx2d.hello;

import java.util.Vector;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class PickDefenseGUI implements DrawableObject,UpdateObject{

	BitmapImage image;
	float x;
	float y;
	float facteur;
	
	public PickDefenseGUI(Vector<Dragable> dragable) {
		image = new BitmapImage("data/images/backDefense.png");
		x = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)*image.getImage().getWidth()/image.getImage().getHeight());
		facteur = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/(float)image.getImage().getHeight();
	}
	
	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(x+y/2, x/2 ,0,y/2, x/2, image);
	}

	@Override
	public void update(GdxGraphics g) {
		
		
	}

}
