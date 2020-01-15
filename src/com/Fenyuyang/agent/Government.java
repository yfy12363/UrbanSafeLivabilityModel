package com.Fenyuyang.agent;

import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class Government {

	public Government(int x, int y, ContinuousSpace<Object> space, Grid<Object> grid) {
		this.x = x;
		this.y = y;
		this.space = space;
		this.grid = grid;
	}

	int x;
	int y;
	ContinuousSpace<Object> space;
	Grid<Object> grid;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
