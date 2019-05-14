package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;
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
	
	final double FRAME_TIME = 0.05; // Duration of each frame
	
	int[][][] map = {
			{{1,0},{1,1},{1,2},{1,3},{1,0}},
			{{2,0},{2,1},{2,2},{2,3},{2,0}},
			{{3,0},{3,1},{3,2},{3,3},{3,0}},
			{{4,0},{4,1},{4,2},{4,3},{4,0}}
	};
	
	Vector<Object> toDraw = new Vector<Object>();
	
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	float tileSize;
	
	int map0x;
	int map0y;
	
	float dt = 0;
	
	final static double PERCENTAGEOFSCREEN =0.5;
	
	public Game() {
		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),false);
	}

	@Override
	public void onInit() {
		
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
		
		toDraw.add(new Tourelle((new Point((10*(int)tileSize*(int)64f),(10*(int)tileSize*(int)64f))),1/tileSize,assets[180],assets[249]));
		toDraw.add(new Mojojo((new Point((10*(int)tileSize*(int)64f),(10*(int)tileSize*(int)64f))),assets[268]));

	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		// Clears the screen
		g.clear();

		//get time
		dt += Gdx.graphics.getDeltaTime();

		// Process update
		if (dt > FRAME_TIME) {
			dt = 0;
			//Update
			for (Object obj : toDraw) {
				((UpdateObject) obj).update(g);
			}
		}

		// Camera fixe
		g.moveCamera(-map0x, -map0y);
		g.getCamera().update();
		
		// Draw map
		tiledMapRenderer.setView(g.getCamera());
		tiledMapRenderer.render();
		
		// draw object
		for (Object obj : toDraw) {
			((DrawableObject) obj).draw(g);
		}
		
		// Draw everything
		//g.drawTransformedPicture(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f, angle, 1.0f, imgBitmap);
		//g.drawStringCentered(Gdx.graphics.getHeight() * 0.8f, "Welcome to gdx2d !");
		//mapManager.drawMap(g,assets);
		//g.drawFPS();
		//g.drawSchoolLogo();
	}
	
	@Override
	public void onClick(int x, int y, int button) {
		// TODO Auto-generated method stub
		super.onClick(x, y, button);
		toDraw.add(new Tourelle(new Point(x-map0x,y-map0y),1/tileSize,assets[180],assets[249]));
		System.out.println(x-map0x);
		System.out.println(y-map0y);
	}
	
	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);
		toDraw.add(new Mojojo((new Point((10*(int)tileSize*(int)64f),(10*(int)tileSize*(int)64f))),assets[268]));
		
		
	}
	
	

	
	public static void main(String[] args) {
		new Game();
	}
}
