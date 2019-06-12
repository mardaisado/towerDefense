package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Pierre-AndrÃ© Mudry on 07.05.2015.
 * HES-SO Valais, 2015
 */
public class CreditsScreen extends RenderingScreen {
	@Override
	public void onInit() {}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.DARK_GRAY);
		g.drawStringCentered(g.getScreenHeight()/2, "Merci d'avoir joué les bros.");
	}

	@Override
	public void dispose() {

		super.dispose();
	}
}
