package pt.ulusofona.lp2.deisichess;


public class Hint implements Comparable<Hint> {

    int x;
    int y;
    int points;

    public Hint(int x, int y, int points) {

        this.x = x;
        this.y = y;
        this.points = points;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ") -> " + points;
    }

    public int compareTo(Hint other) {

        if (this.points == other.points) {

            return 0;
        }
        if (this.points < other.points) {

            return 1;
        } else {

            return -1;
        }
    }
}
