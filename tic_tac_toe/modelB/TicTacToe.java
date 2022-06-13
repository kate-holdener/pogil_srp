public class TicTacToe
{
   public static void main(String []args)
   {
      TicTacToeBoard board = new TicTacToeBoard();
      TicTacToeTerminal terminal = new TicTacToeTerminal(board);

      TicTacToePiece []playerTicTacToePieces = {TicTacToePiece.X,TicTacToePiece.O};

      int nextPlayer = 0;
      TicTacToePiece winner = null;
      AutoPlayer autoPlayer = new AutoPlayer(TicTacToePiece.X);

      while(!board.isFull())
      {
         autoPlayer.makeNextMove(board);
         terminal.showBoard();

         winner = board.getWinner();   
         if (board.isFull() || winner != null)
         {
            break;
         }
         System.out.println("Next Player");
         terminal.manualMove(playerTicTacToePieces[1]);
         terminal.showBoard();

         winner = board.getWinner();   
         if (board.isFull() || winner != null)
         {
            break;
         }
         System.out.println("Next Player");
      }

      if (winner != null)
      {
         System.out.println("Winner is " + winner);
      }
   }
}
