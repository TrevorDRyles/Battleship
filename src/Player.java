/*
 * Trevor Ryles P3 Java 6/8/18
 */
import java.util.*;
public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
private ArrayList<Ship> opponentList;
private Grid opponentGrid;
private int numSunk = 0;
public Player(){
         opponentList= new ArrayList<Ship>();
         opponentGrid =  new Grid();
}
public Grid getGrid() {
	return opponentGrid;
}
public void askForGuess(int row, int col){
        recordOpponentGuess(row,col);
}
public boolean allHit(){
    return numSunk>4;
}
public int getNumSunk() {
	return numSunk;
}
public void loc(){
        char x = 'A';
    System.out.println("Player's cords:");
    for (int row=0;row<opponentGrid.numRows();row++){
        for (int col=0;col<opponentGrid.numCols();col++){
            if(opponentGrid.hasShip(row,col)){
                System.out.println("Ship at: " + (col+1) + ", " + (char)(x+row));
            }
        }
    }
}
  public int getShipLength(){
      return SHIP_LENGTHS.length;
  }

public boolean chooseShipLocation(Ship s, int row, int col, int direction){
        s.setDirection(direction);
        s.setLocation(row,col);
        if(direction==0){
//        	System.out.println("Row: " + row + " Col: " + col + " Dir: " + direction);
        	try {
	        	for(int i=0;i<s.getLength();i++){
	                if(opponentGrid.hasShip(row, col+i)) {
//	                	System.out.println("There is a ship in: " +row + ", " +(col+i));
	                	return false;
	                }
	        	}
        	}catch(Exception e) {
        		return false;
        	}
            for(int i=0;i<s.getLength();i++){
                 opponentGrid.addShip(s);
            }
        }else if(direction==1){
        	try {
	        	for(int i=0;i<s.getLength();i++){
	                if(opponentGrid.hasShip(row+i, col)) {
	                	System.out.println("There is a ship in: " +(row+i) + ", " + col);
	                	return false;
	                }
	        	}
        	}catch(Exception e) {
        		return false;
        	}
            for(int i=0;i<s.getLength();i++){
                if(opponentGrid.hasShip(row, col)) {
                	return false;
                }
            }
            for(int i=0;i<s.getLength();i++){
                opponentGrid.addShip(s);
            }
        }
        opponentList.add(s);
        return true;
}
public void recordOpponentGuess(int a, int b){
    if(opponentGrid.hasShip(a,b)){
        if(!opponentGrid.get(a,b).isSunk()){
            opponentGrid.markHit(a,b);
        }
        System.out.println("Hit");
        Ship shipHolder = opponentList.get(0);
        Ship perm = opponentList.get(0);
        for(int i=0;i<opponentList.size();i++){
            shipHolder=opponentList.get(i);
            if(shipHolder.getDirection()==0){
                if(shipHolder.getRow()==a&&(shipHolder.getCol()<=b
            &&shipHolder.getCol()+shipHolder.getLength()>=b)){
                //System.out.println("Ship: " + i + "for hor");
                perm=shipHolder;
                }
            }else if(shipHolder.getDirection()==1){
                if(shipHolder.getCol()==b&&(shipHolder.getRow()<=a
            &&shipHolder.getRow()+shipHolder.getLength()>=a)){
                //System.out.println("Ship: " + i + "for ver");
                perm=shipHolder;
                }
            }
        }
        //System.out.println("This ship has row: " + 
        //perm.getRow() + " and col: " + perm.getCol());
        int rowHold=a;
        int colHold=b;
        int countHit=0;
        if(perm.getDirection()==0){
        //System.out.println("Direc is 0");
            if(perm.getRow()==a&&(perm.getCol()<=b
            &&perm.getCol()+perm.getLength()>=b)){
                if(colHold==0){
                    for (int k=0;k<perm.getLength();k++){
                        if(opponentGrid.get
                        (rowHold,colHold+k).getStatus()==1){
                            countHit++;
                        }
                    }
                }else{
                    for (int i=0;i<10;i++){
                        if(colHold-1>=0){
                            if(opponentGrid.hasShip(rowHold,
                            (colHold-1))){
                                colHold--;
                            }
                        }
                    }
                    //System.out.println("Now at col: " + colHold);
                    for (int k=0;k<perm.getLength();k++){
                        if(opponentGrid.get(rowHold,
                            colHold+k).getStatus()==1){
                            countHit++;
                        }
                    }
                }
            }
            //System.out.println("COUNTHIT for " + a + ", " + b + " is: " +countHit);
                if(!opponentGrid.get(a,b).isSunk()){
                    if(countHit==perm.getLength()){
                        if(colHold==0){
                            for (int k=0;k<perm.getLength();k++){
                                if(opponentGrid.get(rowHold,
                                    colHold+k).getStatus()==1){
                                    opponentGrid.get(rowHold,
                                    colHold+k).markSunk();
                                }
                            }
                            System.out.println("Ship sunk");
                            numSunk++;
                        }else{
                        for (int i=0;i<10;i++){
                            if(colHold-1>=0){
                                if(opponentGrid.hasShip(rowHold,
                                (colHold-1))){
                                    colHold--;
                                }
                            }
                        }
                        for (int k=0;k<perm.getLength();k++){
                            if(colHold+k<10){
                                if(opponentGrid.get(rowHold,
                                    colHold+k).getStatus()==1){
                                    opponentGrid.get(rowHold,
                                    colHold+k).markSunk();
                                }
                            }
                        }
                            System.out.println("Ship sunk");
                            numSunk++;
                    }
                    }
                }
        }else if(perm.getDirection()==1){
            //System.out.println("Direc is 1");
            if(perm.getCol()==b&&(perm.getRow()<=a
            &&perm.getRow()+perm.getLength()>=a)){
                if(rowHold==0){
                    for (int k=0;k<perm.getLength();k++){
                        if(opponentGrid.get
                        (rowHold+k,colHold).getStatus()==1){
                            countHit++;
                        }
                    }
                }else{
                    for (int i=0;i<10;i++){
                        if(rowHold-1>=0){
                            if(opponentGrid.hasShip((rowHold-1),
                            colHold)){
                                rowHold--;
                            }
                        }
                    }
                    System.out.println("Now at row: " + rowHold);
                    for (int k=0;k<perm.getLength();k++){
                        if(opponentGrid.get((rowHold+k),
                        colHold).getStatus()==1){
                            countHit++;
                        }
                    }
                }
            //System.out.println("COUNTHIT for " + a + ", " + b + " is: " +countHit);
            if(!opponentGrid.get(a,b).isSunk()){
                if(countHit==perm.getLength()){
                    if(rowHold==0){
                        for (int k=0;k<perm.getLength();k++){
                                if(opponentGrid.get(rowHold+k,
                                    colHold).getStatus()==1){
                                    opponentGrid.get(rowHold+k,
                                    colHold).markSunk();
                                }
                        }
                         System.out.println("Ship sunk");
                        numSunk++;
                    }else{
                        for (int i=0;i<10;i++){
                            if(rowHold-1>=0){
                                if(opponentGrid.hasShip((rowHold-1),
                                colHold)){
                                    rowHold--;
                                }
                            }
                        }
                        for (int k=0;k<perm.getLength();k++){
                            if(opponentGrid.get(rowHold+k,
                                colHold).getStatus()==1){
                                opponentGrid.get(rowHold+k,
                                colHold).markSunk();
                            }
                    }
                    System.out.println("Ship sunk");
                    numSunk++;
                    }
                }
                            }
                        }
            } 
    }else{
        opponentGrid.markMiss(a,b);
        System.out.println("Miss");
    }
}
}