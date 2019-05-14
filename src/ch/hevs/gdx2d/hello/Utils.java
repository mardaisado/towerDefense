package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;

public class Utils {
	public static BitmapImage[] loadAssets() {
		BitmapImage[] tmp = new BitmapImage[299];
		String tmp2 = "";
		for (int i = 0; i < tmp.length; i++) {
			tmp2 = String.format("%03d", i);
			tmp[i] = new BitmapImage("data/assets/PNG/Retina/towerDefense_tile" + tmp2 + ".png");
		}
		return tmp;
	}
}
