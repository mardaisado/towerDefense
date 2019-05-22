package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;
import ch.hevs.gdx2d.lib.physics.PhysicsWorld;

/**
 * The Tower Defense 
 *
 * @author Mardaisado (Aurélien Héritier)
 * @author Limace (Jérémy Merle)
 * @version 0.3
 */
public class Game extends PortableApplication {

	private BitmapImage imgBitmap;
	private BitmapImage[] assets;
	
	private Map mapManager;
	
	final double FRAME_TIME = 0.015; // Duration of each frame
	
	int[][][] map = {
			{{1,0},{1,1},{1,2},{1,3},{1,0}},
			{{2,0},{2,1},{2,2},{2,3},{2,0}},
			{{3,0},{3,1},{3,2},{3,3},{3,0}},
			{{4,0},{4,1},{4,2},{4,3},{4,0}}
	};
	
	Vector<Object> toDraw = new Vector<Object>();
	Vector<Ennemi> ennemis = new Vector<Ennemi>();
	Vector<Projectile> projectile = new Vector<Projectile>();
	
	static TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	static float tileSize;
	
	
	TiledMapTileLayer tiledLayer;
	
	int map0x;
	int map0y;
	
	float dt = 0;
	
	final static double PERCENTAGEOFSCREEN =1.5;
	final static boolean FULLSCREEN = true;
	
	public Game() {
		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),FULLSCREEN);
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
		tiledLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Calque 2");

		map0x = (int)((Gdx.graphics.getWidth()-(tiledMap.getProperties().get("width",Integer.class)*tileSize*64f))/2f);
		map0y = (int)((Gdx.graphics.getHeight()-(tiledMap.getProperties().get("height",Integer.class)*tileSize*64f))/2f);
		
		toDraw.add(new PickDefenseGUI(assets));
		//projectile.add(new Projectile(new Point(0, 0), new Point(0, 0), tileSize, assets[180]));
		toDraw.add(new Tourelle((new Point((int)((1-0.5)*tileSize*64f),(int)((1-0.5)*tileSize*64f))),tileSize,assets[180],assets[249],assets,ennemis,projectile));
		//ennemis.add(new Mojojo((new Point((int)((10-0.5)*tileSize*64f),(int)((10-0.5)*tileSize*64f))),tileSize,assets[268]));
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
			for (Ennemi obj : ennemis) {
				((UpdateObject) obj).update(g);
			}
			for (Projectile obj : projectile) {
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
		
		for (Ennemi obj : ennemis) {
			((DrawableObject) obj).draw(g);
		}
		for (Projectile obj : projectile) {
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
		super.onClick(x, y, button);
		toDraw.add(new Tourelle(new Point(x-map0x,y-map0y),tileSize,assets[180],assets[249],assets,ennemis,projectile));
		System.out.println((int)((x-map0x)/(tileSize*64f)));
		System.out.println((int)((y-map0y)/(tileSize*64f)));
		// is walkable
		System.out.println(Utils.isWalkable(Utils.getTile(new Point(x-map0x,y-map0y), tiledLayer)));
	}
	
	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);
		int x=0;
		double y=13.5;
		ennemis.add(new Mojojo((new Point((int)((x)*tileSize*64f),(int)((y)*tileSize*64f))),tileSize,assets[268]));
	}
	
	

	
	public static void main(String[] args) {
		new Game();
	}
}
