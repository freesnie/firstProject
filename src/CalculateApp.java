import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
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
        Button settings = new Button("Налаштування");

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

        HBox.setMargin(calculate, new Insets(0, 0, 0, 5));
        HBox.setMargin(settings, new Insets(0, 0, 0, 0));

        VBox.setMargin(resultOfCalculation, new Insets(0, 5, 0, 5));

        HBox stationArea = new HBox(0);
        stationArea.getChildren().addAll(label1, textField1, label2, textField2,
                label3, textField3);

        HBox tankVolArea = new HBox(0);
        tankVolArea.getChildren().addAll(tankVolLabel, tankVolTextField);

        HBox buttonCalc = new HBox(0);
        buttonCalc.getChildren().addAll(calculate);
        HBox.setHgrow(buttonCalc, Priority.ALWAYS);

        HBox buttonSet = new HBox(0);
        HBox.setMargin(buttonSet, new Insets(0, 5, 0,0));
        buttonSet.getChildren().addAll(settings);

        HBox buttonArea = new HBox(0);
        buttonArea.getChildren().addAll(buttonCalc, buttonSet);


        VBox vBox = new VBox(10); // 10 - это расстояние между элементами
        vBox.getChildren().addAll(curLevLabel, stationArea, tankVolArea, buttonArea, resultOfCalculation);

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(vBox, 600, 400));
        //vBox.requestFocus();

        // Отображаем окно
        primaryStage.show();

        //GasStation.stations();

        for (GasStation station : GasStation.stations) {
            levelLabels[GasStation.stations.indexOf(station)].setText(station.getStationName() + ":");
        }

        calculate.setOnAction(event -> {

            resultOfCalculation.clear();

            for (GasStation station : GasStation.stations) {
                station.setCurrentLevel(Double.parseDouble(levelFields[GasStation.stations.indexOf(station)].getText())*100);
            }

            double tankVol = Double.parseDouble(tankVolTextField.getText());

            // Вызываем метод perMinute из класса Calculate и передаем нужные значения
            Calculate.perMinute(resultOfCalculation, tankVol);
        });

        settings.setOnAction(event -> settingsWindow());
    }

    public void settingsWindow() {

        Stage settingsStage = new Stage();
        settingsStage.setTitle("Settings");

        Button addObject = new Button("Добавить");
        VBox.setMargin(addObject, new Insets(0, 0, 0, 5));

        Label nameLabel = new Label("Название объекта:");
        HBox.setMargin(nameLabel, new Insets(10,2,0,5));

        TextField nameField = new TextField();
        HBox.setMargin(nameField, new Insets(5,5,0,0));
        nameField.setMaxWidth(100);

        HBox name = new HBox(0);
        name.getChildren().addAll(nameLabel, nameField);

        TextArea objInFile = new TextArea();
        objInFile.setEditable(false);
        VBox.setMargin(objInFile, new Insets(0, 5, 0, 5));

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(name, addObject, objInFile);

        settingsStage.setScene(new Scene(vBox, 400, 300));
        settingsStage.show();

        // кнопка добавляет обьекты в файл Stations.txt
        addObject.setOnAction(actionEvent -> {

            GasStation.stations.clear();

            objInFile.clear();

            GasStation newStation = new GasStation();
            newStation.setStationName(nameField.getText());
            WriteToFile.addToFile(newStation);

            WriteToFile.readFromFile();

            for (GasStation station : GasStation.stations) {
                objInFile.appendText(station.getStationName() + "\n");
            }
        });
    }
}