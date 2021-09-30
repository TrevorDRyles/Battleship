/*
 * Trevor Ryles P3 Java 6/8/18
 */
import java.util.Scanner;
public class BattleShip
{
        Ship[] playerShips ={new Ship(2),new Ship(3),
        new Ship(3),new Ship(4),new Ship(5)};
        Ship[] player2Ships ={new Ship(2),new Ship(3),
        new Ship(3),new Ship(4),new Ship(5)};
        Player player = new Player();
        Player player2 = new Player();
        int playerRowGuess;
        int playerColGuess;
        int player2RowGuess;
        int player2ColGuess;
        Scanner scan = new Scanner(System.in);
        public boolean addP1(int i, int row, int col, int dir) {
            boolean a = player.chooseShipLocation(playerShips[i],row-1,col-1,dir);
            return a;
        }
        public boolean addP2(int i, int row, int col, int dir) {
            boolean b = player2.chooseShipLocation(player2Ships[i],row-1,col-1,dir);
            return b;
        }
        public Player getP1() {
        	return player;
        }
        public Player getP2() {
        	return player2;
        }
}