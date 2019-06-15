package ch.hevs.gdx2d.hello;

import java.awt.Point;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

/**
 * preview of the defense with her action radius
 */
public class Preview implements DrawableObject,UpdateObject {
	private Point pos;
	
	private boolean visible = false;
	
	private BitmapImage image = null;
	private BitmapImage ringGrey;
	private BitmapImage ringRed;
	
	private float scale;
	private float circleScale;
	
	private boolean placeable = true;
	
	public Preview() {
		pos = new Point(0,0);
		ringGrey = new BitmapImage("data/images/greyCircle.png");
		ringRed = new BitmapImage("data/images/redCircle.png");
	}
	
	/**
	 * Func that move the preview
	 *
	 * @param x, y : where to move the preview
	 */
	public void move(int x, int y) {
		pos.x = x;
		pos.y = y;
	}
	
	/**
	 * Func that return if the preview is visible
	 *
	 * @return True if the preview is visible / false otherwise
	 */
	public boolean getVisible() {
		return visible;
	}
	
	/**
	 * Func that set the visble state
	 *
	 * @param b : visible state
	 */
	public void setVisible(boolean b) {
		visible = b;
	}
	
	/**
	 * Func that set if the defense is placeable
	 *
	 * @param b : placeable state
	 */
	public void setPlaceable(boolean b) {
		placeable = b;
	}
	
	/**
	 * Func that set the image to preview
	 *
	 * @param image : the image to set
	 * @param scale : the scale for the draw func
	 */
	public void setImage(BitmapImage img,float scale) {
		image = img;
		this.scale = scale;
	}
	
	/**
	 * Func that set the radius of the preview circle
	 *
	 * @param radius : radius of the preview defense
	 */
	public void setRadius(float radius) {
		circleScale = 2*radius*Game.tileSize/ringGrey.getImage().getHeight();
	}

	@Override
	public void update(GdxGraphics g) {
		
	}

	@Override
	public void draw(GdxGraphics g) {
		if(visible) {
			if (placeable) {
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringGrey);
			}
			else {
				g.drawAlphaPicture(pos.x, pos.y, 0, circleScale, 0.4f, ringRed);
			}			
			g.drawTransformedPicture(pos.x, pos.y, 0, scale/2, image);
		}
	}
}
