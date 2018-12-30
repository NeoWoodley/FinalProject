package model.holder;

import model.helper.BattleModel;
import model.creatures.Demon;
import model.creatures.DemonEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DemonHolder implements Holder<Demon> {
	private List<Demon> demons = new CopyOnWriteArrayList<>(new ArrayList<>());
	private BattleModel battleModel;
	/**
	 * 创造出"妖精们"
	 *
	 * @param numOfFollowing 要创建的下喽啰们的数量，初始化设置为6个小喽啰
	 */
	public DemonHolder(int numOfFollowing) {
		demons.add(new Demon(DemonEnum.SNAKE));
		demons.add(new Demon(DemonEnum.SCORPION));
		for (int i = 0; i < numOfFollowing; i++) {
			demons.add(new Demon(DemonEnum.FOLLOWING));
		}
	}

	@Override
	public void changeBattleModel(BattleModel newBattleModel) {
		battleModel = newBattleModel;
	}

	@Override
	public BattleModel getBattleModel() {
		return battleModel;
	}

	@Override
	public List<Demon> getPlayers() {
		return demons;
	}

//	@Override
//	public void sortByRandom() {
//		int randomIndex;
//		Random random = new Random();
//		for (int i = 0; i < demons.length; i++) {
//			randomIndex = random.nextInt(demons.length);  // 范围为介于[0, demons.length)之间的整数
//			swap(i,randomIndex);
//		}
//	}
//
//	@Override
//	public void swap(int x, int y) {
//		Demon temp = demons[x];
//		demons[x] = demons[y];
//		demons[y]=temp;
//	}
}
