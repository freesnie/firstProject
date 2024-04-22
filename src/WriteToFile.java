import java.io.*;

public class WriteToFile {

    //запись обьекта
    public static void addToFile(GasStation newStation) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Stations.txt", true))) {
            out.writeObject(newStation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение обьектов
    public static void readFromFile() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Stations.txt"))) {
            int x = 1;
            while (true) {
                try {
                    System.out.println("Читаем " + x + "й " + "обьект..." );
                    GasStation station = (GasStation) in.readObject();
                    System.out.println(x + "й " + "обьект прочитан..." );
                    System.out.println("Добавляем " + x + "й " + "обьект в ArrayList..." );
                    GasStation.stations.add(station);
                    System.out.println(x + "й " + "обьект добавлен в ArrayList..." );
                    x++;
                } catch (EOFException e) {
                    break; // выходим из цикла, если достигнут конец файла
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // очистка файла
    public static void cleaningFile() {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Stations.txt"))) {
            // ничего не записываем, просто открываем и сразу закрываем поток
        } catch (IOException e) {
            e.getMessage();
        }
    }
}