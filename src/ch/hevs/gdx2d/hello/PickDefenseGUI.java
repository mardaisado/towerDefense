package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class PickDefenseGUI implements DrawableObject,UpdateObject{

	BitmapImage[] assets;
	BitmapImage image;
	BitmapImage t1;
	Dragable[] t;
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

		String[] tmp = {
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png",
			"data/images/t1.png"
		};
		
		t = new Dragable[tmp.length];
		
		for (int i = 0; i < (t.length/2+t.length%2); i++) {
			
			for (int j = 0; j < 2; j++) {
				if(i*2+j < t.length) {
					t[i*2+j] = new Dragable(tmp[i*2+j],x+(95+110*j)*facteur, x-(95+110*i) * facteur ,90*facteur/2);
				}
			}
		}
		
	}
	
	@Override
	public void draw(GdxGraphics g) {
		g.drawTransformedPicture(x+y/2, x/2 ,0,y/2, x/2, image);
		
		for (int i = 0; i < t.length; i++) {
			t[i].draw(g);
		}
		
	}

	@Override
	public void update(GdxGraphics g) {
		
		
	}

}
