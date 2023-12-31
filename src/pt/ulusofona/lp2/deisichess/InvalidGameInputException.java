package pt.ulusofona.lp2.deisichess;

import java.io.IOException;

public class InvalidGameInputException extends IOException {

    public int getLineWithError() {
        return -1;
    }

    public String getProblemDescription() {
        return "erro";
    }
}
