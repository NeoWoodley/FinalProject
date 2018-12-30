package model.holder;

import model.helper.BattleModel;
import model.creatures.GoodPeople;
import model.creatures.GoodPeopleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GoodPeopleHolder implements Holder<GoodPeople> {
	private List<GoodPeople> goodPeople = new CopyOnWriteArrayList<>(new ArrayList<>());
	private BattleModel battleModel;

	public GoodPeopleHolder() {
		goodPeople.add(new GoodPeople(GoodPeopleEnum.GrandPa));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherOne));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherTwo));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherThree));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherFour));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherFive));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherSix));
		goodPeople.add(new GoodPeople(GoodPeopleEnum.BrotherSeven));
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
	public List<GoodPeople> getPlayers() {
		return goodPeople;
	}


	/**
	 * 冒泡排序
	 * <p>
	 * 将七个葫芦娃按大小排序
	 * 将爷爷放在第一个
	 */
//	public void sortByRank() {
//		for (int i = 0; i < NUM_OF_GOODPEOPLE; i++) {
//			for (int j = 0; j < NUM_OF_GOODPEOPLE - i - 1; j++) {
//				if (goodPeople.get(j + 1).less(goodPeople.get(j)))
//					swap(j, j + 1);
//			}
//		}
//	}

	/**
	 * 将七个葫芦娃随机排序
	 */
//	@Override
//	private void sortByRandom() {
//		// 除了爷爷外，将七个葫芦娃随机排序
//		int randomIndex;
//		Random random = new Random();
//		for (int i = 1; i < NUM_OF_GOODPEOPLE - 1; i++) {
//			randomIndex = random.nextInt(NUM_OF_GOODPEOPLE - 1) + 1;   //范围为介于[1, NUM_OF_GOODPEOPLE]之间的整数
////			System.out.print(randomIndex);
//			swap(i, randomIndex);
//		}
//	}

//	private void printGoodPeople() {
//		for (GoodPeople goodPeople : goodPeople) {
//			System.out.print(goodPeople.getSymbol()+" ");
//		}
//		System.out.println();
//	}

	//	@Override
//	private void swap(int x, int y) {
//		GoodPeople temp = goodPeople.get(x);  // 这样子取出来的goodPeople是什么类型呢？？？
//		goodPeople.set(x, goodPeople.get(y));
//		goodPeople.set(y, temp);
//	}

//	public static void main(String[] args) {
//		GoodPeopleHolder goodPeopleHolder = new GoodPeopleHolder();
//		goodPeopleHolder.printGoodPeople();
//		for (int i = 0; i < 10; i++) {
//			goodPeopleHolder.sortByRandom();
//			System.out.print("乱序的    ");
//			goodPeopleHolder.printGoodPeople();
//			goodPeopleHolder.sortByRank();
//			System.out.print("排好序    ");
//			goodPeopleHolder.printGoodPeople();
//		}
//
//	}
}
