package br.com.app.cm.model;

import br.com.app.cm.exception.ExplosionException;

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

    void toggleMark() {
        if (!open) {
            marked = !marked;
        }
    }

    boolean open() {
        if (!open && !marked) {
            open = true;

            if (mine) {
                throw new ExplosionException();
            }

            if (secureNeighborhood()) {
                neighbors.forEach(n -> n.open());
            }

            return true;
        } else {
            return false;
        }
    }

    boolean secureNeighborhood() {
        return neighbors.stream().noneMatch(n -> n.mine);
    }
}
