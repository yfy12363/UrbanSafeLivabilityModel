package com.Fenyuyang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Fenyuyang.agent.Cell;
import com.Fenyuyang.agent.Family;
import com.Fenyuyang.main.UrbanSafeLivabilityModel;
import com.Fenyuyang.tool.Point;

import repast.simphony.context.Context;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class ActionController {
	public static void migrate(Family family, Cell start, ArrayList<Cell> optimalTeam) {
		if (optimalTeam.size() > 0 && family.judgeMigrationCondition()) {
			Random random = new Random();
			int id = family.getId();
			Cell end = optimalTeam.get(random.nextInt(optimalTeam.size()));
			end.getGrid().moveTo(family, end.getX(), end.getY());
			end.getFamilyTeam().add(family);
			ArrayList<Family> startTeam = start.getFamilyTeam();
			for (int i = 0; i < startTeam.size(); i++) {
				if (id == startTeam.get(i).getId()) {
					start.getFamilyTeam().remove(i);
					family.printInformation();
					// 记录搬迁信息
					start.setMoveOutFamilyNumber(start.getMoveOutFamilyNumber() + 1);
					end.setMoveInFamilyNumber(end.getMoveInFamilyNumber() + 1);
				}
			}
		}
	}

	public static ArrayList<Cell> getNghCell(Class<Cell> clazz, Grid<Object> grid, Family family, int minDistance,
			int maxDistance) {

		ArrayList<Cell> cellTeam = new ArrayList<Cell>();

		GridPoint gridPoint = grid.getLocation(family);
		GridCellNgh<Cell> nghCreator = new GridCellNgh<>(grid, gridPoint, clazz, maxDistance, maxDistance);
		List<GridCell<Cell>> gridCells = nghCreator.getNeighborhood(true);

		for (GridCell<Cell> gridCell : gridCells) {
			GridPoint gridPointTemp = gridCell.getPoint();
			int gridPointTemp_x = gridPointTemp.getX();
			int gridPointTemp_y = gridPointTemp.getY();
			if ((Math.pow((family.getX() - gridPointTemp_x), 2)
					+ Math.pow((family.getY() - gridPointTemp_y), 2)) <= Math.pow(maxDistance, 2)
					&& (Math.pow((family.getX() - gridPointTemp_x), 2)
							+ Math.pow((family.getY() - gridPointTemp_y), 2)) >= Math.pow(minDistance, 2)) {
				for (int i = 0; i < gridCell.size(); i++) {
					cellTeam.add((Cell) ((ArrayList<Cell>) gridCell.items()).get(i));
				}
			}
		}
		return cellTeam;
	}

	public static void moveOutFuTian(Family family, Cell cell, Context<Object> context) {

		for (int i = 0; i < family.getInhabitantTeam().size(); i++) {
			context.remove(family.getInhabitantTeam().get(i));
		}
		cell.getFamilyTeam().remove(family);
		context.remove(family);
		cell.setMoveOutFamilyNumber(cell.getMoveOutFamilyNumber() + 1);
		cell.setMoveOutFamilyNumber_now(cell.getMoveOutFamilyNumber_now() + 1);
		DataStorage.getInstance()
				.setMoveOutFamilyNumber_now(DataStorage.getInstance().getMoveOutFamilyNumber_now() + 1);
		DataCollection.getInstance().setMoveOutFamilyNumber_now(DataStorage.getInstance().getMoveOutFamilyNumber_now());
		DataStorage.getInstance().setMoveOutPopulation(
				DataStorage.getInstance().getMoveOutPopulation() + family.getInhabitantTeam().size());
		DataCollection.getInstance().setMoveOutPopulation(DataStorage.getInstance().getMoveOutPopulation());

	}

	public static void moveInFuTian(Family family, Point point, Context<Object> context, Grid<Object> grid) {
		for (int i = 0; i < UrbanSafeLivabilityModel.cellTeam.size(); i++) {
			if (point.getX() == UrbanSafeLivabilityModel.cellTeam.get(i).getX() && point.getY() == UrbanSafeLivabilityModel.cellTeam.get(i).getY()) {
				for (int j = 0; j < family.getInhabitantTeam().size(); j++) {
					context.add(family.getInhabitantTeam().get(j));
					grid.moveTo(family.getInhabitantTeam().get(j), UrbanSafeLivabilityModel.cellTeam.get(i).getX(),
							UrbanSafeLivabilityModel.cellTeam.get(i).getY());
				}
				UrbanSafeLivabilityModel.cellTeam.get(i).getFamilyTeam().add(family);
				UrbanSafeLivabilityModel.cellTeam.get(i).setMoveInFamilyNumber(UrbanSafeLivabilityModel.cellTeam.get(i).getMoveInFamilyNumber() + 1);
				UrbanSafeLivabilityModel.cellTeam.get(i).setMoveInFamilyNumber_now(UrbanSafeLivabilityModel.cellTeam.get(i).getMoveInFamilyNumber_now() + 1);
				context.add(family);
				grid.moveTo(family, point.getX(), point.getY());
				DataStorage.getInstance()
						.setMoveInFamilyNumber_now(DataStorage.getInstance().getMoveInFamilyNumber_now() + 1);
				DataCollection.getInstance()
						.setMoveInFamilyNumber_now(DataStorage.getInstance().getMoveInFamilyNumber_now());
				DataStorage.getInstance().setMoveInPopulation(
						DataStorage.getInstance().getMoveInPopulation() + family.getInhabitantTeam().size());
				DataCollection.getInstance().setMoveInPopulation(DataStorage.getInstance().getMoveInPopulation());
			}
		}
	}
}
