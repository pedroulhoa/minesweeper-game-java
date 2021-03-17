package br.com.app.cm;

import br.com.app.cm.model.Board;
import br.com.app.cm.view.ConsoleBoard;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(6, 6, 3);
        new ConsoleBoard(board);
    }
}
