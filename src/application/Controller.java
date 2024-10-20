package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
	@FXML
	TextField accountName;
	@FXML
	TextField openingBalance;
	@FXML
	Button submit;
	@FXML
	DatePicker openingDate;
	
	double balance;
	String name;
	
    private static HashSet<String> accountNames = new HashSet<>();

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToHome(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToNewAccount(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("newAccount.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void submitButton(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String name = accountName.getText().trim();
        String balanceStr = openingBalance.getText().trim();
        LocalDate date = openingDate.getValue();
        
        // Prevents user from leaving the required fields empty
        if (name.isEmpty() || balanceStr.isEmpty() || date == null) {
            showAlert("All fields are required!");
            return;
        }
        
        // Check for duplicate account names
        if (accountNames.contains(name)) {
            showAlert("Account name already exists!");
            return;
        }
        try {
            balance = Double.parseDouble(balanceStr);
        } catch (NumberFormatException e) {
            showAlert("Opening balance must be a number!");
            return;
        }

        // If all validations pass, add account name to set
        accountNames.add(name);

        // Save information to be used on other pages or database
        saveAccount(name, date, balance);

        // Show success message
        alert.setTitle("Success");
        alert.setHeaderText("Account successfully added!");
        alert.showAndWait();
	}
	
    private void saveAccount(String name, LocalDate date, double balance) {
        // Save account information
        System.out.printf("Saved %s with balance %.2f on %s%n", name, balance, date.toString());
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
