package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Map {

	int[][] map;
	final int HEIGTH;
	final int WIDTH;
	
	
	public Map(int[][] map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.HEIGTH = (int) (0.5*(double)Gdx.graphics.getHeight()/(double)map.length);
		this.WIDTH = (int) (0.5*(double)Gdx.graphics.getWidth()/(double)map[0].length);
	}
	
	public void drawMap(GdxGraphics g, BitmapImage[] assets) {
		
//		for (int j = 0; j < assets.length; j++) {
//			g.drawTransformedPicture(posX, posY, angle, width, height, assets[i]);
//		}
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				g.drawTransformedPicture(WIDTH +WIDTH*i*2,-HEIGTH+HEIGTH*(map.length-j)*2, 0, WIDTH, HEIGTH, assets[map[j][i]]);
			}
		}
		
		
	}
}
