package inw.golf;

import inw.golf.gamerules.IsolationKillsRule;
import inw.golf.gamerules.OvercrowdingKillsRule;
import inw.golf.gamerules.RebirthRule;
import inw.golf.gamerules.StayAliveRule;

import java.util.ArrayList;

public class Golf {

    public static void main(String[] args) throws InterruptedException {

        GameBoard pb = new GameBoard(100,30,0.14);
        ArrayList<GameBoard> boards = new ArrayList<GameBoard>();
        GameRound gr = new GameRound(
                new RebirthRule(),
                new IsolationKillsRule(),
                new StayAliveRule(),
                new OvercrowdingKillsRule());
        int rounds = 100;
        for(int round = 0; round <= rounds; round++) {
            boards.add(pb);
            pb = gr.Play(pb);
        }

        for(int round = 0; round <= rounds; round++)
        {
            Thread.sleep(100);
            System.out.print("\033[H\033[2J");
            System.out.print("Round ");
            System.out.println(round);
            PrintPlayBoard(boards.get(round));
        }
    }

    private static void PrintPlayBoard(GameBoard pb) {
        boolean[][] board = pb.getPlayBoard();
        Dimension dim = pb.dimension;
        for(int row = 0; row < dim.y; row++) {
            for(int col = 0; col < dim.x; col++) {
                boolean cell = board[col][row];
                System.out.print(cell ? "\u2593" : "\u2591");
            }
            System.out.println();
        }
    }

}
