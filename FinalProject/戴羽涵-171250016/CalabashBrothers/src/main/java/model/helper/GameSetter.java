package model.helper;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 游戏初始化设置：
 * 需要由用户输入来选择葫芦娃的阵型、小喽啰的数量
 */
public class GameSetter {
	private int numOfFollowings;
	private String textOfInput;
	private BattleModel battleModelOfDemon;
	private Label message;  // 用户提醒用户输入的不合法性

	public GameSetter() {
		message = new Label();
	}

	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = initSet(primaryStage);
		Scene scene = new Scene(borderPane, 1500, 900, Color.LIGHTBLUE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 初始设定：
	 * 1、Demon的阵型（随机生成）：Text
	 * 2、小喽啰的数量：TextField，需要进行输入合法性的判定
	 * 3、葫芦娃的阵型（由用户选择）：RadioButton
	 *
	 * @return 返回一个BorderPane
	 */
	private BorderPane initSet(Stage primaryStage) {
		BorderPane borderPane = new BorderPane();
//		Text battleModelOfDemon=new Text("妖精们随机生成的阵型为："+ this.battleModelOfDemon);
		TextField numOfFollowings = new TextField("请输入小喽啰的数量（1～10）");
		numOfFollowings.setMaxWidth(300);
		ToggleGroup group = new ToggleGroup();
		RadioButton heYi = new RadioButton("鹤翼");
		heYi.setUserData("鹤翼");
		RadioButton xingE= new RadioButton("行轭");
		xingE.setUserData("行轭");
		RadioButton yuLin= new RadioButton("鱼鳞");
		yuLin.setUserData("鱼鳞");
		RadioButton fangYuan= new RadioButton("方圆");
		fangYuan.setUserData("方圆");
		RadioButton yanYue= new RadioButton("偃月");
		yanYue.setUserData("偃月");
		heYi.setToggleGroup(group);
		heYi.setSelected(true);
		xingE.setToggleGroup(group);
		yuLin.setToggleGroup(group);
		fangYuan.setToggleGroup(group);
		yanYue.setToggleGroup(group);

		Button button = new Button("开始游戏");
		button.setOnAction(e->{
			textOfInput = numOfFollowings.getText().trim();
			if (isLeagel()) {
				message.setVisible(false);
				battleModelOfDemon = BattleModel.getBattleModel(group.getSelectedToggle().getUserData().toString());
				callGameStarter(primaryStage);
			} else {
				message.setVisible(true);
				message.setText("小喽啰数量不合法，请检查");
				message.setTextFill(Color.RED);
			}
		});

		HBox hBox = new HBox(20);  // 间距为20
		hBox.getChildren().addAll(heYi, xingE, yuLin, fangYuan, yanYue);
		VBox vBox = new VBox(40);  // 间距为20
		vBox.getChildren().addAll(numOfFollowings, new Text("请选择妖精的阵型："), hBox,button,message);
		borderPane.setBottom(vBox);
		borderPane.setPadding(new Insets(20,20,20,20));
		borderPane.setTop(new ImageView(new Image("/picture/葫芦山.jpg")));
		return borderPane;
	}

	private boolean isLeagel() {
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(textOfInput);
		if (matcher.matches()) {
			int num = Integer.valueOf(textOfInput);
			if (num >= 1 && num <= 10) {
				numOfFollowings = num;
				return true;
			}
		}
		return false;
	}

	/**
	 * call主界面的生成函数
	 */
	private void callGameStarter(Stage oldStage) {
		Platform.runLater(()->{
			// 创建主界面窗口
			try {
				new GameStarter(battleModelOfDemon,numOfFollowings).start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		oldStage.close();
	}
}
