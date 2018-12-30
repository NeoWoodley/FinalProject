package model.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.helper.Position;
import model.helper.Direction;
import model.creatures.Demon;
import model.creatures.GoodPeople;
import model.creatures.NoPerson;

import java.util.List;

/**
 * 子弹类
 * 妖精们发出的子弹只能向左边移动
 * 葫芦娃们发出的子弹只能向右边移动
 */
public class Bullet extends BaseObject {
	private BasePlayer owner;  // 子弹的拥有者，保证不会"同类"相杀
	private Direction direction;  // 子弹的前进方向
	private BasePlayer hittedPlayer =new NoPerson();  // 记录该子弹击中的enemy，初始化为NoPerson
	final private double offsetX=25;
	final private double offsetY = 25;
	final private double radius = 10;  // buttle的半径大小
	final private double biggerRadius = 15;
	private Color color;  // 子弹的颜色与其owner的颜色保持一致，方便区分
	final private double distance=2;  // 每次重绘刷新时移动的距离

	/**
	 * @param owner 根据owner的具体类型，设置buttle的具体起始坐标、移动方向
	 */
	public Bullet(BasePlayer owner) {
		// TODO: 2018-12-20 子弹的位置还需要进一步调整
		setX(owner.getX()+offsetX);
		setY(owner.getY()+offsetY);
		this.owner = owner;
		this.color = owner.getColor();
		if (owner instanceof Demon) {
			this.direction = Direction.LEFT;
		} else if (owner instanceof GoodPeople) {
			this.direction = Direction.RIGHT;
		}
	}

	private void drawBullet(GraphicsContext gc) {
		gc.save();
		gc.setFill(color);
		gc.fillOval(getX(), getY(), radius, radius);
	}

	/**
	 * 绘画出子弹命中之后的情景
	 * @param gc
	 */
	private void drawBiggerBullet(GraphicsContext gc) {
		gc.save();
		gc.setFill(color);
		gc.fillOval(getX(),getY(),biggerRadius,biggerRadius);
	}

	public void redrawBullet(GraphicsContext gc) {
		gc.save();
		gc.clearRect(getX(), getY(), radius, radius);
		move();
		drawBullet(gc);
	}

	/**
	 * 如果子弹出界了，或者子弹命中了敌人（停留几秒之后），就将子弹的图像从canvas上清除
	 * @param gc
	 */
	public void removeBullet(GraphicsContext gc) {
		gc.save();
		gc.clearRect(getX(), getY(), radius, radius);
	}

	private void removeBiggerBullet(GraphicsContext gc) {
		gc.save();
		gc.clearRect(getX(),getY(),biggerRadius,biggerRadius);
	}

	/**
	 * 按指定的方向移动子弹
	 */
	private void move() {
		if (direction == Direction.LEFT) {
			setX(getX() - distance);
		} else if(direction==Direction.RIGHT){
			setX(getX() + distance);
		}
	}

	/**
	 * 成功击中的动画
	 */
	public void hit(GraphicsContext gc) {
		drawBiggerBullet(gc);
	}

	// TODO: 2018-12-25 这里是否需要synchronized呢？？？
	private boolean isHit(List<BasePlayer> basePlayerList) {
		synchronized (basePlayerList) {
			for (BasePlayer basePlayer : basePlayerList) {
				if (basePlayer.getClass() != owner.getClass()) {  // 如果子弹的拥有者与潜在的被攻击者是敌人的话
//					System.out.print("潜在被攻击者："+basePlayer.getClass());
//					System.out.println("    子弹拥有者："+owner.getClass());
					// 如果子弹能命中敌人
					if (basePlayer.isConflictedWith(getX(),getY())) {
						if (basePlayer.isAlive()) {
							hittedPlayer = basePlayer;  // 设置被攻击到的对象
							return true;
						}
					}
				}
			}
			return false;
		}
	}

	/**
	 * 子弹尝试攻击敌人
	 * 如果成功攻击，就返回true
	 * 如果失败，就返回false
	 * @param gcOfBullet
	 * @param gcOfHP
	 * @param basePlayers 潜在的被攻击的players
	 * @return
	 */
	public boolean tryToHit(GraphicsContext gcOfBullet,GraphicsContext gcOfHP,List<BasePlayer> basePlayers) {
		// 如果子弹命中的话，就显示子弹命中效果
		if (isHit(basePlayers)) {
			System.out.println("Hit Successfully!!!!");
			drawBiggerBullet(gcOfBullet);
			System.out.println("Before hit, hitted's HP: "+hittedPlayer.getHP()+" hitter's attack: "+owner.getAttack());
			hittedPlayer.hitted(owner.getAttack());
			System.out.println("hitted: " + hittedPlayer.getName() + " now HP: " + hittedPlayer.getHP());
			removeBiggerBullet(gcOfBullet);
			hittedPlayer.redrawHP(gcOfHP);
			return true;
		}
		return false;
	}

	public Position getPosition() {
		return new Position(getRowIndex(), getColumnIndex());
	}

	@Override
	public String toString() {
		return "子弹拥有者：" + this.owner.getName() + "，子弹前进方向：" + direction + " 子弹当前行号：" + this.getRowIndex() + " " +
				"子弹当前列号：" + this.getColumnIndex()+" 子弹当前X："+this.getX()+" 子弹当前Y："+this.getY();
	}
}
