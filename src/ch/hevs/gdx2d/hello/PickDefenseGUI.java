package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * Just an image where drawables are draw
 */
public class PickDefenseGUI implements DrawableObject,UpdateObject{

	private BitmapImage image;
	
	public float x;
	public float y;
	public float facteur;

	public PickDefenseGUI() {
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
		//nothing
	}

}
