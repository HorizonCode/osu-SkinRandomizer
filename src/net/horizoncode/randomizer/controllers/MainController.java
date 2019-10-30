package net.horizoncode.randomizer.controllers;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import net.horizoncode.randomizer.Main;
import net.horizoncode.randomizer.utils.FileUtil;
import net.horizoncode.randomizer.utils.RandomSkinUntil;
import net.horizoncode.randomizer.utils.RenderUtils;

public class MainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	public StackPane stackPane;

	@FXML
	private JFXTextField txtField_osuSkinsPath;

	@FXML
	private JFXButton btn_browse;

	@FXML
	private JFXTextField txtField_NameOfTheSkin;

	@FXML
	public JFXButton btb_randomize;

	@FXML
	public JFXSpinner spinner;

	@FXML
	void initialize() {
		assert txtField_osuSkinsPath != null : "fx:id=\"txtField_osuSkinsPath\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert btn_browse != null : "fx:id=\"btn_browse\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert txtField_NameOfTheSkin != null : "fx:id=\"txtField_NameOfTheSkin\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert btb_randomize != null : "fx:id=\"btb_randomize\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert spinner != null : "fx:id=\"spinner\" was not injected: check your FXML file 'MainPanel.fxml'.";

		Main.mainController = this;

		btb_randomize.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						if (txtField_osuSkinsPath.getText().isEmpty()) {
							RenderUtils.renderDialog(stackPane, "Error", "Please set a Skin Folder Location!", "Okay");
							return;
						}

						if (txtField_NameOfTheSkin.getText().isEmpty()) {
							RenderUtils.renderDialog(stackPane, "Error", "Please set a Skin Name!", "Okay");
							return;
						}

						File directory = new File(txtField_osuSkinsPath.getText());

						if (directory == null || !directory.isDirectory()) {
							RenderUtils.renderDialog(stackPane, "Error", "Skin Folder Location is nonexistent or its not a Directory!", "Okay");
							return;
						}

						File output = new File(directory, txtField_NameOfTheSkin.getText());
						List<File> files = FileUtil.listFiles(directory, output);

						new RandomSkinUntil(files, output);

					}
				});
			}
		});

		btn_browse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						DirectoryChooser chooser = new DirectoryChooser();
						chooser.setTitle("Select Folder");
						File selected = chooser.showDialog(Main.mainUI.getStage());
						if (selected != null) {
							if (selected.isDirectory()) {
								txtField_osuSkinsPath.setText(selected.getAbsolutePath());
							} else {
								RenderUtils.renderDialog(stackPane, "Error", "Selected File is not a Directory!", "Okay");
							}
						}
					}
				});
			}
		});

	}
}
