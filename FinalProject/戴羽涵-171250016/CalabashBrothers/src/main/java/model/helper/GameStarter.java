package model.helper;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.canvas.BulletCanvas;
import model.canvas.HPCanvas;
import model.canvas.MapCanvas;
import model.canvas.PlayerCanvas;

public class GameStarter extends Application {
	private int numOfFollowings;
	private BattleModel battleModelOfDemon;

	public GameStarter(BattleModel battleModelOfDemon, int numOfFollowings) {
		this.battleModelOfDemon = battleModelOfDemon;
		this.numOfFollowings = numOfFollowings;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("gameStart");
		Group root = new Group();
		// 三层画布
		MapCanvas mapCanvas = new MapCanvas(1500, 900);
		BulletCanvas bulletCanvas = new BulletCanvas(1500, 900);
		HPCanvas hpCanvas = new HPCanvas(1500, 900);
		PlayerCanvas playerCanvas = new PlayerCanvas(1500, 900, mapCanvas, bulletCanvas, hpCanvas, battleModelOfDemon,
				numOfFollowings,primaryStage
		);  // 让playerCanvas
		// 持有bulletCancas的实例变量
		mapCanvas.draw();
		playerCanvas.draw();
		root.getChildren().addAll(mapCanvas, playerCanvas, hpCanvas, bulletCanvas);
		Scene scene = new Scene(root, 1500, 900, Color.GRAY);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
