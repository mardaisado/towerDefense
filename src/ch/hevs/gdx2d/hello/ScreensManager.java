package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.desktop.PortableApplication;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import java.awt.Toolkit;

/**
 * Show how to add multiple screen and switch between them with different transitions.
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */
public class ScreensManager extends PortableApplication {

    private ScreenManager s = new ScreenManager();
    private int transactionTypeId;
    
    final static double PERCENTAGEOFSCREEN =0.5;
    public ScreensManager() {
		// TODO Auto-generated constructor stub
    	super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),false);
	}

    @Override
    public void onInit() {
    	setTitle("TowerDefense");
        //Logger.log("Press enter/space to show the next screen, 1/2/3 to transition to them");
        s.registerScreen(Menu.class);
        Menu.addScreenManager(s);
        s.registerScreen(Game.class);
        //s.registerScreen(CreditsScreen.class);
    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        s.render(g);
    }

    @Override
    public void onClick(int x, int y, int button) {
        // Delegate the click to the child class
        s.getActiveScreen().onClick(x, y, button);
    }

    @Override
    public void onKeyDown(int keycode) {
        super.onKeyDown(keycode);        
        // Delegate the Key to the child class
        s.getActiveScreen().onKeyDown(keycode);

    }
    

    public static void main(String[] args) {
        new ScreensManager();
    }
}

