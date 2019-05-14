package ch.hevs.gdx2d.hello;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.physics.PhysicsWorld;

/**
 * Hello World demo.
 *
 * @author Christopher Métrailler (mei)
 * @author Pierre-André Mudry (mui)
 * @version 2.1
 */
public class Game extends PortableApplication {

	private BitmapImage imgBitmap;
	private BitmapImage[] assets;
	
	private Map mapManager;
	
	int[][][] map = {
			{{1,0},{1,1},{1,2},{1,3},{1,0}},
			{{2,0},{2,1},{2,2},{2,3},{2,0}},
			{{3,0},{3,1},{3,2},{3,3},{3,0}},
			{{4,0},{4,1},{4,2},{4,3},{4,0}}
	};
	
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	float tileSize;
	
	int map0x;
	int map0y;
	

	@Override
	public void onInit() {
		
		// Load a custom image (or from the lib "res/lib/icon64.png")
		imgBitmap = new BitmapImage("data/images/hei-pi.png");
		
		// Load assets
		assets = Utils.loadAssets();

		// Create Map
		mapManager = new Map(map);
		
		
		tiledMap = new TmxMapLoader().load("data/tilemap/test1.tmx");
		
		float screenHeigth = Gdx.graphics.getHeight();
		tileSize = (((int)(screenHeigth/(tiledMap.getProperties().get("width",Integer.class)))/64f));
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,tileSize);	

		map0x = (int)((Gdx.graphics.getWidth()-(tiledMap.getProperties().get("width",Integer.class)*tileSize*64f))/2f);
		map0y = (int)((Gdx.graphics.getHeight()-(tiledMap.getProperties().get("height",Integer.class)*tileSize*64f))/2f);		
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		// Clears the screen
		g.clear();

		// THE GAME
		//g.moveCamera(1000, 1000, 1000, 1000);
		
		//g.zoom(4f);
		//g.moveCamera(position.x, position.y);
		g.moveCamera(-map0x, -map0y);
		g.getCamera().update();
		
		tiledMapRenderer.setView(g.getCamera());
		tiledMapRenderer.render();
		
		
		// Draw everything
		//g.drawTransformedPicture(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, angle, 1.0f, imgBitmap);
		//g.drawStringCentered(Gdx.graphics.getHeight() * 0.8f, "Welcome to gdx2d !");
		//mapManager.drawMap(g,assets);
		//g.drawFPS();
		//g.drawSchoolLogo();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
