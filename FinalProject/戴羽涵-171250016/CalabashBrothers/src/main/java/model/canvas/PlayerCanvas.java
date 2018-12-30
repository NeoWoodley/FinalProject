package model.canvas;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.creatures.Demon;
import model.creatures.GoodPeople;
import model.creatures.NoPerson;
import model.helper.BattleModel;
import model.helper.GameOver;
import model.helper.Judger;
import model.helper.Position;
import model.holder.DemonHolder;
import model.holder.GoodPeopleHolder;
import model.holder.Holder;
import model.object.BasePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerCanvas extends Canvas {
	final private static int rowNum = MapCanvas.rowNum;
	final private static int columnNum = MapCanvas.columnNum;
	private GraphicsContext gcOfPlayer;

	private List<BasePlayer> basePlayers;
	// 葫芦娃Holder
	private GoodPeopleHolder goodPeopleHolder;
	// 依次顺序是：爷爷、大、二、……、七
	private List<GoodPeople> goodPeople;
	// 妖精Holder
	private DemonHolder demonHolder;
	// 依次顺序是：蛇精、蝎子精、小喽啰们……
	private List<Demon> demons;
	private int numOfFollowings;

	// player的站队
	private BattleModel battleModelOfDemon;
	private final BattleModel battleModelOfGoodPeople = BattleModel.LONG_SNAKE;

	// 计时器
	private Timer demonsMoveTimer;
	private Timer attackTryingTimer;
	private Timer bulletsMoveTimer;

	private BulletCanvas bulletCanvas;
	private GraphicsContext gcOfBullet;
	private GraphicsContext gcOfHP;
	private Stage primaryStage;

	// 当前鼠标选中的goodPeople

	private static final NoPerson NO_PERSON = new NoPerson();  // 用于代替null，防止空指针异常
	private BasePlayer presentPlayer = NO_PERSON;
	private List<Position> positionsCanMoveTo;

	// 裁判，用于判断输赢
	private Judger judger;
	private String winner;

	public PlayerCanvas(double width, double height, MapCanvas mapCanvas, BulletCanvas bulletCanvas, HPCanvas hpCanvas,
	                    BattleModel battleModelOfDemon, int numOfFollowings, Stage primaryStage) {
		super(width, height);
		gcOfPlayer = this.getGraphicsContext2D();

		// PlayerCanvas持有MapCanvas、BulletCanvas、HPCanvas的引用
		MapCanvas mapCanvas1 = mapCanvas;
		// 创建BulletCanvas的对象
		this.bulletCanvas = bulletCanvas;
		HPCanvas hpCanvas1 = hpCanvas;
		gcOfBullet = this.bulletCanvas.getGraphicsContext2D();
		gcOfHP = hpCanvas.getGraphicsContext2D();

		// 阵型、小喽啰的数量设定
		this.battleModelOfDemon = battleModelOfDemon;
		this.numOfFollowings = numOfFollowings;

		this.primaryStage = primaryStage;
	}

	public void draw() {
		initPlayers();
		drawPlayers();
		initTimers();
	}

	/**
	 * 调用player的draw()方法，根据player的X、Y坐标属性来绘制player在GameMap上的位置
	 */
	private void drawPlayers() {
		for (GoodPeople goodPeople : goodPeople) {
			goodPeople.drawAll(gcOfPlayer, gcOfHP);
		}
		for (Demon demon : demons) {
			demon.drawAll(gcOfPlayer, gcOfHP);
		}
	}

	private void initPlayers() {
		goodPeopleHolder = new GoodPeopleHolder();
		goodPeople = goodPeopleHolder.getPlayers();
		demonHolder = new DemonHolder(numOfFollowings);
		demons = demonHolder.getPlayers();
		putPlayers();  // 按已经设定好的阵型将player放置到Map上
		// 将好人、坏人全都添加到basePlayers数组列表中，不管他们死了没有
		basePlayers = new CopyOnWriteArrayList<>(new ArrayList<>());
		basePlayers.addAll(goodPeople);
		basePlayers.addAll(demons);
		// 将鼠标事件放在BulletCanvas上，因为这是最最上面的一个Canvas
		bulletCanvas.requestFocus();  // 获取焦点
		bulletCanvas.setOnMouseClicked(this::onMouseClicked);

		// 创建Judger
		judger = new Judger(goodPeople, demons);
	}

	private void onMouseClicked(MouseEvent e) {
		System.out.println("MouseClicked");
		// 初始化preview、present
		// 当前点击的index
		Position presentClickedPosition = GameMap.getIndex(e.getX(), e.getY());  // 获得点击位置的index，可能会超出范围
		// 记录上一次选中的goodPeople，默认设置为NO_PERSON
		BasePlayer previousPlayer = presentPlayer;  // 记录上次点击事件选择的player
		presentPlayer = getBasePeople(presentClickedPosition.getIndexOfRow(), presentClickedPosition.getIndexOfColumn());
		System.out.println("previous: " + previousPlayer + ", present: " + presentPlayer);

		// 处理先后两次的点击
		// 前一次：goodPeople
		if (previousPlayer instanceof GoodPeople) {
			// 这一次：goodPeople
			if (presentPlayer instanceof GoodPeople) {
				// 清除上一次画的
				GameMap.recoverSquares(gcOfPlayer, positionsCanMoveTo);
				// 这一次：同一个
				if (presentPlayer == previousPlayer) {
					presentPlayer = NO_PERSON;  // 类似于取消选择
				} else {
					// 这一次：不是同一个goodPeople
					// 画上这一次的可到达区域
					positionsCanMoveTo = presentPlayer.getPositionsCanMoveTo(basePlayers);
					GameMap.drawAccessiableSquares(gcOfPlayer, positionsCanMoveTo, presentPlayer.getColor());
				}
			} else if (presentPlayer instanceof NoPerson) {
				// 这一次：NoPerson
				// 清除上一次画的颜色
				GameMap.recoverSquares(gcOfPlayer, positionsCanMoveTo);
				// 这一次：不可到达的NoPerson -> NoAction
				// 这一次：可到达的NoPerson
				if (presentPlayer.getPosisiton().isInPosisiontList(positionsCanMoveTo)) {
					// TODO: 2018-12-23 移动这个goodPeople
//					previousPlayer.setIndex(presentPlayer.getRowIndex(), presentPlayer.getColumnIndex());
					// 重新绘制player到指定的index
					System.out.println("redrawAll: " + presentPlayer.getRowIndex() + " " + presentPlayer.getColumnIndex());
					previousPlayer.redrawAll(gcOfPlayer, gcOfHP, presentPlayer.getRowIndex(),
							presentPlayer.getColumnIndex());
					presentPlayer = NO_PERSON;  // 归位为NoPerson
				}
			} else if (presentPlayer instanceof Demon) {
				// 这一次：demon
				// 清空上一次画的
				GameMap.recoverSquares(gcOfPlayer, positionsCanMoveTo);
			}
		}

		// 前一次：demon
		if (previousPlayer instanceof Demon) {
			// 这一次：goodPeople
			if (presentPlayer instanceof GoodPeople) {
				// 画上这一次的可到达区域
				positionsCanMoveTo = presentPlayer.getPositionsCanMoveTo(basePlayers);
				GameMap.drawAccessiableSquares(gcOfPlayer, positionsCanMoveTo, presentPlayer.getColor());
			} // 这一次：demon || NoPerson -> NoAction
		}

		// 前一次：NoPerson
		if (previousPlayer instanceof NoPerson) {
			// 这一次：goodPeople
			if (presentPlayer instanceof GoodPeople) {
				// 画上这一次的可到达区域
				positionsCanMoveTo = presentPlayer.getPositionsCanMoveTo(basePlayers);
				GameMap.drawAccessiableSquares(gcOfPlayer, positionsCanMoveTo, presentPlayer.getColor());
			} // 这一次：demon || NoPerson -> NoAction
		}
	}

	/**
	 * 根据index值，返回该位置的basePeople
	 * 如果该位置有player，则返回该player
	 * 如果没有player，则返回NoPerson
	 *
	 * @param indexOfRow
	 * @param indexOfColumn
	 * @return 为了兼容子类的类型，返回值设定为父类BasePlayer
	 */
	private BasePlayer getBasePeople(int indexOfRow, int indexOfColumn) {
		for (GoodPeople goodPeople : goodPeople) {
			if (goodPeople.getRowIndex() == indexOfRow && goodPeople.getColumnIndex() == indexOfColumn) {
				return goodPeople;
			}
		}
		for (Demon demon : demons) {
			if (demon.getRowIndex() == indexOfRow && demon.getColumnIndex() == indexOfColumn) {
				return demon;
			}
		}
		return new NoPerson(indexOfRow, indexOfColumn);
	}

	/**
	 * 选择合适的站队设置player的position属性
	 */
	private void putPlayers() {
		goodPeopleHolder.changeBattleModel(battleModelOfGoodPeople);
		demonHolder.changeBattleModel(battleModelOfDemon);
		switchBattleModel(goodPeopleHolder);
		switchBattleModel(demonHolder);
	}

	private void switchBattleModel(Holder<? extends BasePlayer> holder) {
		switch (holder.getBattleModel()) {
			case CRANE_WING:
				changeToCRANE_WING(holder.getPlayers());
				break;
			case WILD_GOOSE:
				changeToWILD_GOOSE(holder.getPlayers());
				break;
			case WOOD:
				changeToWOOD(holder.getPlayers());
				break;
			case LONG_SNAKE:  // 只有葫芦娃们使用这个队形
				changeToLONG_SNAKE(holder.getPlayers());
				break;
			case FISH_SCALE:
				changeToFISH_SCALE(holder.getPlayers());
				break;
			case CIRCLE:
				changeToCIRCLE(holder.getPlayers());
				break;
			case MOON:
				changeToMOON(holder.getPlayers());
				break;
			case ARROW:
				changeToARROW(holder.getPlayers());
				break;
			default:
				break;
		}
	}

	/**
	 * 将多个Timer进行实例化，并执行TimerTask.schedule()
	 */
	private void initTimers() {
		initDemonsMoveTimer();
		initAttackTryingTimer();
		initBulletsMoveTimer();
	}

	private void initDemonsMoveTimer() {
		demonsMoveTimer = new Timer("DemonsMoveTimer");
		demonsMoveTimer.schedule(new TimerTask() {
			@Override
			public void run() {
//				System.out.println("Demons move forwads!!!");
				for (Demon demon : demons) {
					demon.redrawMoveLeft(gcOfPlayer, gcOfHP, basePlayers);
				}
			}
		}, 2000, 1000);  // delay秒后开始执行，每隔period秒就再次执行一次
	}

	private void initAttackTryingTimer() {
		attackTryingTimer = new Timer("AttackTryingTimer");
		attackTryingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
//				System.out.println("AttackTryingTimer");
				for (BasePlayer basePlayer : basePlayers) {
					if (basePlayer.isAlive())
						basePlayer.tryToAttack();  // 修改每个player的bulletList
				}
			}
		}, 2000, 7000);
	}

	private void initBulletsMoveTimer() {
		bulletsMoveTimer = new Timer("BulletsMoveTimer");
		bulletsMoveTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (BasePlayer basePlayer : basePlayers) {
					basePlayer.redrawBullets(gcOfBullet, gcOfHP, basePlayers);  // 遍历每个player的bulletList
				}
//				System.out.println("---------------------------------------------------");
//				System.out.println(judger.isGameOver());
				if (judger.isGameOver()) {
					System.out.println("GameOver");
					winner = judger.getWinner();
					winner = Demon.class.getName();
					Platform.runLater(() -> {
						try {
							gameOver();
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
			}
		}, 2000, 50);
	}


	/**
	 * 有一方已经全部死亡，游戏结束
	 */
	private void gameOver() throws Exception {
		demonsMoveTimer.cancel();
		attackTryingTimer.cancel();
		bulletsMoveTimer.cancel();
		new GameOver(judger.getWinner()).start(primaryStage);
		System.out.println("callGameOver");
	}

	// 各种BattleModel对应的队形

	/**
	 * *     *
	 * *   *
	 * * *
	 * *（奇偶性）
	 * *（蝎子精）
	 */
	private void changeToCRANE_WING(List<? extends BasePlayer> basePlayers) {
		if (basePlayers.get(0) instanceof Demon) {
			int numOfFollowing = basePlayers.size() - 2;
			int rowOfFollowing = (int) Math.ceil(numOfFollowing / 2.0);
			int startRowNum = (rowNum - (rowOfFollowing + 1)) / 2;
			int startColumnNum = columnNum - rowOfFollowing * 2 + numOfFollowing % 2;
			int endRowRowNum = startRowNum + rowOfFollowing - 1;
			int endColumnNum = startColumnNum + rowOfFollowing - 1;
			int index = 2;
			// 放入小喽啰们
			int j = startColumnNum;
			for (int i = startRowNum; i <= endRowRowNum; i++) {
				basePlayers.get(index++).setIndex(i, j++);
			}
			if (numOfFollowing % 2 == 0) {
				basePlayers.get(index++).setIndex(endRowRowNum, j++);
			}
			for (int i = endRowRowNum - 1; i >= startRowNum; i--) {
				basePlayers.get(index++).setIndex(i, j++);
			}

			// 放入领队的蝎子精
			basePlayers.get(1).setIndex(endRowRowNum + 1, endColumnNum);
			// 放入蛇精，呐喊助威用的
			basePlayers.get(0).setIndex(rowNum - 1, columnNum - 1);
		}
	}

	/**
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 */
	private void changeToWILD_GOOSE(List<? extends BasePlayer> basePlayers) {
		if (basePlayers.get(0) instanceof Demon) {
			int startRowNum = (rowNum - basePlayers.size() + 1) / 2;
			int index = basePlayers.size() - 1;
			// 放入小喽啰和领队的蝎子精
			for (int i = startRowNum; i > startRowNum + basePlayers.size() - 1; i--) {
				basePlayers.get(index++).setIndex(i, startRowNum + columnNum - 1 - i);
			}
		}
		// 放入蛇精，呐喊助威用的
		basePlayers.get(0).setIndex(rowNum - 1, columnNum - 1);
	}

	/**
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 * 假定了小喽啰的数量不会超过两列
	 */
	private void changeToWOOD(List<? extends BasePlayer> basePlayers) {
		if (basePlayers.get(0) instanceof Demon) {
			int startRowNum = (rowNum - (basePlayers.size() - 2)) / 2;
			// 将小哈喽们分成两队，第一对的人数<=第二队的人数
			int partOneNum = (basePlayers.size() - 2) / 2;
			int partTwoNum = basePlayers.size() - 2 - partOneNum;
			int index = 2;
			// 放入小哈喽们
			for (int i = startRowNum; i <= startRowNum + 2 * (partTwoNum - 1); i += 2) {
				basePlayers.get(index++).setIndex(i, columnNum - 1);
			}
			for (int i = startRowNum + 1; i <= 1 + startRowNum + 2 * (partOneNum - 1); i += 2) {
				basePlayers.get(index++).setIndex(i, columnNum - 2);
			}
			// 放入蝎子精
			basePlayers.get(1).setIndex(rowNum / 2, columnNum - 3);
			// 放入蛇精，呐喊助威用的
			basePlayers.get(0).setIndex(rowNum - 1, columnNum - 1);
		}
	}

	/**
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 * *
	 *
	 * @param basePlayers demons并不使用这个队形
	 */
	private void changeToLONG_SNAKE(List<? extends BasePlayer> basePlayers) {
		// 将葫芦娃们放入map
		if (basePlayers.get(0) instanceof GoodPeople) {
			int startRowNum = (rowNum - (basePlayers.size() - 1)) >> 1;
			int index = 1;
			for (int i = startRowNum; i < startRowNum + basePlayers.size() - 1; i++) {
				goodPeople.get(index++).setIndex(i, 1);
			}
			// 将爷爷放入map
			goodPeople.get(0).setIndex(rowNum >> 1, 0);
		}
	}

	/**
	 * *
	 * * * *
	 * *   *   *
	 * *  这里至少要有两个 below
	 * *  这里至少要有两个 below 规定这个是蝎子精
	 */
	private void changeToARROW(List<? extends BasePlayer> basePlayers) {
		if (basePlayers instanceof Demon) {
			int numOfThree = ((basePlayers.size() - 2) - 2) / 3;  // 计算出一行中有三个并列的情况有多少个，两次"-2"的含义分别是：扣掉蝎子精、蛇精；再扣掉最顶上、最底下的两个喽啰的耗损
			int numOfVertical = basePlayers.size() - 2 - 3 * numOfThree + numOfThree + 1;  // 最后加的1是添上的蝎子精
			int startRowNum = (rowNum - numOfVertical) / 2;
			// 先把一竖条的喽啰摆放好，暂时先不放蝎子精
			int index = 2;
			int startColumnNum = columnNum - numOfThree - 1;
			for (int i = startRowNum; i < startRowNum + numOfVertical - 1; i++) {
				demons.get(index++).setIndex(i, startColumnNum);
			}
			// 把斜条上的小喽啰放上，先左边，后右边
			int j = startColumnNum + 1;
			for (int i = startRowNum + 1; i <= startRowNum + numOfThree; i++) {
				demons.get(index++).setIndex(i, startRowNum + startColumnNum - i);
				demons.get(index++).setIndex(i, j++);
			}
			// 放上蝎子精、蛇精
			demons.get(1).setIndex(startRowNum + numOfVertical - 1, startColumnNum);
			demons.get(0).setIndex(rowNum - 1, columnNum - 1);
		}
	}

	/**
	 * *（startRowNum，startColumnNum）
	 * * *
	 * **   *（middleRow）
	 * * *
	 * *（endRowNum，endColumnNum）
	 */
	private void changeToCIRCLE(List<? extends BasePlayer> basePlayers) {
		// TODO: 2018/10/9 判断是否为Demon
		if (basePlayers.get(0) instanceof Demon) {
			int numOfFollowing = basePlayers.size() - 2;
			int numOfTwo = (numOfFollowing - 2) / 2;  // 一列中有两个的情况数量
			int numOfOne = numOfFollowing - 2 * numOfTwo;  // 一列中只有一个的情况数量
			int numOfVercital = (numOfTwo + 1) / 2 * 2 + 1;

			int startRowNum = (rowNum - numOfVercital) / 2;
			int startColumnNum = (columnNum - 1) - (numOfTwo / 2 + 1);
			int endRowNum = startRowNum + numOfVercital - 1;
			int endColumnNum = startColumnNum;
			int middleRowNum = startRowNum + numOfVercital / 2;
			int middleColumnNum = columnNum - 1 - 1 - numOfTwo;

			int index = 2;
			int j = startColumnNum + 1;

			// 先把最上面、最下面的单个（或多个）小喽啰放上去
			basePlayers.get(index++).setIndex(startRowNum, startColumnNum);
			basePlayers.get(index++).setIndex(endRowNum, endColumnNum);
			if (numOfTwo % 2 == 0) {
				basePlayers.get(index++).setIndex(startRowNum, startColumnNum + 1);
				basePlayers.get(index++).setIndex(endRowNum, endColumnNum + 1);
			}
			for (int i = startRowNum + 1; i <= middleRowNum; i++) {
				basePlayers.get(index++).setIndex(i, startRowNum + startColumnNum - i);
				basePlayers.get(index++).setIndex(i, (1 + numOfTwo) % 2 + j++);
			}
			j = endColumnNum - 1;  // 恢复j的值
			for (int i = endRowNum - 1; i > middleRowNum; i--) {
				basePlayers.get(index++).setIndex(i, j--);
				basePlayers.get(index++).setIndex(i, (1 + numOfTwo) % 2 + endRowNum + endColumnNum - i);
			}
			// 把多余下来的单个小喽啰、蝎子精放上去
			int leftFollowing = numOfOne - 2;
			if (leftFollowing > 0) {
				basePlayers.get(index++).setIndex(middleRowNum, middleColumnNum - 1);
				basePlayers.get(1).setIndex(middleRowNum, middleColumnNum - 2);
			} else {
				basePlayers.get(1).setIndex(middleRowNum, middleColumnNum - 1);
			}
		}
		// 放入蛇精
		basePlayers.get(0).setIndex(rowNum - 1, columnNum - 1);
	}


	/**
	 * * 1
	 * ** 2
	 * *** 3
	 * ***  3
	 * **  2
	 * *  1
	 * * 1
	 * 1354
	 */
	private void changeToMOON(List<? extends BasePlayer> basePlayers) {
		int numOfFollowing = basePlayers.size() - 2;
		int columnOfFollowing = (int) Math.ceil(Math.sqrt(numOfFollowing));
		int startRowNum = rowNum >> 1;
		int startColumnNum = columnNum - columnOfFollowing;
		int index = 2;
		// 放入小喽啰
		int count = 0;
		for (int j = startColumnNum; j < startColumnNum + columnOfFollowing; j++) {
			for (int i = startRowNum - count; i <= startRowNum; i++) {
				if (index >= basePlayers.size()) {
					break;
				}
				if (startRowNum * 2 - i == startRowNum) {
					basePlayers.get(index++).setIndex(i, j);
				} else {
					basePlayers.get(index++).setIndex(i, j);
					if (index >= basePlayers.size()) {
						break;
					}
					basePlayers.get(index++).setIndex(startRowNum * 2 - i, j);
				}
			}
			count++;
		}
		// 放入蝎子精、蛇精
		basePlayers.get(1).setIndex(startRowNum, startColumnNum - 1);
		basePlayers.get(0).setIndex(startRowNum + 1, startColumnNum - 1);
	}

	/**
	 * *
	 * * * *
	 * * * * * *
	 * * * * * * * *
	 * *（蝎子精）
	 * todo 有问题！！！
	 */
	private void changeToFISH_SCALE(List<? extends BasePlayer> basePlayers) {
		int numOfFollowing = basePlayers.size() - 2;
		int rowOfTriangle = (int) Math.ceil(Math.sqrt(numOfFollowing));
		int numOfVertical = rowOfTriangle + 1;
		int startRowNum = (rowNum - numOfVertical) >> 1;
		int startColumnNum = columnNum - rowOfTriangle;
		int index = 2;
		int count = 0;
		// 先放入小喽啰们构成的三角形
		for (int i = startRowNum; i < startRowNum + rowOfTriangle; i++) {
			for (int j = startColumnNum - count; j <= startColumnNum + count; j++) {
				basePlayers.get(index++).setIndex(i, j);
				if (index >= basePlayers.size()) {
					break;
				}
			}
			count++;
		}
		// 放入蝎子精、蛇精
		basePlayers.get(1).setIndex(startRowNum + rowOfTriangle, startColumnNum);
		basePlayers.get(0).setIndex(rowNum - 1, columnNum - 1);
	}
}