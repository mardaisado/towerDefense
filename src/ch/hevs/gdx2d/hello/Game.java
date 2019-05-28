package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.awt.Toolkit;
import java.util.Iterator;
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
	Vector<Ennemi> ennemi = new Vector<Ennemi>();
	Vector<Defense> defense = new Vector<Defense>();
	Vector<Dragable> dragable = new Vector<Dragable>();
	Vector<Projectile> projectile = new Vector<Projectile>();
	
	static TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	static float tileSize;
	Point lastClick;
	Preview preview;
	PickDefenseGUI pickGui;
	OverviewGUI defenseGui;
	
	TiledMapTileLayer tiledLayer;
	
	int map0x;
	int map0y;
	
	float dt = 0;
	
	static MoneyCounter money = null;
	
	final static double PERCENTAGEOFSCREEN =1.5;
	final static boolean FULLSCREEN = false;
	final static int START_MONEY = 1000;
	
	// { pick image, dragable image, radius}
	Object[][] defenseChoice = {
			{"data/images/t1.png","data/images/t1_p.png",200f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",100f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",100f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",300f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",400f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",500f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",600f},
			{"data/images/t1.png","data/assets/PNG/Retina/towerDefense_tile271.png",100f}
	};	
	public Game() {
		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),FULLSCREEN);
	}

	@Override
	public void onInit() {
		
		setTitle("Best Tower Defense Game you've ever seen");
		
		// Load assets
		assets = Utils.loadAssets();

		// Create Map
		mapManager = new Map(map);
		
		
		tiledMap = new TmxMapLoader().load("data/tilemap/test1.tmx");
		
		float screenHeigth = Gdx.graphics.getHeight();
		tileSize = (((int)(screenHeigth/(tiledMap.getProperties().get("width",Integer.class)))/64f));
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap,tileSize);	
		tiledLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

		map0x = (int)((Gdx.graphics.getWidth()-(tiledMap.getProperties().get("width",Integer.class)*tileSize*64f))/2f);
		map0y = (int)((Gdx.graphics.getHeight()-(tiledMap.getProperties().get("height",Integer.class)*tileSize*64f))/2f);
		
		preview = new Preview();
		pickGui = new PickDefenseGUI(dragable);
		defenseGui = new OverviewGUI();
		money = new MoneyCounter(START_MONEY);
		
		
		for (int i = 0; i < (defenseChoice.length/2+defenseChoice.length%2); i++) {
			for (int j = 0; j < 2; j++) {
				if(i*2+j < defenseChoice.length) {
					dragable.add(new Dragable(defenseChoice[i*2+j],pickGui.x+(95+110*j)*pickGui.facteur, pickGui.x-(95+110*i) * pickGui.facteur ,90*pickGui.facteur/2));
				}
			}
		}
		
		//preview.setImage(assets[271], tileSize);
		toDraw.add(defenseGui);
		toDraw.add(pickGui);
		toDraw.add(money);
		//toDraw.add(preview);
		//projectile.add(new Projectile(new Point(0, 0), new Point(0, 0), tileSize, assets[180]));
		//toDraw.add(new Tourelle((new Point((int)((1-0.5)*tileSize*64f),(int)((1-0.5)*tileSize*64f))),tileSize,assets[180],assets[249],assets,ennemis,projectile));
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
			for (Defense obj : defense) {
				((UpdateObject) obj).update(g);
			}
			
			Iterator<Ennemi> q = ennemi.iterator();
			while(q.hasNext()) {
				Ennemi obj;
				obj = q.next();
				if (((Ennemi) obj).update(g)) {
					q.remove();
				}
			}
			
			
			Iterator<Projectile> p = projectile.iterator();
			while(p.hasNext()) {
				Projectile obj;
				obj = p.next();
				if (((Projectile) obj).update(g)) {
					p.remove();
				}
			}
			
			//System.out.println("size "+ennemi.size());
		}

		// Camera fixe
		g.moveCamera(-map0x, -map0y);
		g.getCamera().update();
		
		// Draw map
		tiledMapRenderer.setView(g.getCamera());
		tiledMapRenderer.render();
		
		// draw object
		
		for (Defense obj : defense) {
			((DrawableObject) obj).draw(g);
		}
		
		for (Ennemi obj : ennemi) {
			((DrawableObject) obj).draw(g);
		}
		for (Projectile obj : projectile) {
			((DrawableObject) obj).draw(g);
		}
		
		for (Object obj : toDraw) {
			((DrawableObject) obj).draw(g);
		}
		
		for (Dragable obj : dragable) {
			((DrawableObject) obj).draw(g);
		}
		
		preview.draw(g);

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
		//toDraw.add(new Tourelle(new Point(x-map0x,y-map0y),tileSize,assets[180],assets[249],assets,ennemis,projectile));
		lastClick = new Point(x-map0x, y-map0y);
		//find overview
		if (defenseGui.getVisible()) {
			defenseGui.clicked(x-map0x, y-map0y);
		}
		Defense def = Utils.getDefenseClicked(defense,x-map0x, y-map0y);
		if (def != null) {
			defenseGui.setVisible(true);
			defenseGui.setDefense(def);
		}
//		System.out.println((int)((x-map0x)/(tileSize*64f)));
//		System.out.println((int)((y-map0y)/(tileSize*64f)));
//		System.out.println(x-map0x);
//		System.out.println(y-map0y);
		//System.out.println("yes: "+x +" / "+y);
//		// is walkable
//		System.out.println(Utils.isWalkable(Utils.getTile(new Point(x-map0x,y-map0y), tiledLayer)));
	}
	
	@Override
	public void onRelease(int x, int y, int button) {
		// TODO Auto-generated method stub
		super.onRelease(x, y, button);
		if (preview.getVisible()) {
			preview.setVisible(false);
			if(Utils.getTile(new Point((int) ((x-map0x) /tileSize),(int) ((y-map0y) /tileSize)),tiledLayer).getProperties().get("posable")!=null)
			{
				System.out.println("Not null");
				if((boolean) Utils.getTile(new Point((int) ((x-map0x) /tileSize),(int) ((y-map0y) /tileSize)),tiledLayer).getProperties().get("posable") == true)
				{
					System.out.println("true");
					if(money.getMoneyCount()>= Tourelle.PRICE) {
						defense.add(new Tourelle(new Point(x-map0x,y-map0y),tileSize,assets[180],assets[249],assets,ennemi,projectile));
						money.takeOffMoneyCount(Tourelle.PRICE);
					}
				}
				
			}

		}
	}
	
	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);
		int x=0;
		double y=13.5;
		ennemi.add(new Mojojo(tileSize,assets[299],tiledMap,100,100));
	}
	
	
	@Override
	public void onDrag(int x, int y) {
		super.onDrag(x, y);
		for (Dragable obj : dragable) {
			boolean h = ((Dragable) obj).check(lastClick.x,lastClick.y);
			if(h == true) {
				//print preview
				//((Dragable) obj).
							
				if (!preview.getVisible()) {
					preview.setVisible(true);
					preview.setRadius((float)obj.defense[2]);
					preview.setImage(new BitmapImage((String)obj.defense[1]), tileSize);
				}

				preview.move(x-map0x, y-map0y);
				
			}
		}
		//System.out.println(x +" / "+y);
	}
	
	

	
	public static void main(String[] args) {
		new Game();
	}
}
