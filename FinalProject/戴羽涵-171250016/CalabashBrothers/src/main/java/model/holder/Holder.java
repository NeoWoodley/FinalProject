package model.holder;

import model.helper.BattleModel;
import model.object.BasePlayer;

import java.util.List;

public interface Holder<T extends BasePlayer> {
	void changeBattleModel(BattleModel newBattleModel);

	BattleModel getBattleModel();

	List<T> getPlayers();
}