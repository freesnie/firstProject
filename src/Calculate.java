import javafx.scene.control.TextArea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculate {

    //поминутный уровень
    public static void perMinute(TextArea resultOfCalculation, double tankVol) {

        List<String> lines = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        for (GasStations gasStation : GasStations.leftCoast) {

            LocalDateTime timeForCalc = LocalDateTime.now();
            double lvl = gasStation.currentLevel;
            String name = gasStation.name;

            while (lvl > 0) {

                lvl -= GasStations.litresPerHour(timeForCalc, name) / 60;
                tankVol -= GasStations.litresPerHour(timeForCalc, name) / 60;
                timeForCalc = timeForCalc.plusMinutes(1);
            }

            lines.add(timeForCalc.format(formatter) + " " + gasStation.name + " STOP");
        }

        Collections.sort(lines);
        lines.add("");
        lines.add("Залишок у цистерні: " + Math.round(tankVol) + "л");

        // Выводим отсортированные строки
        for (String line : lines) {
            resultOfCalculation.appendText(line + "\n");
        }

    }
}