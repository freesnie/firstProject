import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
public class GasStations {
    String name;
    double currentLevel;
    //Label currentLevelLabel;
    //TextField currentLevelTextField;

    private GasStations(String name, double currentLevel) {
       this.name = name;
       this.currentLevel = currentLevel;
    }

    static GasStations Krainia = new GasStations("Крайня", 0);
    static GasStations Shukhevycha = new GasStations("Шухевича", 0);
    static GasStations Azerbaidzhanska = new GasStations("Азербайджанська", 0);
    static GasStations[] leftCoast = {Krainia, Shukhevycha, Azerbaidzhanska};

    public static double litresPerHour(LocalDateTime timeForCalc, String name) {
        if (name.equals("Крайня")) return switch (timeForCalc.getHour()) {
                case 5, 22 -> 49;
                case 6 -> 113;
                case 7 -> 229;
                case 8 -> 322;
                case 9, 18 -> 338;
                case 10 -> 335;
                case 11 -> 218;
                case 12 -> 341;
                case 13 -> 309;
                case 14 -> 265;
                case 15 -> 248;
                case 16 -> 346;
                case 17 -> 350;
                case 19 -> 281;
                case 20 -> 147;
                case 21 -> 34;
                case 23 -> 15;
                default -> 0;
            };
        else if (name.equals("Шухевича")) return switch (timeForCalc.getHour()) {
                case 5 -> 45;
                case 6 -> 208;
                case 7 -> 385;
                case 8 -> 428;
                case 9 -> 332;
                case 10 -> 458;
                case 11 -> 313;
                case 12 -> 425;
                case 13 -> 456;
                case 14 -> 379;
                case 15 -> 451;
                case 16 -> 441;
                case 17 -> 498;
                case 18 -> 744;
                case 19 -> 532;
                case 20 -> 335;
                case 21 -> 275;
                case 22 -> 159;
                case 23 -> 144;
                default -> 0;
            };
        else return switch (timeForCalc.getHour()) {
                case 5 -> 41;
                case 6 -> 219;
                case 7 -> 305;
                case 8 -> 445;
                case 9 -> 495;
                case 10 -> 509;
                case 11 -> 393;
                case 12 -> 453;
                case 13 -> 513;
                case 14 -> 501;
                case 15 -> 442;
                case 16 -> 439;
                case 17 -> 537;
                case 18 -> 528;
                case 19 -> 280;
                case 20 -> 259;
                case 21 -> 292;
                case 22 -> 126;
                case 23 -> 78;
                default -> 0;
            }; //Азербайджанская
    }
}

//gasStations[] leftCoast = {Krainia}; Shukhevycha, Bortnychi, Boryspilska, Hryhorenka, Zdolbunivska, Drahomanova, Azerbaidzhanska