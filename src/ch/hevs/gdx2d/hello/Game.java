package ch.hevs.gdx2d.hello;

import java.awt.Point;
import java.time.Instant;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;
import ch.hevs.gdx2d.lib.utils.Logger;

/**
 * The Tower Defense
 *
 * @author Mardaisado (Aurélien Héritier)
 * @author Limace (Jérémy Merle)
 * @version 0.3
 */
public class Game extends RenderingScreen {
	static final double FRAME_TIME = 0.015; // Duration of each frame

	Vector<Object> toDraw = new Vector<Object>();
	Vector<Ennemi> ennemi = new Vector<Ennemi>();
	Vector<Defense> defense = new Vector<Defense>();
	Vector<Dragable> dragable = new Vector<Dragable>();
	Vector<Object> projectile = new Vector<Object>();

	static TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	static float tileSize;
	Point lastClick;
	Preview preview;
	PickDefenseGUI pickGui;
	OverviewGUI defenseGui;
	PlayButton playButton;
	RoundManager roundManager;
	TiledMapTileLayer tiledLayer;
	Dragable nowDragable;

	int map0x;
	int map0y;

	int defSelec = 0;

	float dt = 0;

	static MoneyCounter money = null;
	
	static Sound sound= Gdx.audio.newSound(Gdx.files.internal("data/sound/soundtrack.wav"));
	
	final static double PERCENTAGEOFSCREEN = 1.5;
	final static boolean FULLSCREEN = false;
	final static int START_MONEY = 1000;
	final static int MAX_LEVEL = 3;
	final static float PERCENTAGEOFREWARD = 0.5f;

	// { pick image, dragable image, radius, class, price}
	static DefenseProperties[] defenseProperties = {
			new DefenseProperties("data/images/t1.png", "data/images/t1_p.png", 200f, "ch.hevs.gdx2d.hello.Tourelle", 100),
			new DefenseProperties("data/images/t2.png", "data/images/t2_p.png", 250f, "ch.hevs.gdx2d.hello.Tourelle2", 150),
			new DefenseProperties("data/images/t3.png", "data/images/t3_p.png", 300f, "ch.hevs.gdx2d.hello.Tourelle3", 300),
			new DefenseProperties("data/images/t4.png", "data/images/t4_p.png", 200f, "ch.hevs.gdx2d.hello.Tourelle4", 1000)
	};
	
	@Override
	public void onInit() {
		Logger.dbg("Game", "Tower Defense Game v1.0.0, | aurher, jermer (c) 2019");

		// setTitle("Best Tower Defense Game you've ever seen");

//		Pixmap pm = new Pixmap(Gdx.files.internal("/data/ui/crosshair123.png"));
//		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		
		tiledMap = new TmxMapLoader().load("data/tilemap/"+ DemoScreen.mapSelector +".tmx");

		float screenHeigth = Gdx.graphics.getHeight();
		tileSize = (((int) (screenHeigth / (tiledMap.getProperties().get("width", Integer.class))) / 64f));

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, tileSize);
		tiledLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);

		map0x = (int) ((Gdx.graphics.getWidth()
				- (tiledMap.getProperties().get("width", Integer.class) * tileSize * 64f)) / 2f);
		map0y = (int) ((Gdx.graphics.getHeight()
				- (tiledMap.getProperties().get("height", Integer.class) * tileSize * 64f)) / 2f);

		preview = new Preview();
		pickGui = new PickDefenseGUI(dragable);
		defenseGui = new OverviewGUI();
		money = new MoneyCounter(START_MONEY);
		playButton = new PlayButton(ennemi);
		roundManager = new RoundManager(ennemi, playButton);

		for (int i = 0; i < (defenseProperties.length / 2 + defenseProperties.length % 2); i++) {
			for (int j = 0; j < 2; j++) {
				if (i * 2 + j < defenseProperties.length) {
					dragable.add(new Dragable(defenseProperties[i * 2 + j], pickGui.x + (95 + 110 * j) * pickGui.facteur,
							pickGui.x - (95 + 110 * i) * pickGui.facteur, 90 * pickGui.facteur / 2));
				}
			}
		}

		// preview.setImage(assets[271], tileSize);
		toDraw.add(roundManager);
		toDraw.add(defenseGui);
		toDraw.add(pickGui);
		toDraw.add(money);
		toDraw.add(playButton);
		// toDraw.add(preview);
		// projectile.add(new Projectile(new Point(0, 0), new Point(0, 0), tileSize,
		// assets[180]));
		// toDraw.add(new Tourelle4((new
		// Point((int)((24-0.5)*tileSize*64f),(int)((3-0.5)*tileSize*64f))),ennemi,projectile));
		// ennemis.add(new Mojojo((new
		// Point((int)((10-0.5)*tileSize*64f),(int)((10-0.5)*tileSize*64f))),tileSize,assets[268]));
		sound.play();
		sound.loop();
		
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		// Clears the screen
		g.clear();

		// get time
		dt += Gdx.graphics.getDeltaTime();

		// Process update
		if (dt > FRAME_TIME) {
			dt = 0;
			// Update
			for (Object obj : toDraw) {
				((UpdateObject) obj).update(g);
			}
			
			Iterator<Defense> f = defense.iterator();
			while (f.hasNext()) {
				Defense obj;
				obj = f.next();
				if (((Defense) obj).update(g)) {
					f.remove();
				}
			}

			Iterator<Ennemi> q = ennemi.iterator();
			while (q.hasNext()) {
				Ennemi obj;
				obj = q.next();
				if (((Ennemi) obj).update(g)) {
					q.remove();
				}
			}

			Iterator<Object> p = projectile.iterator();
			while (p.hasNext()) {
				Object obj;
				obj = p.next();
				if (((DeleteObject) obj).update(g)) {
					p.remove();
				}
			}

			// System.out.println("size "+ennemi.size());
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
		for (Object obj : projectile) {
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
		// g.drawTransformedPicture(Gdx.graphics.getWidth() / 2.0f,
		// Gdx.graphics.getHeight() / 2.0f, angle, 1.0f, imgBitmap);
		// g.drawStringCentered(Gdx.graphics.getHeight() * 0.8f, "Welcome to gdx2d !");
		// mapManager.drawMap(g,assets);
		// g.drawFPS();
		// g.drawSchoolLogo();
	}

	@Override
	public void onClick(int x, int y, int button) {
		super.onClick(x, y, button);
		lastClick = new Point(x - map0x, y - map0y);

		// find overview
		if (defenseGui.getVisible()) {
			defenseGui.clicked(x - map0x, y - map0y);
		}

		// play button test
		playButton.clicked(x - map0x, y - map0y, roundManager);

		Defense def = Utils.getDefenseClicked(defense, x - map0x, y - map0y);
		if (def != null) {
			defenseGui.setDefense(def);
			defenseGui.setVisible(true);
		}
		// System.out.println((int)((x-map0x)/(tileSize*64f)));
		// System.out.println((int)((y-map0y)/(tileSize*64f)));
		// System.out.println(x-map0x);
		// System.out.println(y-map0y);
		// System.out.println("yes: "+x +" / "+y);
		// // is walkable
		// System.out.println(Utils.isWalkable(Utils.getTile(new Point(x-map0x,y-map0y),
		// tiledLayer)));
	}

	@Override
	public void onRelease(int x, int y, int button) {

		super.onRelease(x, y, button);

		if (preview.getVisible()) {
			preview.setVisible(false);

			if ((Utils.returnStateForBool(new Point((int) ((x - map0x) / tileSize), (int) ((y - map0y) / tileSize)),
					null, "posable", tiledMap)) == true
					&& Utils.checkDefenseCollision(defense, (int) x - map0x, (int) y - map0y, 30) == false) {
				if (money.getMoneyCount() >= (int) nowDragable.properties.price) {
					// defense.add(new Tourelle(new Point(x-map0x,y-map0y),ennemi,projectile));
					Defense d = Utils.createDefense((String) nowDragable.properties.classDefense, new Point(x - map0x, y - map0y),
							ennemi, projectile);
					if (d != null) {
						defense.add(d);
					}
					// System.out.println((String)nowDragable.defense[3]);
					money.takeOffMoneyCount((int) nowDragable.properties.price);
				}
			}
		}
	}

	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);

		ennemi.add(new Mojojo(tiledMap, 30, 100));
	}

	@Override
	public void onDrag(int x, int y) {
		super.onDrag(x, y);
		for (Dragable obj : dragable) {
			boolean h = ((Dragable) obj).check(lastClick.x, lastClick.y);
			if (h == true) {
				// print preview
				nowDragable = obj;

				if (!preview.getVisible()) {
					preview.setVisible(true);
					preview.setRadius((float) obj.properties.radius);
					preview.setImage(new BitmapImage((String) obj.properties.previewImage), tileSize);
					// System.out.println((obj.defense[3]));
				}
				if ((Utils.returnStateForBool(
						new Point((int) ((x - map0x) / Game.tileSize), (int) ((y - map0y) / Game.tileSize)), null,
						"posable", tiledMap)) == true
						&& Utils.checkDefenseCollision(defense, (int) x - map0x, (int) y - map0y, 30) == false) {
					preview.setPlaceable(true);
				} else {
					preview.setPlaceable(false);
				}

				preview.move(x - map0x, y - map0y);

			}
		}
	}

}
