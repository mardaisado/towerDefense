package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.utils.Logger;

import java.awt.Toolkit;

import com.badlogic.gdx.Input;

/**
 * Show how to add multiple screen and switch between them with different transitions.
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */


public class DemoScreen extends PortableApplication {

	

	final static double PERCENTAGEOFSCREEN =1.5;
	final static boolean FULLSCREEN = false;
	
    private ScreenManager s = new ScreenManager();
    private int transactionTypeId;

	public DemoScreen() {
		super((int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().width),(int)(PERCENTAGEOFSCREEN*Toolkit.getDefaultToolkit().getScreenSize().height),FULLSCREEN);
	
	}
    
    @Override
    public void onInit() {
    	
    	
        setTitle("Multiple screens and transitions");
        Logger.log("Press enter/space to show the next screen, 1/2/3 to transition to them");
        s.registerScreen(Game.class);
        s.registerScreen(CreditsScreen.class);

    }

    @Override
    public void onGraphicRender(GdxGraphics g) {
        s.render(g);
    }

    @Override
    public void onKeyUp(int keycode) {
        super.onKeyUp(keycode);

        s.getActiveScreen().onKeyUp(keycode);
        
        if (keycode == Input.Keys.NUM_1)
            s.transitionTo(1, ScreenManager.TransactionType.SLICE); // s.activateScreen(0);

        if (keycode == Input.Keys.NUM_2)
            s.transitionTo(0, ScreenManager.TransactionType.SLIDE); // s.activateScreen(1);

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
    
    
    public void endGame()
    {
    	s.transitionToNext(ScreenManager.TransactionType.values()[transactionTypeId]);
    }            


    public static void main(String[] args) {
        new DemoScreen();
    }
}
