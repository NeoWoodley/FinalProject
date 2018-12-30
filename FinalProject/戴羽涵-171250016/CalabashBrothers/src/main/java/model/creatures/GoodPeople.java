package model.creatures;

import model.object.BasePlayer;

public class GoodPeople extends BasePlayer {
	public GoodPeople(GoodPeopleEnum goodPeopleEnum) {
		super(goodPeopleEnum.getName(), goodPeopleEnum.getRank(), goodPeopleEnum.getImageName(), goodPeopleEnum.getColor());
	}
}
