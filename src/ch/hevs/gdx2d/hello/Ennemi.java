package ch.hevs.gdx2d.hello;


import java.awt.Point;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Ennemi implements DrawableObject,UpdateObject {

	Point pos;
	
	int progress;
	
	int hp;
	
	int reward;	
	
	public Ennemi(int hp, int reward) {
		this.hp = hp;
		this.reward=reward;
	}
	
	public int getReward()
	{
		return reward;
	}

	public int getProgress()
	{
		return progress;
	}
	
	/**
	 * Func for give damage at the ennemi
	 *
	 * @return HP of the ennemi
	 */
	public int getHP() {
		return hp;
	}
	
	/**
	 * Func for give damage at the ennemi
	 *
	 * @param damage
	 *            The power of the damage
	 * @return True if the ennemi is alive, , false otherwise
	 */
	public boolean giveDamage(int damage)
	{
		hp= hp-damage;
		
		if(hp-damage <= 0)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public abstract void draw(GdxGraphics g);

	@Override
	public abstract void update(GdxGraphics g);
}