package de.omegasystems.bot;

import de.omegasystems.Board;

public interface Bot {
	public Board getSetup();
	public void move();
	public int getMove();
}
