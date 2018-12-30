package model.creatures;

import javafx.scene.paint.Color;

/**
 * 该类的成员变量都是固定不变的，无法被修改
 */
public enum GoodPeopleEnum {
	GrandPa("爷爷", 0, "/picture/爷爷1.png", Color.WHITESMOKE),
	BrotherOne("红娃", 1, "/picture/红大娃.png", Color.RED),
	BrotherTwo("橙娃", 2, "/picture/橙二娃.png", Color.ORANGE),
	BrotherThree("黄娃", 3, "/picture/黄三娃.png", Color.YELLOW),
	BrotherFour("绿娃", 4, "/picture/绿四娃.png", Color.GREEN),
	BrotherFive("青娃", 5, "/picture/青五娃.png", Color.DARKBLUE),
	BrotherSix("蓝娃", 6, "/picture/蓝六娃.png", Color.BLUE),
	BrotherSeven("紫娃", 7, "/picture/紫七娃.png", Color.PURPLE);

	final private String name;
	final private int rank;
	final private String imageName;  // 这里不直接加载图片是因为enum是一个static类
	final private Color color;

	GoodPeopleEnum(String name, int rank, String imageName, Color color) {
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
