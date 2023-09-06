package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {

    private String labelText;

    String selectedButton = "incorrect";

    @FXML
    private Label correctLabel;

    @FXML
    private Label incorrectLabel;

    @FXML
    private Label welcomeText;

    @FXML
    private Button myButton;

    private int numCorrect = 0;

    private int numIncorrect = 0;

    @FXML
    private Button buttonA;


    @FXML
    private Button buttonB;

    @FXML
    private Button buttonC;

    @FXML
    private Button buttonD;

    @FXML
    private Button submitButton;

    @FXML
    private Label questionLabel;

    private int idx;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        questionLabel.setWrapText(true);
        questionLabel.setMinWidth(700);
        questionLabel.setMinHeight(100);
        GameLogic.generateVocabList();
        resetColors();
        idx = 0;
        labelText = Vocab.getDefinitionList().get(idx);
        correctLabel.setText("Correct Answers: " + String.valueOf(numCorrect));
        incorrectLabel.setText("Incorrect Answers: " + String.valueOf(numIncorrect));
        questionLabel.setText(labelText);
        buttonA.setText(Vocab.getWordList().get(idx));
        buttonB.setText(Vocab.getWordList().get((idx + 3) % 26));
        buttonC.setText(Vocab.getWordList().get((idx + 9) % 26));
        buttonD.setText(Vocab.getWordList().get((idx + 15) % 26));
    }

    public void setButtons() {

        correctLabel.setText("Correct Answers: " + String.valueOf(numCorrect));
        incorrectLabel.setText("Incorrect Answers: " + String.valueOf(numIncorrect));
        idx++;
        labelText = Vocab.getDefinitionList().get(idx);
        questionLabel.setText(labelText);
        buttonA.setText(Vocab.getWordList().get(idx));
        buttonB.setText(Vocab.getWordList().get((idx + 3) % 26));
        buttonC.setText(Vocab.getWordList().get((idx + 9) % 26));
        buttonD.setText(Vocab.getWordList().get((idx + 15) % 26));
    }


    public void buttonClicked(ActionEvent itemClicked) {
        Button buttonClicked = (Button) itemClicked.getSource();
        if (!buttonClicked.equals(submitButton)) {
            String answer = labelText.replace("_____", buttonClicked.getText());
            questionLabel.setText(answer);
            resetColors();
            buttonClicked.setStyle("-fx-background-color: #ffcccb; -fx-text-fill: black; -fx-font-size: 16px;");
            if (buttonClicked.equals(buttonA)) {
                selectedButton = "Button A";
            }
            else {
                selectedButton = "incorrect";
            }
        }
        else {
            if(idx == 26) {
                Node node = (Node) itemClicked.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            }
            resetColors();

            if (selectedButton.equals("Button A")) {
                buttonClicked.setStyle("-fx-background-color: #008000; -fx-text-fill: black; -fx-font-size: 16px;");
                numCorrect++;
                selectedButton = "incorrect";
                setButtons();
            } else {
                buttonClicked.setStyle("-fx-background-color: #FF0000; -fx-text-fill: black; -fx-font-size: 16px;");
                numIncorrect++;
                setButtons();
            }
            if (numCorrect * 1.0 / (numIncorrect + numCorrect) >= 0.9) {
                questionLabel.setStyle("-fx-text-fill: green;");
            }
            else if (numCorrect * 1.0 / (numIncorrect + numCorrect) >= 0.8) {
                questionLabel.setStyle("-fx-text-fill: orange");
            }
            else if (numCorrect * 1.0 / (numIncorrect + numCorrect) >= 0.7) {
                questionLabel.setStyle("-fx-text-fill: blue");
            }
            else {
                questionLabel.setStyle("-fx-text-fill: red");
            }
        }
    }



    public void resetColors() {
        buttonA.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; -fx-font-size: 16px;");
        buttonB.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; -fx-font-size: 16px;");
        buttonC.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; -fx-font-size: 16px;");
        buttonD.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; -fx-font-size: 16px;");
        submitButton.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black; -fx-font-size: 16px;");
    }
}