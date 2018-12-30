package model.helper;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.creatures.Demon;
import model.creatures.GoodPeople;

import java.util.Optional;

public class GameOver extends Application {
	private String winner;

	public GameOver( String winner) {
		this.winner = winner;

		endGame();
	}

	/**
	 * 游戏结束
	 */
	private void endGame() {
		if (winner.equals(Demon.class.getName())) {
			alert("真是可怜，你被妖精消灭啦！");
			System.out.println("真是可怜，你被妖精消灭啦！");
		} else if (winner.equals(GoodPeople.class.getName())) {
			alert("你真棒，妖精都被你消灭啦！");
			System.out.println("你真棒，妖精都被你消灭啦！");
		}
	}

	private void alert(String alertString) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  // 确认对话框
		alert.setTitle("游戏结束");
		alert.setHeaderText("制作极其垃圾的葫芦娃大战妖精游戏结束啦");
		alert.setContentText(alertString);

		// TODO: 2018-12-28 不论按哪个按钮，都会退出hhh
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		} else {
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		endGame();
	}
}
