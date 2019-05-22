package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class PickDefenseGUI implements DrawableObject,UpdateObject{

	BitmapImage[] assets;
	BitmapImage image;
	BitmapImage t1;
	BitmapImage[] t;
	float x;
	float y;
	float facteur;
	
	public PickDefenseGUI(BitmapImage[] assets) {
		this.assets = assets;
		image = new BitmapImage("data/images/backDefense.png");
		t1 = new BitmapImage("data/images/t1.png");
		x = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f)*image.getImage().getWidth()/image.getImage().getHeight());
		facteur = (float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/(float)image.getImage().getHeight();

		BitmapImage[] tmp = {
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png"),
			new BitmapImage("data/images/t1.png")
		};
		t = tmp;
	}
	
	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(x+y/2, x/2 ,0,y/2, x/2, image);
		
		for (int i = 0; i < (t.length/2+t.length%2); i++) {
			
			for (int j = 0; j < 2; j++) {
				if(i*2+j < t.length) {
					g.drawTransformedPicture(x+(95+110*j)*facteur, x-(95+110*i) * facteur ,0,90*facteur/2, 90*facteur/2, t[i*2+j]);
				}
			}
		}
		
	}

	@Override
	public void update(GdxGraphics g) {
		
		
	}

}
