package model.creatures;

import javafx.scene.canvas.GraphicsContext;
import model.object.BaseObject;
import model.object.BasePlayer;

import java.util.List;

/**
 * 初始化设定为demon共有8个，1个蛇精，1个蝎子精，6个小喽啰
 * <p>
 * 由于枚举类型不能进行继承操作，所以决定将枚举类作为Demon类的内部类
 */
public class Demon extends BasePlayer {
	public Demon(DemonEnum demonEnum) {
		// 图片大小设定
		super(demonEnum.getName(), demonEnum.getRank(), demonEnum.getImageName(), demonEnum.getColor());
	}

	@Override
	public void hit(BasePlayer enermy) {
		super.hit(enermy);
	}

	/**
	 * 先清空单个格子
	 * 重新设定player的X、Y坐标
	 * 再根据新的X、Y坐标值进行重新绘制player、HP值
	 *
	 * @param gcOfPlayer
	 */
	public void redrawMoveLeft(GraphicsContext gcOfPlayer, GraphicsContext gcOfHP,List<BasePlayer> basePlayers) {
		gcOfPlayer.save();
		gcOfHP.save();
		moveLeft();
		if (!canMoveTo(getRowIndex(), getColumnIndex(), basePlayers)) {
			moveRight();  // 退回去
		}
		redrawAll(gcOfPlayer,gcOfHP);
	}

	/**
	 * 这个方法在写的时候遇到了一点小问题
	 * 就是当demon同步移动的时候，表面上一个demon前面站立着一个另一个demon，是不能进行移动的；但实际上是可以继续向前移动的，因为demon是同步移动的
	 * @param indexOfRow
	 * @param indexOfColumn
	 * @param basePlayers
	 * @return
	 */
	private boolean canMoveTo(int indexOfRow, int indexOfColumn, List<BasePlayer> basePlayers) {
		// 只要指定的index上没有goodPeople、scene、除了自己以外的demon站立就行
		// 如果有demon站立也是可以移动的，因为demons是同步移动的：这个刚移走，另一个就可以站上去了
		for (BaseObject baseObject : basePlayers) {
			if (!isAlive()) {
				return false;
			}
			if (indexOfRow == baseObject.getRowIndex() && indexOfColumn == baseObject.getColumnIndex()) {
				// 如果在this想要移动到的index上证好站立了一个player，
				// 如果这个player是他自己，那么就可以继续移动（因为index只是一个粗略的估计，在同一个index上，可以有小幅度x、y的变动
				// 如果这个player是别的什么goodPeople、demon，就不能移动
				return baseObject==this;
			}
		}
		return true;
	}
}
