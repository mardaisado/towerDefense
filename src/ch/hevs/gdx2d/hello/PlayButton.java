package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public class PlayButton implements DrawableObject,UpdateObject{

	BitmapImage playButton;
	BitmapImage fastButton;
	
	boolean play = false;
	
	float x;
	float y;
	float scale;
	
	public PlayButton() {
		// TODO Auto-generated constructor stub
		x = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		
		playButton = new BitmapImage("data/images/playButton.png");
		fastButton = new BitmapImage("data/images/fastButton.png");
		
		scale =  0.1f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/playButton.getImage().getHeight();
	}

	@Override
	public void update(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public void clicked(int x,int y) {
		if(x >= this.y-scale*playButton.getImage().getHeight()/2 && x <= this.y+scale*playButton.getImage().getHeight()/2  && y >= this.x-scale*playButton.getImage().getHeight()/2  && y <= this.x+scale*playButton.getImage().getHeight()/2 ) {
			play = !play;
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		// TODO Auto-generated method stub
		if (play) {
			g.drawTransformedPicture(y, x, 0,scale, fastButton);
		}
		else {
			g.drawTransformedPicture(y, x, 0,scale, playButton);
		}
	}
}
