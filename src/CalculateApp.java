import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

public class CalculateApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("CalculateApp");

        // Создаем кнопку "Рассчитать" и другие элементы управления
        Button calculate = new Button("Розрахувати");

        Label curLevLabel = new Label("Уведіть залишок у %:");
        Label tankVolLabel = new Label("Газу у цистерні, л:");

        Label label1 = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label[] levelLabels = {label1, label2, label3};

        TextField textField1 = new TextField();
        textField1.setMaxWidth(30);

        TextField textField2 = new TextField();
        textField2.setMaxWidth(30);

        TextField textField3 = new TextField();
        textField3.setMaxWidth(30);

        TextField[] levelFields = {textField1, textField2, textField3};

        TextField tankVolTextField = new TextField();
        tankVolTextField.setMaxWidth(50);

        TextArea resultOfCalculation = new TextArea();
        resultOfCalculation.setEditable(false);

        VBox.setMargin(curLevLabel, new Insets(5, 5, 0, 5));

        HBox.setMargin(label1, new Insets(5, 2, 0, 5));
        HBox.setMargin(textField1, new Insets(0, 5, 0, 0));

        HBox.setMargin(label2, new Insets(5, 2, 0, 10));
        HBox.setMargin(textField2, new Insets(0, 5, 0, 0));

        HBox.setMargin(label3, new Insets(5, 2, 0, 10));
        HBox.setMargin(textField3, new Insets(0, 5, 0, 0));

        HBox.setMargin(tankVolLabel, new Insets(5, 2, 0, 5));
        HBox.setMargin(tankVolTextField, new Insets(0, 5, 0, 0));

        VBox.setMargin(calculate, new Insets(0, 0, 0, 5));
        VBox.setMargin(resultOfCalculation, new Insets(0, 5, 0, 5));

        HBox stationArea = new HBox(0);
        stationArea.getChildren().addAll(label1, textField1, label2, textField2,
                label3, textField3);

        HBox tankVolArea = new HBox(0);
        tankVolArea.getChildren().addAll(tankVolLabel, tankVolTextField);

        VBox vBox = new VBox(10); // 5 - это расстояние между элементами
        vBox.getChildren().addAll(curLevLabel, stationArea, tankVolArea, calculate, resultOfCalculation);

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(vBox, 600, 400));
        //vBox.requestFocus();

        // Отображаем окно
        primaryStage.show();

        GasStation.stations();

        for (GasStation gasStation : GasStation.indexes) {
            levelLabels[GasStation.indexes.indexOf(gasStation)].setText(gasStation.getStationName() + ":");
        }

        calculate.setOnAction(event -> {

            resultOfCalculation.clear();

            for (GasStation gasStation : GasStation.indexes) {
                gasStation.setCurrentLevel(Double.parseDouble(levelFields[GasStation.indexes.indexOf(gasStation)].getText())*100);
            }

            double tankVol = Double.parseDouble(tankVolTextField.getText());

            // Вызываем метод perMinute из класса Calculate и передаем нужные значения
            Calculate.perMinute(resultOfCalculation, tankVol);
        });
    }
}