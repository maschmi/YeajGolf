package inw.golf;

public class Golf {

    public static void main(String[] args) {
        PlayBoard pb = new PlayBoard(10,10,1);
        PrintPlayBoard(pb);
    }

    private static void PrintPlayBoard(PlayBoard pb) {
        boolean[][] board = pb.getPlayBoard();
        for (boolean[] row: board) {
            for(boolean cell : row) {
                System.out.print(cell);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
