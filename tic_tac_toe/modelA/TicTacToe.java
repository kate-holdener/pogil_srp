import java.util.Scanner;

public class TicTacToe
{
   private int size;
   private TicTacToePiece [][]pieces;

   public TicTacToe()
   {
      this.size = 3;
      this.initTicTacToe();
   }

   private void initTicTacToe()
   {
      this.pieces = new TicTacToePiece[this.size][this.size];
   }
 
   public TicTacToePiece getTicTacToePiece(int row, int col)
   {
      return this.pieces[row][col];
   }

   public boolean placeTicTacToePiece(int row, int col, TicTacToePiece piece)
   {
      boolean success = false;
      if (this.pieces[row][col] == null)
      {
         this.pieces[row][col] = piece;
         success = true;
      }
      return success;
   }

   public boolean isFull()
   {
      for (int i = 0; i < this.size; i++)
      {
         for (int j = 0; j < this.size; j++)
         {
            if (this.pieces[i][j] == null)
            {
               return false;
            }
         }
      }
      return true;
   }

   public TicTacToePiece getWinner()
   {
      TicTacToePiece winner = getRowWinner();
      if (winner == null)
      {
         winner = getColumnWinner();
      }
      if (winner == null)
      {
         winner = getDiagonalWinner();
      }
      return winner;
   }

   private TicTacToePiece getRowWinner()
   {
      TicTacToePiece rowWinner = null;
      for(int i = 0; i < this.size; i++)
      {
         rowWinner = this.pieces[i][0];
         for (int j = 0; j < this.size; j++)
         {
            if (this.pieces[i][j] != rowWinner)
            {
               rowWinner = null;
               break;
            }
         }
         if (rowWinner != null)
         {
            break;
         }
      }
      return rowWinner;
   }

   private TicTacToePiece getColumnWinner()
   {
      TicTacToePiece columnWinner = null;
      for (int j = 0; j < this.size; j++)
      {
         columnWinner = this.pieces[0][j];
         for (int i = 0; i < this.size; i++)
         {
            if(this.pieces[i][j] != columnWinner)
            {
               columnWinner = null;
            }
         }
         if (columnWinner != null)
         {
            break;
         }
      }
      return columnWinner;
   }

   private TicTacToePiece getDiagonalWinner()
   {
      TicTacToePiece diagonalWinner = this.pieces[0][0];
     
      // check the top left to bottom right diagonal
      for (int i = 0; i < this.size; i++)
      {
         if (this.pieces[i][i] != diagonalWinner)
         {
            diagonalWinner = null;
         }
      }
      if (diagonalWinner != null)
      {
         return diagonalWinner;
      }

      // check the top right to bottom left diagonal
      diagonalWinner = this.pieces[0][this.size-1];
      for (int i = 0; i < this.size; i++)
      {
         if (this.pieces[i][this.size-1-i] != diagonalWinner)
         {
            diagonalWinner = null;
         }
      }
      return diagonalWinner;
   }

   public boolean autoPlace(TicTacToePiece piece)
   {
      for (int i = 0; i < this.size; i++)
      {
         for (int j = 0; j < this.size; j++)
         {
            if(this.placeTicTacToePiece(i,j,piece))
            {
               return true;
            }
         }
      }
      return false;
   }

   public void showTicTacToePieces()
   {
      for (int i = 0; i < this.size; i++)
      {
         String line = "";
         for (int j = 0; j < this.size; j++)
         {
            TicTacToePiece piece = this.getTicTacToePiece(i,j);
            if (piece == null)
            {
               line+="-";
            }
            else
            {
               line+=piece;
            }
         }
         System.out.println(line);
      }
   }
 
   public void manualMove(Scanner scanner, TicTacToePiece piece)
   {
      int row = 0;
      int col = 0;
      do
      {
         System.out.println("Enter row: ");
         row = scanner.nextInt();
         System.out.println("Enter colunn: ");
         col = scanner.nextInt();
      }while (!this.placeTicTacToePiece(row, col, piece));
   }

   public static void main(String []args)
   {
      Scanner scanner = new Scanner(System.in);
      TicTacToe board = new TicTacToe();
      TicTacToePiece []playerTicTacToePieces = {TicTacToePiece.X,TicTacToePiece.O};
      int nextPlayer = 0;
      TicTacToePiece winner = null;

      while(!board.isFull())
      {
         board.autoPlace(playerTicTacToePieces[0]);
         board.showTicTacToePieces();

         winner = board.getWinner();   
         if (board.isFull() || winner != null)
         {
            break;
         }
         System.out.println("Next Player");
         board.manualMove(scanner, playerTicTacToePieces[1]);
         board.showTicTacToePieces();

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
