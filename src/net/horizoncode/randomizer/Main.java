package net.horizoncode.randomizer;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.application.Application;
import javafx.stage.Stage;
import net.horizoncode.randomizer.controllers.MainController;
import net.horizoncode.randomizer.ui.MainUI;

public class Main extends Application {
	
	public static MainUI mainUI;
	public static MainController mainController;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainUI = new MainUI();
	}
	
}
