package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;


import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.util.Vector;


/**
 * Play button that start every rounds
 */
public class PlayButton implements DrawableObject, UpdateObject{

	public boolean play = false;

	private float x;
	private float y;
	private float scale;	

	private BitmapImage playButton;

	public PlayButton(Vector<Ennemi> ennemi) {
		playButton = new BitmapImage("data/images/play2_white.png");
		
		x = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		scale =  0.1f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/playButton.getImage().getHeight();
	}

	/**
	 * Func that check if the button is pressed and play a new round
	 *
	 * @param x, y : mouse position
	 * @param roundManager : round manager for starting a new round
	 */
	public void clicked(int x,int y,RoundManager roundManager) {
		if(x >= this.y-scale*playButton.getImage().getHeight()/2 && x <= this.y+scale*playButton.getImage().getHeight()/2  && y >= this.x-scale*playButton.getImage().getHeight()/2  && y <= this.x+scale*playButton.getImage().getHeight()/2 ) {

			if (!play) {
				play = true;
				roundManager.play();
			}
		}
	}

	@Override
	public void draw(GdxGraphics g) {
		if (!play) {
			g.drawTransformedPicture(y, x, 0,scale, playButton);
		}
	}

	@Override
	public void update(GdxGraphics g) {

	}
}
