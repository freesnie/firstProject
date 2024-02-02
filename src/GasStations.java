public class GasStations {
    String name;
    double currentLevel;
    double saleSpeed;
    double saleInTopHours;

    private GasStations(String name, double currentLevel, double saleSpeed, double saleInTopHours) {
       this.name = name;
       this.currentLevel = currentLevel;
       this.saleSpeed = saleSpeed;
       this.saleInTopHours = saleInTopHours;
    }

    static GasStations Krainia = new GasStations("Крайня", 0, 409, 634);

}

// gasStations[] leftCoast = {Krainia}; Shukhevycha, Bortnychi, Boryspilska, Hryhorenka, Zdolbunivska, Drahomanova, Azerbaidzhanska