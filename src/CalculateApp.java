import javafx.application.Application;
import javafx.scene.text.Text;
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
        Label label = new Label("Уведіть кінцевий час розрахунку:");
        TextField inputField = new TextField();
        Text resultOfCalculation = new Text();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        VBox.setMargin(label, new Insets(10, 0, 0, 0));
        VBox.setMargin(inputField, new Insets(0, 0, 10, 0));

        VBox root = new VBox(10); // 10 - это расстояние между элементами
        root.getChildren().addAll(label, inputField, calculate, resultOfCalculation);

        calculate.setOnAction(event -> {
            String inputStartDateTime = LocalDateTime.now().format(formatter);
            String inputEndDateTime = inputField.getText();

            // Вызываем метод perMinute из класса Calculate и передаем нужные значения
            Calculate.perMinute(inputStartDateTime, inputEndDateTime, formatter);
        });

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(root, 600, 400));

        // Отображаем окно
        primaryStage.show();
    }

}