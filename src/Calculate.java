import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Calculate {

    //поминутный уровень
    public static String perMinute(String inputStartDateTime, String inputEndDateTime, DateTimeFormatter formatter) {

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String result = "";

        do {
            startDateTime = LocalDateTime.parse(inputStartDateTime, formatter); //переводим текстовую строку в
            endDateTime = LocalDateTime.parse(inputEndDateTime, formatter); //обьект LocalDateTime согласно форматтеру

            if (startDateTime.isAfter(endDateTime)) {
                System.out.println("Помилка! Кінцевий час має йти після початкового, а не перед.");
            } else if (startDateTime.equals(endDateTime)) {
                System.out.println("Помилка! Кінцевий час не може бути однаковим з початковим.");
            }
        } while (startDateTime.isAfter(endDateTime) || startDateTime.equals(endDateTime));

        while (!startDateTime.isEqual(endDateTime) && GasStations.Krainia.currentLevel >= 0) {

            double previousLevel = GasStations.Krainia.currentLevel;

            if (startDateTime.getHour() == 8 || startDateTime.getHour() == 9 || startDateTime.getHour() == 18
                    || startDateTime.getHour() == 19) {
                GasStations.Krainia.currentLevel -= GasStations.Krainia.saleInTopHours / 100 / 60;
            } else if (startDateTime.getHour() >= 0 && startDateTime.getHour() <= 4) {
                continue;//в комендантский час уровень остается неизменным
            } else {
                GasStations.Krainia.currentLevel -= GasStations.Krainia.saleSpeed / 100 / 60;
            }

            startDateTime = startDateTime.plusMinutes(1);

            if ((int) GasStations.Krainia.currentLevel != (int) previousLevel) {
                if ((int) GasStations.Krainia.currentLevel != 0) {
                    result = startDateTime.format(formatter) + " " + (int) GasStations.Krainia.currentLevel + "%";
                } else {
                    result = startDateTime.format(formatter) + " " + GasStations.Krainia.name + " STOP";
                }
            }
        }
        return result;
    }
}