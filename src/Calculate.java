import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class Calculate {
    
    //Метод принимает введенную дату ивремя либо берет текущую дату и время для начала рассчетов,
    //проверяет правильность ввода согласно нужного формата
    public static String[] timeForCalc(Scanner scanner, DateTimeFormatter formatter) {

        System.out.print("Відлік від поточного часу(1) або вказати початок відліку(2): ");

        String inputStartDateTime;

        if (scanner.nextLine().equals("1")) {
            //отсчет от текущей даты и времени
            inputStartDateTime = LocalDateTime.now().format(formatter);
            System.out.println("Поточний час: \"" + inputStartDateTime + "\"");
        } else {
            // или ввод первой даты и времени с проверкой на соответствие нужному формату
            System.out.print("Уведіть початкову дату та час у форматі \"dd-mm-yy hh:mm\": ");
            while (true) {
                inputStartDateTime = scanner.nextLine();
                try {
                    LocalDateTime.parse(inputStartDateTime, formatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.print("Початкові дата та час уведені у неправильному форматі, повторіть спробу: ");
                }
            }
        }
        // ввод второй даты и времени с проверкой на соответствие нужному формату
        System.out.print("Уведіть кінцеву дату та час у форматі \"dd-mm-yy hh:mm\": ");
        String inputEndDateTime;//Присвоить этой строке
        while (true) {
            inputEndDateTime = scanner.nextLine();
            try {
                LocalDateTime.parse(inputEndDateTime, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Кінцеві дата та час уведені у неправильному форматі, повторіть спробу: ");
            }
        }

        return new String[] {inputStartDateTime, inputEndDateTime};
    }
    //поминутный уровень
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        do {
            String[] userInput = timeForCalc(scanner, formatter);

            startDateTime = LocalDateTime.parse(userInput[0], formatter); //переводим текстовую строку в
            endDateTime = LocalDateTime.parse(userInput[1], formatter); //формат времени согласно форматтеру

            if (startDateTime.isAfter(endDateTime)) {
                System.out.println("Помилка! Початковий час відліку має йти перед кінцевим, а не після.");
            } else if (startDateTime.equals(endDateTime)) {
                System.out.println("Помилка! Початковий час не може дорівнювати кінцевому.");
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