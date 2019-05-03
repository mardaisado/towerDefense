package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.physics.PhysicsWorld;
import ch.hevs.gdx2d.desktop.PortableApplication;

/**
 * Hello World demo.
 *
 * @author Christopher Métrailler (mei)
 * @author Pierre-André Mudry (mui)
 * @version 2.1
 */
public class Game extends RenderingScreen {

	private BitmapImage imgBitmap;
	private BitmapImage[] assets;
	
	private Map mapManager;
	
	int[][] map = {
			{1,1,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5},
			{1,2,3,4,5}
	};

	@Override
	public void onInit() {

		// Load a custom image (or from the lib "res/lib/icon64.png")
		imgBitmap = new BitmapImage("data/images/hei-pi.png");
		
		// Load assets
		assets = loadAssets();

		// Create Map
		mapManager = new Map(map);
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		// Clears the screen
		g.clear();

		// Compute the angle of the image using an elastic interpolation
		//float t = computePercentage();
		float angle = 0;

		// Draw everything
		//g.drawTransformedPicture(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, angle, 1.0f, imgBitmap);
		//g.drawStringCentered(Gdx.graphics.getHeight() * 0.8f, "Welcome to gdx2d !");
		mapManager.drawMap(g,assets);
		//g.drawFPS();
		//g.drawSchoolLogo();
	}
	
	@Override
	public void dispose() {
		imgBitmap.dispose();
		PhysicsWorld.dispose();
		super.dispose();
	}
	
	public static BitmapImage[] loadAssets() {
		BitmapImage[] tmp = new BitmapImage[299];
		String tmp2 = "";
		for (int i = 0; i < tmp.length; i++) {
			tmp2 = String.format("%03d", i);
			tmp[i] = new BitmapImage("data/assets/PNG/Retina/towerDefense_tile" + tmp2 + ".png");
		}
		return tmp;
	}

//	public static void main(String[] args) {
//		new Game();
//	}
}
