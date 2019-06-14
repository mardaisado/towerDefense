package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.utils.Logger;

import java.awt.Toolkit;

/**
 * Main windows controller for the game. 
 * This class is managing the transition for the Menu, Settings, Game ...
 *
 */
public class MasterScreen extends PortableApplication {

	final static double PERCENTAGEOFSCREEN = 1.5;
	final static boolean FULLSCREEN = true; // Change this boolean for choose FullScreen or not

	static ScreenManager s = new ScreenManager();

	static int mapSelector=0; // Variable used to bridge between this class and MapMenu

	public MasterScreen() {

		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),FULLSCREEN);
	}

	@Override
	public void onInit() {
		setTitle("Best Tower Defense Game you've ever seen");
		Logger.dbg("Game", "Tower Defense Game v1.0.0, | aurher, jermer (c) 2019");
		s.registerScreen(MainMenu.class); 	// 0
		s.registerScreen(MapMenu.class);	// 1
		s.registerScreen(Game.class); 		// 2
		s.registerScreen(EndScreen.class); 	// 3
		s.registerScreen(SettingMenu.class);// 4        

	}
	
	/**
	 * Func for make the transition screen
	 *
	 * @param screen
	 *            the screen to display
	 */
	static public void transition(int screen)
	{
		s.activateScreen(screen);
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		s.render(g);
	}

	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);

		s.getActiveScreen().onKeyUp(keycode);
	}

	@Override
	public void onKeyDown(int keycode) {

		super.onKeyDown(keycode);

		s.getActiveScreen().onKeyDown(keycode);

	}
	
	@Override
	public void onClick(int x, int y, int button) {
		// Delegate the click to the child class
		s.getActiveScreen().onClick(x, y, button);
	}

	@Override
	public void onDrag(int x, int y) {
		s.getActiveScreen().onDrag(x, y);
	}

	@Override
	public void onRelease(int x, int y, int button) {
		s.getActiveScreen().onRelease(x, y, button);
	}

	public static void main(String[] args) {

		new MasterScreen();
	}
}
