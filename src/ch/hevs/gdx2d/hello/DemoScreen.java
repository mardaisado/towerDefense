package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.utils.Logger;

import java.awt.Toolkit;

/**
 * Show how to add multiple screen and switch between them with different transitions.
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */


public class DemoScreen extends PortableApplication {

	final static double PERCENTAGEOFSCREEN = 1.5;
	final static boolean FULLSCREEN = true;

    static ScreenManager s = new ScreenManager();
    
    int test=0;

	public DemoScreen() {
		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),FULLSCREEN);

	}

    @Override
    public void onInit() {


    	setTitle("Best Tower Defense Game you've ever seen");
    	Logger.dbg("Game", "Tower Defense Game v1.0.0, | aurher, jermer (c) 2019");
    	
    	s.registerScreen(DemoGUI.class); // 0
    	s.registerScreen(Game.class); // 1
        s.registerScreen(CreditsScreen.class); // 2
        s.registerScreen(SettingGUI.class); // 3
        
        
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
    	// TODO Auto-generated method stub
    	s.getActiveScreen().onDrag(x, y);
    }

    @Override
    public void onRelease(int x, int y, int button) {
    	// TODO Auto-generated method stub
    	s.getActiveScreen().onRelease(x, y, button);
    }

    static public void transition(int screen)
    {
    	s.transitionTo(screen, ScreenManager.TransactionType.SMOOTH);
    }


    public static void main(String[] args) {
        new DemoScreen();
    }
}