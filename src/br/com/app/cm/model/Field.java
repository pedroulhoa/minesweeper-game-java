package br.com.app.cm.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private boolean mine = false;
    private boolean open = false;
    private boolean marked = false;

    private final int line;
    private final int column;

    private List<Field> neighbors = new ArrayList<>();

    Field(int line, int column) {
        this.line = line;
        this.column = column;
    }

    boolean addNeighbor(Field neighbor) {
        boolean differentLine = line != neighbor.line;
        boolean differentColumn = column != neighbor.column;
        boolean diagonal = differentLine && differentColumn;

        int deltaLine = Math.abs(line - neighbor.line);
        int deltaColumn = Math.abs(column - neighbor.column);
        int deltaGeneral = deltaColumn + deltaLine;

        if (deltaGeneral == 1 && !diagonal) {
            neighbors.add(neighbor);
            return true;
        } else if (deltaGeneral == 2 && diagonal) {
            neighbors.add(neighbor);
            return true;
        } else {
            return false;
        }
    }
}
