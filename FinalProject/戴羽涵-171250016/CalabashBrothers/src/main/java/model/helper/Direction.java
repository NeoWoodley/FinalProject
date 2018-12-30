package model.helper;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;

	@Override
	public String toString() {
		switch (this) {
			case RIGHT:
				return "向右";
			case LEFT:
				return "向左";
			case UP:
				return "向上";
			case DOWN:
				return "向下";
			default:
				return null;
		}
	}
}
