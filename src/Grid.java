/*
 * Trevor Ryles P3 Java 6/8/18
 */
public class Grid
{
    private Location[][] grid;

// Constants for number of rows and columns.
public static final int NUM_ROWS = 10;
public static final int NUM_COLS = 10;
// Create a new Grid. Initialize each Location in the grid
// to be a new Location object.
public Grid(){
    grid = new Location[10][10];
    for (int i=0;i<grid.length;i++){
        for (int j=0;j<grid[i].length;j++){
            grid[i][j] = new Location();
            }
        }
    }

// Mark a hit in this location by calling the markHit method
// on the Location object.  
public Location[][] getLocGrid() {
	return grid;
}
public void markHit(int row, int col){
    grid[row][col].markHit();
}

// Mark a miss on this location.    
public void markMiss(int row, int col){
    grid[row][col].markMiss();

}

// Set the status of this location object.
public void setStatus(int row, int col, int status){
    grid[row][col].setStatus(status);
}

// Get the status of this location in the grid  
public int getStatus(int row, int col){
    return grid[row][col].getStatus();
}

// Return whether or not this Location has already been guessed.
public boolean alreadyGuessed(int row, int col) {
    return grid[row][col].isUnguessed();

}   

// Set whether or not there is a ship at this location to the val   
public void setShip(int row, int col, boolean val){
    grid[row][col].setShip(val);
    
}

// Return whether or not there is a ship here   
public boolean hasShip(int row, int col){
    return grid[row][col].hasShip();
}


// Get the Location object at this row and column position
public Location get(int row, int col){
    return grid[row][col];
}

// Return the number of rows in the Grid
public int numRows(){
    return grid.length;
}

// Return the number of columns in the grid
public int numCols(){
     return grid[0].length;
}
public void addShip(Ship s){
    int row = s.getRow();
    int col = s.getCol();
    int length = s.getLength();
    int dir = s.getDirection();
    if(dir==0){
        for (int i=0;i<length;i++){
            grid[row][col + i].setShip(true); 
        }
    }else if(dir==1){
        for (int i=0;i<length;i++){
            grid[row + i][col].setShip(true); 
        }    
    }
}
}