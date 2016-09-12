package eruser.barrenland;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by erikruser on 9/10/16.
 */
public class Parcel {

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    private Parcel north;
    private Parcel west;
    private Parcel south;
    private Parcel east;

    private HashSet<Parcel> northSplit = new HashSet<>();
    private HashSet<Parcel> westSplit = new HashSet<>();
    private HashSet<Parcel> southSplit = new HashSet<>();
    private HashSet<Parcel> eastSplit = new HashSet<>();

    private final boolean isBarren;

    public Parcel(int minX, int minY, int maxX, int maxY, boolean isBarren) {

        if(maxX < minX){
            throw new IllegalArgumentException("maxX can not be less than minX");
        }

        if(maxY < minY){
            throw new IllegalArgumentException("maxY can not be less than minY");
        }

        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.isBarren = isBarren;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean isBarren() {
        return isBarren;
    }

    public void setNorthSplit(HashSet<Parcel> northSplit) {
        this.northSplit = northSplit;
    }

    public void setWestSplit(HashSet<Parcel> westSplit) {
        this.westSplit = westSplit;
    }

    public void setSouthSplit(HashSet<Parcel> southSplit) {
        this.southSplit = southSplit;
    }

    public void setEastSplit(HashSet<Parcel> eastSplit) {
        this.eastSplit = eastSplit;
    }

    public HashSet<Parcel> getNorthSplit() {
        return northSplit;
    }

    public HashSet<Parcel> getWestSplit() {
        return westSplit;
    }

    public HashSet<Parcel> getSouthSplit() {
        return southSplit;
    }

    public HashSet<Parcel> getEastSplit() {
        return eastSplit;
    }

    public void setNorth(Parcel north) {
        this.north = north;
    }

    public void setWest(Parcel west) {
        this.west = west;
    }

    public void setSouth(Parcel south) {
        this.south = south;
    }

    public void setEast(Parcel east) {
        this.east = east;
    }

    public Parcel getNorth() {
        return north;
    }

    public Parcel getWest() {
        return west;
    }

    public Parcel getSouth() {
        return south;
    }

    public Parcel getEast() {
        return east;
    }




    public void reconcileNeighbors(){
        reconcileNorth();
        reconcileWest();
        reconcileSouth();
        reconcileEast();
    }

    private void reconcileNorth() {
        for(Parcel neighbor : northSplit){
            if(neighbor.getMinX() == minX && neighbor.getMaxX() == maxX && neighbor.getMinY() == (maxY + 1)){
                north = neighbor;
                northSplit.clear();
                break;
            }
        }
    }

    private void reconcileWest() {
        for(Parcel neighbor : westSplit){
            if(neighbor.getMinY() == minY && neighbor.getMaxY() == maxY && neighbor.getMaxX() == (minX - 1)){
                west = neighbor;
                westSplit.clear();
                break;
            }
        }
    }

    private void reconcileSouth() {
        for(Parcel neighbor : southSplit){
            if(neighbor.getMinX() == minX && neighbor.getMaxX() == maxX && neighbor.getMaxY() == (minY - 1)){
                south = neighbor;
                southSplit.clear();
                break;
            }
        }
    }

    private void reconcileEast() {
        for(Parcel neighbor : eastSplit){
            if(neighbor.getMinY() == minY && neighbor.getMaxY() == maxY && neighbor.getMinX() == (maxX + 1)){
                east = neighbor;
                eastSplit.clear();
                break;
            }
        }
    }





}
