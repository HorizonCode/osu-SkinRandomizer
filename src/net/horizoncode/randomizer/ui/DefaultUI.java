package net.horizoncode.randomizer.ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DefaultUI {

	private String style;
	private Scene scene;
	private Stage stage;

	public DefaultUI(String sheet) {
		style = sheet;
		initUI();
	}

	private void initUI() {
		if (style == null || style.isEmpty()) {
			System.out.println("style is null");
			return;
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("sheets/" + style));
					scene = new Scene(fxmlLoader.load());
					stage = new Stage();
					stage.setTitle("Skin Randomizer!");
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
					stage.centerOnScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void hide() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				getStage().hide();
			}
		});
	}

	public void show() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				getStage().show();
			}
		});
	}

	public Stage getStage() {
		return stage;
	}

	public Scene getScene() {
		return scene;
	}
}
