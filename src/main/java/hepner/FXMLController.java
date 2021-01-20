package hepner;
/*
Maximilian Hepner
January 20th, 2021
simple hangman game
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;


public class FXMLController implements Initializable {
    String[] words = new String[9];
    String shown, quote, check;
    int man = 0;
    int correct = 0;


    @FXML
    private Label lblOut;
    @FXML
    private TextField txtInput;
    @FXML
    private ImageView imgMan;
    @FXML
    private Button btnGuess;
    @FXML
    private Button btnStart;

    @FXML
    void btnGuessAction(ActionEvent event) {

        try {
            int temp = Integer.parseInt(txtInput.getText());

            return;
        } catch (NumberFormatException ignored) {
        }
        String charPressed = txtInput.getText();
        boolean foundLetter = false;
        // Use a nested structure to loop through the quote and find matching letters.

        for (int i = 0; i < quote.length(); i++) {
            if (quote.substring(i, i + 1).toLowerCase().equals(charPressed)) {
                // match found, change shown quote

                shown = shown.substring(0, i) + charPressed + shown.substring(i + 1);
                lblOut.setText(shown);
                correct -= -1;
                foundLetter = true;
            }
        }
        if (!foundLetter) {
            man -= -1;
            imgMan.setImage(new Image(getClass().getResource("/Images/hang" + man + ".gif").toString()));
        }
        if (man == 10) {
            //TODO lose
            System.out.println("lose");
            Alert("LOSE!","Click the 'ok' button and then 'Start The Game' to try again");
        }
        if (correct == check.length()) {
            //TODO win
            System.out.println("win");
            Alert("WIN!","Click the 'ok' button and then 'Start The Game' to play again");
        }

    }

    public void Alert(String title, String message){
        btnGuess.setDisable(true);
        txtInput.setDisable(true);
        btnStart.setVisible(true);
        btnStart.setDisable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @FXML
    void btnStartAction(ActionEvent event) {
        btnStart.setDisable(true);
        btnStart.setVisible(false);
        btnGuess.setDisable(false);
        txtInput.setDisable(false);
        int rand = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("hangman.txt"));
            for (int i = 0; i < rand; i++) {

                words[0] = readFile.readLine();

            }
            readFile.close();
            quote = words[0].replaceAll(".(?=.)", "$0 ");
            check = words[0];

            shown = quote.replaceAll("[a-zA-Z]", "_");
            // Create a copy of quote with _ for each letter;
            //Where quote is the word you want to guess on
            lblOut.setText(shown);


        } catch (IOException ignored) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
