package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.text.Text;

public class Vue{

    private Label mistakeLabel;
    private FlowPane gameFlowPane;
    private Pane paneGame;
    private Presentation presentation;
    private Stage fenetre;
    private Scene sceneGame;
    private Label scoreLabel;
    private ImageView currentImage;
    private int xScreen;
    private TextField textField;
    private Label answer;
    private Text time;

    public Vue(Presentation presentation){
        this.presentation = presentation;
        double xTextField = 400;
        textField = new TextField();

        //Screen size
        Screen screen = Screen.getPrimary();
        xScreen = (int) screen.getBounds().getWidth();
        int yScreen = (int) screen.getBounds().getHeight();

        //Game title
        Label titre = new Label("Quiz of what you want");
        titre.setStyle("-fx-font: 100 Verdana; -fx-text-fill: YELLOW; -fx-font-weight: bold;");
        titre.setPrefSize(xScreen,50);
        titre.setLayoutX(xScreen/6.5);
        titre.setLayoutY(yScreen /8.0);

        //Button start
        Button start = new Button("Start the Quiz");
        start.setStyle("-fx-font: 36 Verdana; -fx-font-weight: bold; -fx-color: GREEN");
        start.setId("start");
        start.setPrefSize(400,20);
        start.setLayoutX(xScreen/2.0-start.getPrefWidth()/2.0);
        start.setLayoutY(yScreen /2.0-start.getPrefHeight()/2.0);

        //Champ Text
        textField.setPrefSize(xTextField,xTextField/10);

        //Score label
        scoreLabel = new Label("Score : 0");
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-font: 36 Verdana;");

        //Mistake label
        mistakeLabel = new Label("Mistakes : 0");
        mistakeLabel.setStyle("-fx-font-weight: bold; -fx-font: 36 Verdana;");

        //Time
        time = new Text("0");
        time.setStyle("-fx-font-weight: bold; -fx-font: 36 Verdana;");
        time.setX(xScreen/10.0);
        time.setY(yScreen -140);

        Button showButton = new Button("Show answer");
        showButton.setStyle("-fx-font-weight: bold; -fx-font: 20 Verdana; -fx-color : RED");

        //Add in panes
        Pane paneMenu = new Pane();
        paneMenu.setStyle("-fx-background-color: #8A62C6;");
        paneMenu.getChildren().addAll(titre, start);

        paneGame = new Pane();
        paneGame.setStyle("-fx-background-color: #8A62C6;");

        gameFlowPane = new FlowPane();
        gameFlowPane.setHgap(40);
        FlowPane.setMargin(time, new Insets(10, 0, 10, 20));
        gameFlowPane.getChildren().addAll(time, textField, scoreLabel, mistakeLabel, showButton);

        BorderPane structure = new BorderPane();
        structure.setCenter(paneGame);
        structure.setTop(gameFlowPane);

        //Add in scenes
        Scene sceneMenu = new Scene(paneMenu, screen.getBounds().getWidth(), screen.getBounds().getHeight());
        sceneGame = new Scene(structure, screen.getBounds().getWidth(), screen.getBounds().getHeight());

        //Initialize stage
        fenetre = new Stage();
        fenetre.setTitle("Quiz of what you want");
        fenetre.setScene(sceneMenu);
        fenetre.setMaximized(true);
        fenetre.setResizable(true);
        fenetre.show();

        //Evenements
        textField.setOnAction(presentation);

        start.setOnAction(event -> {
            fenetre.setScene(sceneGame);
            presentation.initializeGame();
        });

        showButton.setOnAction(event -> {
            try {
                gameFlowPane.getChildren().add(answer);
                presentation.lostPoint();
            }catch (Exception ignored){}
        });
    }

    public ImageView createImageView(String path){
        final Image image = new Image(new File(path).toURI().toString());
        return new ImageView(image);
    }

    public void actualizeScore() {
        scoreLabel.setText("Score : "+presentation.getScore()+"/"+presentation.returnScoreMax());
        mistakeLabel.setText("Mistakes : "+presentation.getMistakes());
        textField.clear();
    }

    public void changeImageView() {
        paneGame.getChildren().remove(currentImage);
        String imagePath = presentation.randomImagePath();
        if (!imagePath.equals("Game Over")){
            setUpImageView(imagePath);
        } else {
            setUpEndText();
        }
        showAnswer(imagePath);
    }

    public void setUpImageView(String path){
        currentImage = createImageView(path);
        double xOrigin = currentImage.getBoundsInLocal().getWidth();
        double yOrigin = currentImage.getBoundsInLocal().getHeight();
        currentImage.setFitHeight(800);
        currentImage.setFitWidth(800*xOrigin/yOrigin);
        currentImage.setX(xScreen/2.0 - currentImage.getBoundsInLocal().getWidth()/2.0);
        currentImage.setY(55);
        paneGame.getChildren().add(currentImage);
    }

    public void setUpEndText(){
        Label gameOver = new Label("Congratulation !");
        gameOver.setStyle("-fx-font-weight: bold; -fx-font: 36 Verdana;");
        gameFlowPane.getChildren().add(gameOver);
    }

    public void showAnswer(String imagePath){
        gameFlowPane.getChildren().remove(answer);
        answer = new Label(imagePath.replace(".png","")
                                    .replace(".jpg","")
                                    .replace("/"," : ").toUpperCase());
        answer.setStyle("-fx-font-weight: bold; -fx-font: 24 Verdana;");
    }

    public void incrementTime(int second, int minute) {
        if (second < 10){
            this.time.setText(minute+":"+"0"+second);
        }else {
            this.time.setText(minute+":"+second);
        }
    }
}