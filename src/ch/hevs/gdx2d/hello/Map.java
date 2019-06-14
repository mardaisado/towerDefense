package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class Map {

	private int[][][] map;
	final int HEIGTH;
	final int WIDTH;

	public Map(int[][][] map) {
		this.map = map;

		if((int) (0.5*(double)Gdx.graphics.getWidth()/(double)map[0].length) > (int) (0.5*(double)Gdx.graphics.getHeight()/(double)map.length)) {
			this.WIDTH = (int) (0.5*(double)Gdx.graphics.getHeight()/(double)map.length);
		}
		else {
			this.WIDTH = (int) (0.5*(double)Gdx.graphics.getWidth()/(double)map[0].length);
		}

		this.HEIGTH = WIDTH;
	}

	public void drawMap(GdxGraphics g, BitmapImage[] assets) {

		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				g.drawTransformedPicture(WIDTH +WIDTH*i*2,-HEIGTH+HEIGTH*(map.length-j)*2, map[j][i][1]*90, WIDTH, HEIGTH, assets[map[j][i][0]]);
			}
		}
	}
}
