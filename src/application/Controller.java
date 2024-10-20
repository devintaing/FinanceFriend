package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private void loadScene(String fxmlFile, ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxmlFile));
		scene = new Scene(root);

		String css = getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void switchToHome(ActionEvent event) throws IOException {
		loadScene("Homepage.fxml", event); // Use the loadScene method
	}

	public void switchToNewAccount(ActionEvent event) throws IOException {
		loadScene("NewAccount.fxml", event); // Use the loadScene method
	}
}
