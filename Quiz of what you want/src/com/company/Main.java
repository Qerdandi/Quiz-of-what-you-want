package com.company;

public class Main {

    public static void main(String[] strings)
    {
        Modele modele = new Modele();
        Presentation presentation = new Presentation(modele);
        Vue vue = new Vue(presentation);
        presentation.associerVue(vue);
    }
}
