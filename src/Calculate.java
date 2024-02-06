import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Duration;
//import java.time.format.DateTimeParseException;

public class Calculate {

    //поминутный уровень
    public static void perMinute(String inputStartDateTime, String inputEndDateTime, DateTimeFormatter formatter) {

        Scanner scanner = new Scanner(System.in);

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        do {

            startDateTime = LocalDateTime.parse(inputStartDateTime, formatter); //переводим текстовую строку в
            endDateTime = LocalDateTime.parse(inputEndDateTime, formatter); //обьект LocalDateTime согласно форматтеру

            if (startDateTime.isAfter(endDateTime)) {
                System.out.println("Помилка! Кінцевий час має йти після початкового, а не перед.");
            } else if (startDateTime.equals(endDateTime)) {
                System.out.println("Помилка! Кінцевий час не може бути однаковим з початковим.");
            }
        } while (startDateTime.isAfter(endDateTime) || startDateTime.equals(endDateTime));

        Duration difference = Duration.between(startDateTime, endDateTime); //разница во времени
        long minutesDifference = difference.toMinutes();//перевод разницы в мин

        int hours = (int) (minutesDifference / 60); //в часах
        int minutes = (int) (minutesDifference % 60); //в минутах
        System.out.println("Різниця: " + hours + " год " + minutes + " хв");

        System.out.print("Введіть залишок на АЗС " + GasStations.Krainia.name + " у %: ");
        GasStations.Krainia.currentLevel = scanner.nextInt();

        while (!startDateTime.isEqual(endDateTime) && GasStations.Krainia.currentLevel >= 0) {

            double previousLevel = GasStations.Krainia.currentLevel;

            if (startDateTime.getHour() == 8 || startDateTime.getHour() == 9 || startDateTime.getHour() == 18
                    || startDateTime.getHour() == 19) {
                GasStations.Krainia.currentLevel -= GasStations.Krainia.saleInTopHours / 100 / 60;
            } else if (startDateTime.getHour() >= 0 && startDateTime.getHour() <= 4) {
                continue;//в коменд час уровень остается неизменным
            } else {
                GasStations.Krainia.currentLevel -= GasStations.Krainia.saleSpeed / 100 / 60;
            }

            startDateTime = startDateTime.plusMinutes(1);

            if ((int) GasStations.Krainia.currentLevel != (int) previousLevel) {
                if ((int) GasStations.Krainia.currentLevel != 0) {
                    System.out.println(startDateTime.format(formatter) + " " + (int) GasStations.Krainia.currentLevel + "%");
                } else {
                    System.out.println(startDateTime.format(formatter) + " " + GasStations.Krainia.name + " STOP");
                }
            }
        }
    }
}