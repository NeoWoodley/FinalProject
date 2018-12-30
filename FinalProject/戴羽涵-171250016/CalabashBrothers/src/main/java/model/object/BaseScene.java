package model.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.canvas.GameMap;

/**
 * 基础景物类：大树、石头、草丛，这三个除了对应的图片不同，没什么具体的区别
 * 一个BaseScene，占据一个地图格子
 * 注意点：
 * 1、这些景物应当置于涂层的最上层
 * 2、它们不会被破坏掉
 * 3、它们是固定不动的
 * 4、默认生成：4棵大树、2块石头、3个草丛，随机选择具体样式
 */
public class BaseScene extends BaseObject {
	private String name;
	private Image image;

	public BaseScene(String name, String imageString, double x, double y) {
		this.name = name;
		this.image = new Image(getClass().getResourceAsStream(imageString));
		this.setPosition(x, y);
	}

	public void draw(GraphicsContext gc) {
		gc.save();
		if (this.name.equals("大树0")) {
			gc.drawImage(image, getX(), getY(), 6 * GameMap.squareWidth, 4 * GameMap.squareWidth);
		} else {
			gc.drawImage(image, getX(), getY(), 2 * GameMap.squareWidth, 2 * GameMap.squareHeight);
		}
	}
}
