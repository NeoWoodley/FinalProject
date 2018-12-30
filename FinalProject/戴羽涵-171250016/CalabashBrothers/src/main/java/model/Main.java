package model;

import javafx.application.Application;
import javafx.stage.Stage;
import model.helper.GameSetter;

/**
 * 主入口
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		GameSetter gameSetter = new GameSetter();
		gameSetter.start(primaryStage);
	}
}
