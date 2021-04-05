package com.company;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class Presentation implements EventHandler<ActionEvent> {
    private Modele modele;
    private Vue vue;
    private AnimationTimer animationTimer;
    private int timeValue;
    private int second;
    private int minute;

    public Presentation(Modele modele){
        second = 0;
        minute = 0;
        this.modele = modele;
    }

    public void handle(ActionEvent event){
        TextField textField = (TextField) event.getSource();

        if (!textField.getText().equalsIgnoreCase("")){
            if(modele.validateEntry(textField.getText())){
                vue.actualizeScore();
                vue.changeImageView();
            }else{
                vue.actualizeScore();
            }
        }
    }

    public void timeStart(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeValue++;
                if(timeValue % 60 == 1) {
                    second++;
                    if (second == 60){
                        second = 0;
                        minute++;
                    }
                    vue.incrementTime(second, minute);
                }
            }
        };
        animationTimer.start();
    }

    public int getScore(){ return modele.getScore(); }

    public void associerVue(Vue vue){ this.vue = vue; }

    public String randomImagePath() {
        String imagePath = modele.randomImagePath();
        if (imagePath.equals("Game Over")){
            animationTimer.stop();
        }
        return imagePath;
    }

    public void initializeGame(){
        String imagePath = modele.randomImagePath();
        timeStart();
        vue.setUpImageView(imagePath);
        vue.showAnswer(imagePath);
    }

    public void lostPoint() { modele.decrementScore(); }

    public int returnScoreMax(){ return modele.returnScoreMax(); }

    public int getMistakes() { return modele.getMistakes(); }
}
