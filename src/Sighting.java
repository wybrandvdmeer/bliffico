public class Sighting {

    private double chance;

    private Coordinate coordinate;

    public Sighting(double chance, Coordinate coordinate) {
        this.chance = chance;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getChance() {
        return chance;
    }

    public String toString() {
        return String.format("Sighting(chance: %f, coordinate: %s)", chance, coordinate);
    }
}


