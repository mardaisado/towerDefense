package ch.hevs.gdx2d.hello;


import java.awt.Point;

import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.interfaces.DrawableObject;

public abstract class Ennemi implements DrawableObject {

	Point pos;
	
	protected int progress;
	
	protected int hp;
	
	private int reward;	
	
	private boolean alive = true;
	
	public Ennemi(int hp, int reward) {
		this.hp = hp;
		this.reward=reward;
	}
	
	/**
	 * Func for return ennemi's reward
	 *
	 * @return reward of the ennmi
	 */
	public int getReward()
	{
		return reward;
	}

	/**
	 * Func for return ennemi's progress
	 *
	 * @return progress on the map of the ennemi
	 */
	public int getProgress()
	{
		return progress;
	}
	
	/**
	 * Func for return ennemis'hp
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
	public void giveDamage(int damage)
	{
		hp= hp-damage;
		
		if(hp <= 0 && alive)
		{
			alive = false;
			Game.money.addMoneyCount(reward);
		}
	}
	
	@Override
	public abstract void draw(GdxGraphics g);

	public abstract boolean update(GdxGraphics g);





}