import java.time.LocalDateTime;

public class GasStations {
    String name;
    double currentLevel;
    double saleSpeed;

    private GasStations(String name, double currentLevel, double saleSpeed) {
       this.name = name;
       this.currentLevel = currentLevel;
       this.saleSpeed = saleSpeed;
    }


    static GasStations Krainia = new GasStations("Крайня", 0, 457);
    public static double litresPerHour(LocalDateTime timeForCalc) {
        return switch (timeForCalc.getHour()) {
            case 5 -> 49;
            case 6 -> 113;
            case 7 -> 229;
            case 8 -> 322;
            case 9 -> 338;
            case 10 -> 335;
            case 11 -> 218;
            case 12 -> 341;
            case 13 -> 309;
            case 14 -> 265;
            case 15 -> 248;
            case 16 -> 346;
            case 17 -> 350;
            case 18 -> 338;
            case 19 -> 281;
            case 20 -> 147;
            case 21 -> 34;
            case 22 -> 49;
            case 23 -> 15;
            default -> 0;
        };
    }
    //static GasStations Shukhevycha = new GasStations("Шухевича", 2, 761);
    //static GasStations Azerbaidzhanska = new GasStations("Азербайджанська", 3, 893);

    static GasStations[] leftCoast = {Krainia};
}

//gasStations[] leftCoast = {Krainia}; Shukhevycha, Bortnychi, Boryspilska, Hryhorenka, Zdolbunivska, Drahomanova, Azerbaidzhanska