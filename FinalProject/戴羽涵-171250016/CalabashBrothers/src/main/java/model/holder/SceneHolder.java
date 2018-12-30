package model.holder;

import model.canvas.GameMap;
import model.object.BaseScene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SceneHolder{
//	private BattleModel battleModel;
	private List<BaseScene> trees = new CopyOnWriteArrayList<>(new ArrayList<>(6));
	private double squareWidth = GameMap.squareWidth;
	private double squareHeight = GameMap.squareHeight;
//	private List<BaseScene> stones = new ArrayList<>(2);
//	private List<BaseScene> grasses = new ArrayList<>(5);

	public SceneHolder() {
		int midRow=GameMap.rowNum>>1;
		int midColumn= GameMap.columnNum>>1;

		trees.add(new BaseScene("大树0", "/picture/大树1.png",(midColumn-3.25)*squareWidth,(midRow-2.85)*squareHeight));
		trees.add(new BaseScene("大树1", "/picture/大树6.png",correctPositionX(midColumn-2),correctPositionY(midRow)));
		trees.add(new BaseScene("大树2", "/picture/大树6.png",correctPositionX(midColumn+2),correctPositionY(midRow)));
		trees.add(new BaseScene("大树3", "/picture/大树6.png",correctPositionX(midColumn-1),correctPositionY(midRow+1)));
		trees.add(new BaseScene("大树4", "/picture/大树6.png",correctPositionX(midColumn+1),correctPositionY(midRow+1)));
		trees.add(new BaseScene("大树5", "/picture/大树6.png",correctPositionX(midColumn),correctPositionY(midRow+2)));
//		stones.add(new BaseScene("石头1", "/picture/石头1.png"));
//		stones.add(new BaseScene("石头2", "/picture/石头2.png"));
//		grasses.add(new BaseScene("草丛1", "/picture/草丛1.png"));
//		grasses.add(new BaseScene("草丛2", "/picture/草丛2.png"));
//		grasses.add(new BaseScene("草丛3", "/picture/草丛3.png"));
//		grasses.add(new BaseScene("草丛4", "/picture/草丛4.png"));
//		grasses.add(new BaseScene("草丛5", "/picture/草丛5.png"));
	}

//	public void changeBattleModel(BattleModel newBattleModel) {
//		battleModel = newBattleModel;
//	}

//	public BattleModel getBattleModel() {
//		return battleModel;
//	}

	private double correctPositionX(int columnIndex) {
		return (columnIndex - 1.55) * squareWidth;
	}

	private double correctPositionY(int rowIndex) {
		return (rowIndex - 0.9) * squareHeight;
	}

	public List<BaseScene> getTrees() {
		return trees;
	}

//	public List<BaseScene> getStones() {
//		return stones;
//	}

//	public List<BaseScene> getGrasses() {
//		return grasses;
//	}
}
