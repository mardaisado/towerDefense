package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

import java.util.Vector;

import org.json.simple.JSONArray;

public class PlayButton implements DrawableObject, UpdateObject{

	BitmapImage playButton,pauseButton;
	BitmapImage fastButton;
	BitmapImage fastButtonClicked;

	boolean play = false;
	boolean fast = false;

	float x;
	float y;
	float scale;

	Vector<Ennemi> ennemi;

	int [] need = {1,0,1,0,1,0,0};

	int index=0; 
	
	JSONArray listOfEnnemi;
	int timeDelta=0;
	double frame_time;
	public PlayButton(Vector<Ennemi> ennemi) {
		// TODO Auto-generated constructor stub
		x = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		y = 0.92f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f));
		playButton = new BitmapImage("data/images/play2.png");
		fastButton = new BitmapImage("data/images/fastButton.png");
		pauseButton = new BitmapImage("data/images/blank.png");
		fastButtonClicked = new BitmapImage("data/images/fastButtonClicked.png");
		this.frame_time=Game.FRAME_TIME;
		this.ennemi=ennemi;
		scale =  0.1f*(float)((Game.tiledMap.getProperties().get("height",Integer.class)*Game.tileSize*64f))/playButton.getImage().getHeight();
	}

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
		// TODO Auto-generated method stub
		if (play) {
			g.drawTransformedPicture(y, x, 0,scale, pauseButton);
		}
		else {
			g.drawTransformedPicture(y, x, 0,scale, playButton);
		}
	}

	@Override
	public void update(GdxGraphics g) {
		// TODO Auto-generated method stub
		
	}
}
