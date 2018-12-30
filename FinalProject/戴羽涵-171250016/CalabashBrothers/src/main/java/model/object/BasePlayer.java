package model.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.canvas.GameMap;
import model.helper.Position;
import model.holder.BulletHolder;

import java.util.ArrayList;
import java.util.List;

public class BasePlayer extends BaseObject {
	private String name;
	private int rank;  // 排名：1、葫芦娃之间的rank；2、妖精之间的rank
	private Image image;  // 对应的图片
	final private double imageWidth = scaleProportion * (GameMap.squareWidth - GameMap.gap);
	final private double imageHeight = scaleProportion * (GameMap.squareHeight - GameMap.gap);
	final public static double scaleProportion = 0.8;  // 图片的缩小比例
	private Color color;  // 每个player对应唯一一种颜色
	private BulletHolder bulletHolder = new BulletHolder();
	private List<Bullet> bulletList;
	private List<BasePlayer> sameRowEnemyList = new ArrayList<>();  // 与该player在同一行的enemy
	private int HP = 10;  // 生命值
	private int attack = 1;  // 攻击值
	private boolean isAlive = true;  // 初始值为true

	private List<Position> positionsCanMoveTo = new ArrayList<>();

	public BasePlayer(String name, int rank, String imageName, Color color) {
		this.name = name;
		this.rank = rank;
		this.image = new Image(getClass().getResourceAsStream(imageName));
		this.color = color;
		setWidth(imageWidth);
		setHeight(imageHeight);
	}

	public BasePlayer(int indexOfRow, int indexOfColumn) {
		super();
		setIndex(indexOfRow, indexOfColumn);
		setWidth(imageWidth);
		setHeight(imageHeight);
	}

	public BasePlayer() {

	}

	public List<Position> getPositionsCanMoveTo(List<BasePlayer> basePlayers) {
//		System.out.println("当前对象为："+this.getName()+" "+getRowIndex()+" "+getColumnIndex());
		Position UP = new Position(getRowIndex() - 1, getColumnIndex());
		Position DOWN = new Position(getRowIndex() + 1, getColumnIndex());
		Position LEFT = new Position(getRowIndex(), getColumnIndex() - 1);
		Position RIGHT = new Position(getRowIndex(), getColumnIndex() + 1);
//		System.out.println("上 "+UP);
//		System.out.println("下 "+DOWN);
//		System.out.println("左 "+LEFT);
//		System.out.println("右 "+RIGHT);

		// 首先要清空list，因为list的随时会改变，我们要计算最新的position值
		positionsCanMoveTo.clear();
		// 判断向上移动行不行
		if (canMoveTo(UP, basePlayers) && UP.isLegal()) {
			positionsCanMoveTo.add(UP);
		}
		// 判断向下移动行不行
		if (canMoveTo(DOWN, basePlayers) && DOWN.isLegal()) {
			positionsCanMoveTo.add(DOWN);
		}
		// 判断向左移动行不行
		if (canMoveTo(LEFT, basePlayers) && LEFT.isLegal()) {
			positionsCanMoveTo.add(LEFT);
		}
		// 判断向右移动行不行
		if (canMoveTo(RIGHT, basePlayers) && RIGHT.isLegal()) {
			positionsCanMoveTo.add(RIGHT);
		}
		return positionsCanMoveTo;
	}

	/**
	 * 判断当前的player能否移动到指定的index上
	 *
	 * @param position
	 * @param basePlayers
	 * @return
	 */
	private boolean canMoveTo(Position position, List<BasePlayer> basePlayers) {
		for (BasePlayer basePlayer : basePlayers) {
			// 注意：在这里要把this自己给排除在外，否则永远返回false
			if (!isAlive) {
				return false;  // 如果死了就不能移动
			}
			if (basePlayer != this) {
				if (basePlayer.getRowIndex() == position.getIndexOfRow() && basePlayer.getColumnIndex() == position.getIndexOfColumn()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 绘制player、HP
	 *
	 * @param gcOfPlayer
	 * @param gcOfHP
	 */
	public void drawAll(GraphicsContext gcOfPlayer, GraphicsContext gcOfHP) {
		gcOfPlayer.save();
		gcOfPlayer.drawImage(image, getX(), getY(), imageWidth, imageHeight);
		drawHP(gcOfHP);
	}

	/**
	 * 绘制player到指定的index上
	 * 步骤：
	 * 1、清空之前player所在的位置
	 * 2、将player的index设置为指定的index
	 * 3、根据player的新的index重新绘制player的位置
	 *
	 * @param gcOfPlayer
	 * @param indexOfRow
	 * @param indexOfColumn
	 */
	public void redrawAll(GraphicsContext gcOfPlayer, GraphicsContext gcOfHP, int indexOfRow, int indexOfColumn) {
		gcOfPlayer.save();
		gcOfPlayer.clearRect(getX(), getY(), GameMap.squareWidth, GameMap.squareHeight);
		clearHP(gcOfHP);
		setIndex(indexOfRow, indexOfColumn);
		drawAll(gcOfPlayer, gcOfHP);
		System.out.println(getName() + isAlive);
		if (!isAlive) {
			gcOfPlayer.setStroke(getColor());
			gcOfPlayer.setLineWidth(3);
			gcOfPlayer.strokeLine(getX() + 4, getY() + 4, getX() + getWidth() - 4, getY() + getHeight() - 4);
			gcOfPlayer.strokeLine(getX() + getWidth() - 4, getY() + 4, getX() + 4, getY() + getHeight() - 4);
		}
	}

	/**
	 * 根据已经被修改的player的index来重绘player
	 *
	 * @param gcOfPlayer
	 * @param gcOfHP
	 */
	public void redrawAll(GraphicsContext gcOfPlayer, GraphicsContext gcOfHP) {
		gcOfPlayer.save();
		gcOfPlayer.clearRect(getX(), getY(), GameMap.squareWidth, GameMap.squareHeight);
		clearHP(gcOfHP);
		drawAll(gcOfPlayer, gcOfHP);
		System.out.println(getName() + isAlive);
		if (!isAlive) {
			gcOfPlayer.setStroke(getColor());
			gcOfPlayer.setLineWidth(3);
			gcOfPlayer.strokeLine(getX() + 4, getY() + 4, getX() + getWidth() - 4, getY() + getHeight() - 4);
			gcOfPlayer.strokeLine(getX() + getWidth() - 4, getY() + 4, getX() + 4, getY() + getHeight() - 4);
		}
	}

	private void drawHP(GraphicsContext gcOfHP) {
		double width = (HP / 10.0) * GameMap.squareWidth * scaleProportion;  // 计算HP条的长度

		gcOfHP.save();
		gcOfHP.setFill(getColor());
		gcOfHP.fillRect(getX(), getY() + GameMap.squareHeight * (scaleProportion - 0.025), width, 4);
	}

	/**
	 * 重绘HP
	 * 清空原来的HP，清除单位的范围是一小块square
	 *
	 * @param gcOfHP
	 */
	public void redrawHP(GraphicsContext gcOfHP) {
		gcOfHP.save();
		clearHP(gcOfHP);
		drawHP(gcOfHP);
		if (!isAlive) {
			gcOfHP.setStroke(getColor());
			gcOfHP.setLineWidth(3);
			gcOfHP.strokeLine(getX() + 4, getY() + 4, getX() + getWidth() - 4, getY() + getHeight() - 4);
			gcOfHP.strokeLine(getX() + getWidth() - 4, getY() + 4, getX() + 4, getY() + getHeight() - 4);
		}
	}

	private void clearHP(GraphicsContext gcOfHP) {
		gcOfHP.save();
		gcOfHP.clearRect(getX(), getY(), GameMap.squareWidth, GameMap.squareHeight);
	}

	public void redrawBullets(GraphicsContext gcOfBullet, GraphicsContext gcOfHP, List<BasePlayer> basePlayerList) {
		bulletList = bulletHolder.getBulletList();  // bulletList需要"实时"获取
		for (Bullet bullet : bulletList) {
			if (bullet.getPosition().isLegal()) {
				bullet.redrawBullet(gcOfBullet);
				if (bullet.tryToHit(gcOfBullet, gcOfHP, basePlayerList)) {  // 如果尝试攻击成功
					// 清除掉"报废"的子弹
					bulletHolder.removeBullet(bullet);
				}
			} else {
				bullet.removeBullet(gcOfBullet);
				bulletHolder.removeBullet(bullet);  // 如果子弹出界了，就remove这个bullet
			}
		}
	}

	public void moveLeft() {
		setX(getX() - 5);  // 每次移动5像素
	}

	public void moveRight() {
		setX(getX() + 5);
	}

	/**
	 * 用于rank的比较
	 *
	 * @param basePlayer
	 * @return
	 */
	public boolean less(BasePlayer basePlayer) {
		return this.rank < basePlayer.rank;
	}

	/**
	 * 不论面前是否有敌人，都会进行攻击尝试
	 * 调用该函数的关键是
	 * 开始尝试攻击，但不一定能攻击到对方
	 * 会创建一个新的Bullet实例
	 */
	public void tryToAttack() {
		// 在该player的子弹list中添加一个子弹
		bulletHolder.addBullet(new Bullet(this));
	}

	/**
	 * 成功命中，被攻击的player的HP--，同时
	 *
	 * @param enermy 葫芦娃和妖精们互为敌人
	 */
	public void hit(BasePlayer enermy) {
		enermy.decreaseHP(this.attack);
	}

	/**
	 * 该player被攻击，HP减去harm值
	 *
	 * @param harm
	 */
	public void hitted(int harm) {
		decreaseHP(harm);
	}

	private void decreaseHP(int harm) {
		if (HP > 0) {
			HP -= harm;
		}
		if (HP <= 0) {
			isAlive = false;
		}
	}

	@Override
	public String toString() {
		return this.getName() + " Row: " + getRowIndex() + " Column: " + getColumnIndex() + " X: " + getX() + " Y: " + getY();
	}

	// getter
	public String getName() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public Color getColor() {
		return color;
	}

	public int getHP() {
		return HP;
	}

	public int getAttack() {
		return attack;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public Position getPosisiton() {
		return new Position(getRowIndex(), getColumnIndex());
	}

	// setter
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 重写父类方法，因为存在图片放缩后position的调整问题
	 *
	 * @param rowIndex    行号
	 * @param columnIndex 列号
	 */
	@Override
	public void setIndex(int rowIndex, int columnIndex) {
		super.setIndex(rowIndex, columnIndex);
		this.setX(this.getX() + (1 - BasePlayer.scaleProportion) / 2 * GameMap.squareWidth);
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}