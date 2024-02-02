import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        Label label = new Label("Введите данные:");
        TextField inputField = new TextField();

        VBox.setMargin(label, new Insets(10, 0, 0, 0));
        VBox.setMargin(inputField, new Insets(0, 0, 10, 0));

        VBox root = new VBox(10); // 10 - это расстояние между элементами
        root.getChildren().addAll(label, inputField, calculate);

        calculate.setOnAction(event -> {
            String inputEndDayTime = inputField.getText();//Вот эту строку по нажатию кнопки с содержимым поля ввода
            //передать в Calculate, метод timeForCalc и присвоить для inputEndDateTime

            //Calculate.timeForCalc(inputField.getText());
            // Вызываем метод main из класса Calculate
            Calculate.main(new String[]{});
        });

        // Создаем сцену с макетом и устанавливаем ее для primaryStage
        primaryStage.setScene(new Scene(root, 600, 400));

        // Отображаем окно
        primaryStage.show();
    }

}