package br.com.app.cm.model;

import br.com.app.cm.exception.ExplosionException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

    private int lines;
    private int columns;
    private int mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        generateFields();
        mixNeighbors();
        drawMines();
    }

    public void open(int line, int column) {
        try {
            fields.parallelStream()
                    .filter(f -> f.getLine() == line && f.getColumn() == column)
                    .findFirst()
                    .ifPresent(f -> f.open());
        } catch (ExplosionException e) {
            fields.forEach(f -> f.setOpen(true));
            throw e;
        }
    }

    public void toggleMark(int line, int column) {
        fields.parallelStream()
                .filter(f -> f.getLine() == line && f.getColumn() == column)
                .findFirst()
                .ifPresent(f -> f.toggleMark());
    }

    private void generateFields() {
        for (int l = 0; l < lines; l++) {
            for (int c = 0; c < columns; c++) {
                fields.add(new Field(l, c));
            }
        }
    }

    private void mixNeighbors() {
        for (Field f1 : fields) {
            for (Field f2 : fields) {
                f1.addNeighbor(f2);
            }
        }
    }

    private void drawMines() {
        long armedMines = 0;
        Predicate<Field> isMine = f -> f.isMine();

        do {
            int random = (int) (Math.random() * fields.size());
            fields.get(random).setMine();
            armedMines = fields.stream().filter(isMine).count();
        } while (armedMines < mines);
    }

    public boolean goalAchieved() {
        return fields.stream().allMatch(f -> f.goalAchieved());
    }

    public void restart() {
        fields.stream().forEach(f -> f.restart());
        drawMines();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int c = 0; c < columns; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }

        sb.append("\n");

        int i = 0;
        for (int l = 0; l < lines; l++) {
            sb.append(l);
            sb.append(" ");
            for (int c = 0; c < columns; c++) {
                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
