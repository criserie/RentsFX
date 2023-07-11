/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rentsfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author crise
 */
public class RentsGUI extends Application{
    @Override
    public void start(Stage primaryStage) {
        //System.out.println("now in start");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("New Document", "Open ", new Separator(), "Save", "Save as"));
        
        
        //VBox root = new VBox();
        BorderPane root = new BorderPane();
        
        
        ObservableList myList = root.getChildren();
        System.out.println(myList);
        root.setTop(cb);
        //myList.add(btn);
        TextField myYears = new TextField("Years");
        TextField myRate = new TextField("Rate");
        root.setTop(btn);
        root.setCenter(myYears);
        root.setBottom(myRate);
        //System.out.println(myList);
        
 Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
