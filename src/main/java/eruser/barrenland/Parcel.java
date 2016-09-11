package eruser.barrenland;
/**
 * Created by erikruser on 9/10/16.
 */
public class Parcel {

    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

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
}
