package Chess;

import Chess.Gui.ChessTable;
import Chess.Gui.StartPage;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        ChessTable table = new ChessTable(true);
        StartPage start = new StartPage(table);
    }
}
