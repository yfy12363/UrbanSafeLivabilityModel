package com.Fenyuyang.tool;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;

public class BackgroundPicture {

	// ========================== Constructors ===========================
	public BackgroundPicture(int x, int y, Context<Object> context, ContinuousSpace<Object> space, Grid<Object> grid) {
		this.x = x;
		this.y = y;
		this.context = context;
		this.space = space;
		this.grid = grid;
	}

	int x;
	int y;
	Context<Object> context;
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
