package br.com.app.mw.view;

import br.com.app.mw.exception.ExitException;
import br.com.app.mw.exception.ExplosionException;
import br.com.app.mw.model.Board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleBoard {

    private Board board;
    private Scanner sc = new Scanner(System.in);

    public ConsoleBoard(Board board) {
        this.board = board;
        execGame();
    }

    private void execGame() {
        try {
            boolean continueGame = true;

            while (continueGame) {
                loopGame();

                System.out.println("Play another match? (Y/n) ");
                String answer = sc.nextLine();

                if ("n".equalsIgnoreCase(answer)) continueGame = false;
                else board.restart();
            }
        } catch (ExitException e) {
            System.out.println("Bye!");
        } finally {
            sc.close();
        }
    }

    private void loopGame() {

        try {
            while (!board.goalAchieved()) {
                System.out.println(board);

                String typed = captureValueTyped("Type (x, y): ");

                Iterator<Integer> xy = Arrays.stream(typed.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();

                typed = captureValueTyped("'1' - Open or '2' - Mark on/off: ");

                if ("1".equals(typed)) board.open(xy.next(), xy.next());
                else if ("2".equals(typed)) board.toggleMark(xy.next(), xy.next());
            }

            System.out.println(board);
            System.out.println("Win!!!!!");
        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("Game Over!");
        }
    }

    private String captureValueTyped(String text) {
        System.out.print(text);
        String typed = sc.nextLine();
        if ("exit".equalsIgnoreCase(typed)) throw new ExitException();

        return typed;
    }
}
