package model.helper;

import model.canvas.GameMap;

import java.util.List;

/**
 * 这是一个坐标类
 */
public class Position {
	private int indexOfRow;
	private int indexOfColumn;

	public Position(int indexOfRow, int indexOfColumn) {
		this.indexOfRow = indexOfRow;
		this.indexOfColumn = indexOfColumn;
	}

	public int getIndexOfRow() {
		return indexOfRow;
	}

	public int getIndexOfColumn() {
		return indexOfColumn;
	}

	@Override
	public String toString() {
		return "Row: " + indexOfRow + "  Column: " + indexOfColumn;
	}

	public boolean isLegal() {
		return indexOfRow >= 0 && indexOfRow < GameMap.rowNum && indexOfColumn >= 0 && indexOfColumn < GameMap.columnNum;
	}

	/**
	 * 判断当前的position对象是否位于一个PositionList中
	 *
	 * @return
	 */
	public boolean isInPosisiontList(List<Position> positionList) {
		synchronized (positionList) {
			for (Position position : positionList) {
				if (this.equals(position)) {
					return true;
				}
			}
			return false;
		}
	}

	private boolean equals(Position position) {
		return getIndexOfRow() == position.getIndexOfRow() && getIndexOfColumn() == position.getIndexOfColumn();
	}
}
