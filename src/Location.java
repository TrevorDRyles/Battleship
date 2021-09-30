/*
 * Trevor Ryles P3 Java 6/8/18
 */
public class Location
{
public static final int UNGUESSED = 0;
public static final int HIT = 1;
public static final int MISSED = 2;
public static final int SUNK = 3;
private boolean hasShip = false;
private boolean isHit = false;
private int status = UNGUESSED;
// Location constructor. 
public Location(){
   
}

// Was this Location a hit?
public boolean checkHit(){
    return isHit;
}

// Was this location a miss?
public boolean checkMiss(){
    if(status==MISSED){
        return true;
    }
    return false;
}

// Was this location unguessed?
public boolean isUnguessed(){
    return status==UNGUESSED;
}
public boolean isSunk(){
    if(status==SUNK){
        return true;
    }
    return false;
}
// Mark this location a hit.
public void markHit(){
    isHit = true;
    status= HIT;
}
public void markSunk(){
    status = SUNK;
}

// Mark this location a miss.
public void markMiss(){
    status = MISSED;
}

// Return whether or not this location has a ship.
public boolean hasShip(){
    return hasShip;
}

// Set the value of whether this location has a ship.
public void setShip(boolean val){
    this.hasShip = val;
}

// Set the status of this Location.
public void setStatus(int status){
    this.status = status;
    if(status==1||status==2||status==3){
    }
}
// Get the status of this Location.
public int getStatus(){
    return status;
}
}