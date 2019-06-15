package ch.hevs.gdx2d.hello;

import ch.hevs.gdx2d.lib.GdxGraphics;

/**
 * Interface to define an update with a return boolean to delete the object
 */
public interface DeleteObject {
	public boolean update(GdxGraphics g);
}
