import java.io.*;

public class WriteToFile {

    static FileOutputStream fileOut;
    static ObjectOutputStream out;
    static FileInputStream fileIn;
    static ObjectInputStream in;

    static {
        try {
            fileOut = new FileOutputStream("Stations.txt", true);
            out = new ObjectOutputStream(fileOut);
            fileIn = new FileInputStream("Stations.txt");
            in = new ObjectInputStream(fileIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //запись обьекта
    public static void addToFile(GasStation newStation) {

        try  {
            out.writeObject(newStation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //чтение обьектов
    public static void readFromFile() {

        try {
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
                    System.out.println("Достигнут конец файла...");
                    break; // выходим из цикла, если достигнут конец файла
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    // закрытие потоков
//    public static void closeStreams() {
//
//        try {
//            if (out != null) out.close();
//            if (fileOut != null) fileOut.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    // очистка файла
//    public static void cleaningFile() {
//
//        try (ObjectOutputStream cleaningStream = new ObjectOutputStream(new FileOutputStream("Stations.txt"))) {
//            // ничего не записываем, просто открываем и сразу закрываем поток
//        } catch (IOException e) {
//            e.getMessage();
//        }
//    }
}