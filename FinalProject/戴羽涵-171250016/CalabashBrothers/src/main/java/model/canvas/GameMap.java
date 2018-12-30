package model.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import model.creatures.GoodPeople;
import model.helper.Position;

import java.util.List;

/**
 * GameMap负责生成小格子的图形，拥有drawGameMap()函数
 * 该函数将被MyCanvas调用，进行地图格子的绘制
 *
 * 格子的宽度、高度、偏移量、圆角弧度等属性是固定的
 * GameMap的行数、列数在MyCanvas中进行设定
 */
public class GameMap {
	// 小格子的宽度、高度设置为相等的
	final public static double squareWidth=65;  // 设置小格子的宽度
	final public static double squareHeight=65;  // 设置小格子的高度
	public static int rowNum;  // 地图的行数
	public static int columnNum;  // 地图的列数
	final private int columnsOfHome = 4;  // 设置各自"老巢"所占的列数
	final public static double startX =10;  // 地图的左上角的起始position
	final public static double startY=10;
	final public static double gap = 5;  // 设置正方形之间的间隙宽度
	final private static double arcWidth = 15;  // 圆角弧度
	final private static double arcHeight = 15;
	final private Color colorOfGoodPeopleHome = Color.GAINSBORO;  // 葫芦娃老巢的颜色
	final private Color colorOfDemonHome = Color.PINK;  // 妖精老巢的颜色
	final private Color colorOfBattle = Color.LIGHTGREEN;  // 战场的颜色

	/**
	 * 根据默认的小格子大小、地图的行列数量生成GamepMap实例
	 */
	public GameMap(int rowNum,int columnNum) {
		this.rowNum = rowNum;
		this.columnNum = columnNum;
	}

	/**
	 * 绘制地图的格子
	 * 注意点：
	 * 1、strokeRoundRect：x、y的取值并不是最最左上角的position，而是stroke中间宽度的左上角
	 * 2、strokeRoundRect：w、h也不是总的宽度、高度，也是以stroke宽度的中间线来计算的
	 */
	public void draw(GraphicsContext gc) {
		// 加入Rectangle
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < columnNum; j++) {
				if (j < columnsOfHome) {
					gc.setFill(colorOfGoodPeopleHome);
					gc.save();
				} else if (j >= columnNum - columnsOfHome) {
					gc.setFill(colorOfDemonHome);
					gc.save();
				} else {
					gc.setFill(colorOfBattle);
					gc.save();
				}
				gc.fillRoundRect(startX+gap/2.0+j*squareWidth, startY+gap/2.0+i*squareHeight,
						squareWidth-gap, squareHeight-gap,arcWidth,arcHeight);  // 有填充色的正方形
			}
		}
		gc.applyEffect(new DropShadow(gap,Color.DEEPPINK));  // 使用阴影
	}

	/**
	 * 因为调用这个函数的Canvas是PlayerCanvas。所以可以直接进行清除，并且不会造成square的丢失
	 * @param gcOfPlayer
	 * @param positions
	 */
	public static void recoverSquares(GraphicsContext gcOfPlayer, List<Position> positions) {
		for (Position position : positions) {
			gcOfPlayer.clearRect(startX+gap/2.0+position.getIndexOfColumn()*squareWidth,
					startY+gap/2.0+position.getIndexOfRow()*squareHeight,
					squareWidth-gap,squareHeight-gap);
		}
	}

	public static void drawAccessiableSquares(GraphicsContext gc,List<Position> positionsCanMoveTo,Color color) {
		for (Position position : positionsCanMoveTo) {
			drawSquareColor(gc, position.getIndexOfRow(), position.getIndexOfColumn(),color);
		}
	}

	/**
	 * 在player站立的小方格上显示特定颜色
	 *
	 * @param gc
	 * @param indexOfRow
	 * @param indexOfColumn
	 * @param color
	 */
	private static void drawSquareColor(GraphicsContext gc, int indexOfRow, int indexOfColumn, Color color) {
		gc.setFill(color);
		gc.fillRoundRect(startX+gap/2.0+indexOfColumn*squareWidth,startY+gap/2.0+indexOfRow*squareHeight,
				squareWidth-gap,squareHeight-gap,arcWidth,arcHeight);
	}

	/**
	 * 根据x、y的坐标值返回对应的index值
	 * @param x x坐标值
	 * @param y y坐标值
	 * @return 返回Poition
	 */
	public static Position getIndex(double x, double y) {
		int indexOfColumn = (int)Math.floor((x-startX)/squareWidth);
		int indexOfRow = (int) Math.floor((y - startY) / squareHeight);
		return new Position(indexOfRow, indexOfColumn);
	}

	public static int getIndexOfRow(double y) {
		return (int) Math.floor((y - startY) / squareHeight);
	}

	public static int getIndexOfColumn(double x) {
		return (int) Math.floor((x - startX) / squareWidth);
	}

	// getter
	public double getSquareWidth() {
		return squareWidth;
	}

	public double getSquareHeight() {
		return squareHeight;
	}

	public int getNumOfRow() {
		return rowNum;
	}

	public int getNumOfColumn() {
		return columnNum;
	}
}
