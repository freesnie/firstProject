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
        Label curLevLabelKrainia = new Label("Крайня:");
        Label curLevLabelShukhevycha = new Label("Шухевича:");
        Label curLevLabelAzerbaidzhanska = new Label("Азербайджанська:");

        TextField curLevKrainia = new TextField();
        curLevKrainia.setMaxWidth(30);
        //curLevKrainia.setText("");

        TextField curLevShukhevycha = new TextField();
        curLevShukhevycha.setMaxWidth(30);
        //curLevShukhevycha.setText("");

        TextField curLevAzerbaidzhanska = new TextField();
        curLevAzerbaidzhanska.setMaxWidth(30);
        //curLevAzerbaidzhanska.setText("");
        //String[] curLevFields = {String.valueOf(curLevKrainia), String.valueOf(curLevShukhevycha), String.valueOf(curLevAzerbaidzhanska)};

        TextField tankVolTextField = new TextField();
        tankVolTextField.setMaxWidth(50);

        TextArea resultOfCalculation = new TextArea();
        resultOfCalculation.setEditable(false);

        VBox.setMargin(curLevLabel, new Insets(5, 5, 0, 5));

        HBox.setMargin(curLevLabelKrainia, new Insets(5, 2, 0, 5));
        HBox.setMargin(curLevKrainia, new Insets(0, 5, 0, 0));

        HBox.setMargin(curLevLabelShukhevycha, new Insets(5, 2, 0, 10));
        HBox.setMargin(curLevShukhevycha, new Insets(0, 5, 0, 0));

        HBox.setMargin(curLevLabelAzerbaidzhanska, new Insets(5, 2, 0, 10));
        HBox.setMargin(curLevAzerbaidzhanska, new Insets(0, 5, 0, 0));

        HBox.setMargin(tankVolLabel, new Insets(5, 2, 0, 5));
        HBox.setMargin(tankVolTextField, new Insets(0, 5, 0, 0));

        VBox.setMargin(calculate, new Insets(0, 0, 0, 5));
        VBox.setMargin(resultOfCalculation, new Insets(0, 5, 0, 5));

        HBox stationArea = new HBox(0);
        stationArea.getChildren().addAll(curLevLabelKrainia, curLevKrainia, curLevLabelShukhevycha, curLevShukhevycha,
                curLevLabelAzerbaidzhanska, curLevAzerbaidzhanska);

        HBox tankVolArea = new HBox(0);
        tankVolArea.getChildren().addAll(tankVolLabel, tankVolTextField);

        VBox vBox = new VBox(10); // 5 - это расстояние между элементами
        vBox.getChildren().addAll(curLevLabel, stationArea, tankVolArea, calculate, resultOfCalculation);

        calculate.setOnAction(event -> {

            resultOfCalculation.clear();
/*
            for (int i = 0; i < GasStations.leftCoast.length; i++) {
                GasStations.leftCoast[i].currentLevel = CalculateApp.getCurLevValue(GasStations.leftCoast[i].name);
            }
*/
            GasStations.Krainia.currentLevel = Integer.parseInt(curLevKrainia.getText())*100;
            GasStations.Shukhevycha.currentLevel = Integer.parseInt(curLevShukhevycha.getText())*100;
            GasStations.Azerbaidzhanska.currentLevel = Integer.parseInt(curLevAzerbaidzhanska.getText())*100;

            double tankVol = Double.parseDouble(tankVolTextField.getText());

            // Вызываем метод perMinute из класса Calculate и передаем нужные значения
            Calculate.perMinute(resultOfCalculation, tankVol);
        });

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(vBox, 600, 400));
        //vBox.requestFocus();

        // Отображаем окно
        primaryStage.show();
    }
/*
    public static Integer getCurLevValue(String name) {
        return switch(name) {
            case "Крайня" -> Integer.parseInt(curLevKrainia.getText())*100;
            case "Шухевича" -> Integer.parseInt(curLevShukhevycha.getText())*100;
            case "Азербайджанська" -> Integer.parseInt(curLevAzerbaidzhanska.getText())*100;
        };
    }
*/
}