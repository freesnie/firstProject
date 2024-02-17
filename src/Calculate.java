import javafx.scene.control.TextArea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Calculate {

    //поминутный уровень
    public static void perMinute(final LocalDateTime startTime, DateTimeFormatter formatter, TextArea resultOfCalculation) {

        //String result = "";

        for (GasStations gasStation : GasStations.leftCoast) {

            LocalDateTime timeForCalc = startTime;
            double lvl = gasStation.currentLevel;

            while (gasStation.currentLevel > 0) {

                lvl -= GasStations.litresPerHour(timeForCalc) / 60;
                timeForCalc = timeForCalc.plusMinutes(1);
            }

            String result = timeForCalc.format(formatter) + " " + gasStation.name + " STOP";
            resultOfCalculation.appendText(result + "\n");
        }
    }
}