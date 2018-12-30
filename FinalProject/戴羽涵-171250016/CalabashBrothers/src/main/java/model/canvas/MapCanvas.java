package model.canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import model.holder.SceneHolder;
import model.object.BaseScene;

import java.util.List;

public class MapCanvas extends Canvas {
	// GraphicsContext
	private GraphicsContext gcOfGameMap;
	// GameMap
	private GameMap gameMap;
	final public static int rowNum = 11;
	final public static int columnNum = 20;
	// 小景物Holder
	private List<BaseScene> trees;
	// TODO: 2018-12-24 石头、草丛的list不小心被我删掉了，可能以后会用得到啊！！！

	/**
	 * 构造函数：
	 * 1、创建一个指定宽度、高度的Canvas实例
	 * 2、加载mapImage为指定图片
	 * 3、获取GraphicsContent2D
	 * 4、创建GameMap实例对象
	 * 5、start线程
	 *
	 * @param width  Canvas实例的宽度
	 * @param height Canvas实例的高度
	 */
	public MapCanvas(double width, double height) {
		super(width, height);  // 创建一个指定宽度、高度的Canvas实例
		gcOfGameMap = this.getGraphicsContext2D();
		// 初始化GameMap
		gameMap = new GameMap(rowNum, columnNum);
	}

	/**
	 * 主绘制函数
	 */
	public void draw() {
		gameMap.draw(gcOfGameMap);
//		initScenes();

//		drawScene();
	}

	// init
	private void initGameMap() {

	}

	public void drawScene() {
		for (BaseScene baseScene : trees) {
			baseScene.draw(gcOfGameMap);
		}
	}

	private void initScenes() {
		SceneHolder sceneHolder = new SceneHolder();
		trees = sceneHolder.getTrees();
//		stones = sceneHolder.getStones();
//		grasses = sceneHolder.getGrasses();
	}

	public List<BaseScene> getTrees() {
		return trees;
	}
}

