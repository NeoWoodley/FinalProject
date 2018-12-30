package model.helper;

import model.creatures.Demon;
import model.creatures.GoodPeople;
import model.creatures.NoPerson;
import model.object.BasePlayer;

import java.util.List;

/**
 * 裁判类
 * 用于判断Game的输赢
 * <p>
 * 葫芦娃胜：
 * 1、妖精全部消灭
 * <p>
 * 妖精胜利：
 * 1、葫芦娃、爷爷全部被消灭
 * 2、妖精进入了葫芦娃的"Home"
 */
public class Judger {
	private List<GoodPeople> goodPeople;
	private List<Demon> demons;
	private String winner= NoPerson.class.getName();  // 初始化为NoPerson

	public Judger(List<GoodPeople> goodPeople, List<Demon> demons) {
		this.goodPeople = goodPeople;
		this.demons = demons;
	}

	private boolean isGoodPeopleAllDead() {
		boolean flag = isOnePartAllDead(goodPeople);
		if (flag) {
			winner = Demon.class.getName();
		}
		return flag;
	}

	private boolean isDemonAllDead() {
		boolean flag = isOnePartAllDead(demons);
		if (flag) {
			winner = GoodPeople.class.getName();
		}
		return flag;
	}

	private boolean isOnePartAllDead(List<? extends BasePlayer> basePlayers) {
		for (BasePlayer basePlayer : basePlayers) {
			if (basePlayer.isAlive())
				return false;
		}
		return true;
	}

	public String getWinner() {
		return winner;
	}

	public boolean isGameOver() {
		return isGoodPeopleAllDead() || isDemonAllDead();
	}
}
