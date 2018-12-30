package model.helper;

import java.util.Random;

public enum BattleModel {
	CRANE_WING("鹤翼"),  // 鹤翼
	WILD_GOOSE("雁行"),  // 雁行
	WOOD("行轭"),  // 行轭
	LONG_SNAKE("长蛇"),  // 长蛇，只有葫芦娃使用这个队形
	FISH_SCALE("鱼鳞"),  // 鱼鳞
	CIRCLE("方圆"),  // 方圆
	MOON("偃月"),  // 偃月
	ARROW("锋矢");  // 锋矢

	final private String nameOfBattleModel;

	BattleModel(String nameOfBattleModel) {
		this.nameOfBattleModel = nameOfBattleModel;
	}

	@Override
	public String toString() {
		return this.nameOfBattleModel;
	}

	/**
	 * 得到随机生成的BattleModel
	 * @return BattleModel的枚举类型
	 */
	public static BattleModel getRandomBattleModel() {
		Random random = new Random();
		int randomNum=random.nextInt(8);
		BattleModel[] battleModels = BattleModel.values();
		while (randomNum == 4) {
			randomNum = random.nextInt(8);
		}
		return battleModels[randomNum];
	}

	public static BattleModel getBattleModel(String nameOfBattleModel) {
		switch (nameOfBattleModel) {
			case "鹤翼":
				return CRANE_WING;
			case "雁行":
				return WILD_GOOSE;
			case "行轭":
				return WOOD;
			case "长蛇":
				return LONG_SNAKE;
			case "鱼鳞":
				return FISH_SCALE;
			case "方圆":
				return CIRCLE;
			case "偃月":
				return MOON;
			case "锋矢":
				return ARROW;
				default:
					return null;
		}
	}
}
