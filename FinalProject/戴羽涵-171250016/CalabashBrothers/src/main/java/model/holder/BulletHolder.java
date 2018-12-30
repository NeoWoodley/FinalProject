package model.holder;

import model.object.Bullet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 每个player会持有一个BulletHolder对象
 */
public class BulletHolder {
	private List<Bullet> bulletsList;

	public BulletHolder() {
		bulletsList = new CopyOnWriteArrayList<>();
	}

	public synchronized void addBullet(Bullet bullet) {
		bulletsList.add(bullet);
	}

	public synchronized void removeBullet(Bullet bullet) {
		bulletsList.remove(bullet);
	}

	public synchronized List<Bullet> getBulletList() {
		return bulletsList;
	}
}
