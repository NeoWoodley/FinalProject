package model.object;

import javafx.scene.Parent;
import model.canvas.GameMap;

import java.io.Serializable;

/**
 * 这是所有要在Canvas上绘制的元素的抽象父类
 */
public abstract class BaseObject extends Parent implements Serializable {
	private double x, y;
	private int rowIndex, columnIndex;
	private double width, height;
	// 引用GameMap中的常量
	final private double startX = GameMap.startX;
	final private double startY = GameMap.startY;
	final private double squareWidth = GameMap.squareWidth;
	final private double squareHeight = GameMap.squareHeight;
	final private double gap = GameMap.gap;

	public void moveUp(double distance) {
		setY(getY()-distance);
	}

	public void moveDown(double distance) {
		setY(getY()+distance);
	}

	public void moveLeft(double distance) {
		setX(getX()-distance);
	}

	public void moveRight(double distance) {
		setX(getX()+distance);
	}

	// 每次移动一个格子
	public void moveUpOneSquare() {
		setY(getY() - squareHeight);
		setRowIndex(getRowIndex() - 1);
	}

	public void moveDownOneSquare() {
		setY(getY()+squareHeight);
		setRowIndex(getRowIndex() + 1);
	}

	public void moveLeftOneSquare() {
		setX(getX() - squareWidth);
		setColumnIndex(getColumnIndex() - 1);
	}

	public void moveRightOneSquare() {
		setX(getX() + squareWidth);
		setColumnIndex(getColumnIndex() + 1);
	}

	// getter
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

//	public boolean isVisible() {
//		return isVisible;
//	}

	// setter
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
	}

	public void setX(double x) {
		this.x = x;
		columnIndex=GameMap.getIndexOfColumn(x);  // 不能直接调用setIndexOfColumn()方法，因为调用该方法的话，又会把X同步修改掉
	}

	public void setY(double y) {
		this.y = y;
		rowIndex=GameMap.getIndexOfRow(y);
	}

	public void setIndex(int rowIndex, int columnIndex) {
		setRowIndex(rowIndex);
		setColumnIndex(columnIndex);  // 不能直接调用setIndexOfRow()方法，因为调用该方法的话，又会把Y同步修改掉
	}

	/**
	 * 在设定rowIndex的同时还要更改Y坐标的值
	 * @param rowIndex
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
		this.setY(startY + gap / 2 + rowIndex * squareHeight);
	}

	/**
	 * 在设定columnIndex的同时还要更改X坐标的值
	 * @param columnIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
		this.setX(startX+gap/2+columnIndex*squareWidth);
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

//	public void setVisible(boolean visible) {
//		isVisible = visible;
//	}

	/**
	 * 判断该object是否会与指定坐标(double x, double y)产生冲突
	 * @param x 坐标X
	 * @param y 坐标Y
	 * @return 返回冲突与否
	 */
	public boolean isConflictedWith(double x, double y) {
		return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + height;
	}

	public boolean isInSamePosition(BaseObject otherObject) {
		return getRowIndex() == otherObject.getRowIndex() && getColumnIndex() == otherObject.getColumnIndex();
	}

}
