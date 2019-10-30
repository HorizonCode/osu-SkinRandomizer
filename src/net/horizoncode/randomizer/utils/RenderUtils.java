package net.horizoncode.randomizer.utils;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RenderUtils {

	public static void renderDialog(StackPane stackpane, String header, String text, String Button) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(header));
		content.setBody(new Text(text));
		stackpane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		content.setStyle("-fx-text-fill:WHITE;-fx-background-color:#ecf0f1;-fx-font-size:14px;-fx-font-family:system;");
		JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
		if (!Button.isEmpty()) {
			JFXButton closeButton = new JFXButton(Button);
			closeButton.setStyle("-fx-background-color: #4059a9;-fx-font-size:13px");
			closeButton.setTextFill(Color.WHITE);
			closeButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					dialog.close();
				}
			});
			content.setActions(closeButton);
		}
		dialog.show();
	}

	public static void renderExitDialog(StackPane stackpane, String header, String text) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				JFXDialogLayout content = new JFXDialogLayout();
				Text headere = new Text(header);
				headere.setFill(Color.WHITE);
				content.setHeading(headere);
				Text texte = new Text(text);
				texte.setFill(Color.WHITE);
				content.setBody(texte);
				stackpane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
				content.setStyle("-fx-text-fill:WHITE;-fx-background-color:#353D45;-fx-font-size:14px;-fx-font-family:system;");
				JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
				List<Node> actions = new ArrayList<Node>();
				JFXButton abortButton = new JFXButton("Cancel");
				abortButton.setStyle("-fx-background-color: #9224A6;-fx-font-size:13px;-fx-translate-x:13");
				abortButton.setTextFill(Color.WHITE);
				abortButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialog.close();
					}
				});
				actions.add(abortButton);
				JFXButton closeButton = new JFXButton("Exit");
				closeButton.setStyle("-fx-background-color: #C62828;-fx-font-size:13px;-fx-translate-x:15");
				closeButton.setTextFill(Color.WHITE);
				closeButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Platform.exit();
					}
				});
				actions.add(closeButton);
				content.setActions(actions);
				dialog.show();
			}
		});
	}

	public static void loadFXML(Pane pane, Node node) {
		pane.getChildren().clear();
		pane.getChildren().add(node);
	}
}
