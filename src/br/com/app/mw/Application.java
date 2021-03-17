package br.com.app.mw;

import br.com.app.mw.model.Board;
import br.com.app.mw.view.ConsoleBoard;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(6, 6, 3);
        new ConsoleBoard(board);
    }
}
