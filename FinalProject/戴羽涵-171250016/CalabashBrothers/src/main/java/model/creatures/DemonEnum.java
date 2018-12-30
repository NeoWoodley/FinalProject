package model.creatures;

import javafx.scene.paint.Color;

/**
 * 该类的成员变量都是固定不变的，无法被修改
 */
public enum DemonEnum {
	SNAKE("蛇精", 1, "/picture/蛇精1.png",Color.DEEPPINK),  // 蛇精
	SCORPION("蝎子精", 2, "/picture/蝎子精1.png",Color.BROWN),  // 蝎子精
	FOLLOWING("小喽啰", 3, "/picture/小喽啰1.png",Color.GRAY);  // 小喽啰

	final private String name;
	final private int rank;
	final private String imageName;
	final private Color color;

	DemonEnum(String name, int rank, String imageName, Color color) {
		this.name = name;
		this.rank = rank;
		this.imageName = imageName;
		this.color = color;
	}

	// getter
	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public String getImageName() {
		return imageName;
	}

	public Color getColor() {
		return color;
	}
}
