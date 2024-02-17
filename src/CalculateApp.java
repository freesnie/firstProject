import javafx.application.Application;
import javafx.scene.control.TextArea;
//import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculateApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("CalculateApp");

        // Создаем кнопку "Рассчитать" и другие элементы управления
        Button calculate = new Button("Розрахувати");

        //Label endTimeLabel = new Label("Уведіть кінцевий час розрахунку у форматі \"дд-мм-рр гг:хх\":");
        Label currentLevelLabel = new Label("Уведіть залишок на АЗС " + GasStations.Krainia.name + " у %:");

        //TextField inputFieldEndTime = new TextField();
        TextField inputFieldCurrentLevel = new TextField();

        TextArea resultOfCalculation = new TextArea();
        resultOfCalculation.setEditable(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        //VBox.setMargin(endTimeLabel, new Insets(10, 5, 0, 5));
        //VBox.setMargin(inputFieldEndTime, new Insets(0, 5, 10, 5));
        VBox.setMargin(currentLevelLabel, new Insets(0, 5, 0, 5));
        VBox.setMargin(inputFieldCurrentLevel, new Insets(0, 5, 10, 5));
        VBox.setMargin(calculate, new Insets(0, 0, 0, 5));
        VBox.setMargin(resultOfCalculation, new Insets(10, 5, 0, 5));

        VBox root = new VBox(5); // 10 - это расстояние между элементами
        root.getChildren().addAll(currentLevelLabel, inputFieldCurrentLevel,
                calculate, resultOfCalculation);

        calculate.setOnAction(event -> {
            resultOfCalculation.clear();
            final LocalDateTime startTime = LocalDateTime.now();
            //final LocalDateTime startTime = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
            //LocalDateTime endTime = LocalDateTime.parse(inputFieldEndTime.getText(), formatter);
            GasStations.Krainia.currentLevel = Integer.parseInt(inputFieldCurrentLevel.getText())*100;

            // Вызываем метод perMinute из класса Calculate и передаем нужные значения
            Calculate.perMinute(startTime, formatter, resultOfCalculation);
            //String result = Calculate.perMinute(startTime, endTime, formatter, resultOfCalculation);
            //resultOfCalculation.setText(result);
        });

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(root, 600, 400));

        // Отображаем окно
        primaryStage.show();
    }

}