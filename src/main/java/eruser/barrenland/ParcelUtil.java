package eruser.barrenland;

import java.util.LinkedList;

/**
 * Created by erikruser on 9/10/16.
 */
public class ParcelUtil {

    private static class CoordinatePair {

        Integer minVal;
        Integer maxVal;

        public Integer getMinVal() {
            return minVal;
        }

        public void setMinVal(Integer minVal) {
            this.minVal = minVal;
        }

        public Integer getMaxVal() {
            return maxVal;
        }

        public void setMaxVal(Integer maxVal) {
            this.maxVal = maxVal;
        }

    }

    public static LinkedList<Parcel> splitParcel(Parcel toSplit, Parcel splitBy){

        if(!splitBy.isBarren()){
            throw new IllegalArgumentException("Parcel to split by must be a barren parcel.");
        }

        LinkedList<CoordinatePair> xCoordinates = new LinkedList<>();
        LinkedList<CoordinatePair> yCoordinates = new LinkedList<>();

        CoordinatePair leftMost = new CoordinatePair();
        leftMost.setMinVal(toSplit.getMinX());
        xCoordinates.add(leftMost);

        if(toSplit.getMinX() < splitBy.getMinX() && toSplit.getMaxX() >= splitBy.getMinX()){
            Integer leftMaxX = splitBy.getMinX() - 1; //Will be the maxX of any parcels to the left of the barren parcel.
            xCoordinates.getLast().setMaxVal(leftMaxX);
            CoordinatePair next = new CoordinatePair();
            next.setMinVal(splitBy.getMinX());
            xCoordinates.add(next);
        }

        if(toSplit.getMaxX() > splitBy.getMaxX() && toSplit.getMinX() <= splitBy.getMaxX()){
            Integer rightMinX = splitBy.getMaxX() + 1; //Will be the minX of any parcels to the right of the barren parcel.
            xCoordinates.getLast().setMaxVal(splitBy.getMaxX());
            CoordinatePair next = new CoordinatePair();
            next.setMinVal(rightMinX);
            xCoordinates.add(next);
        }

        xCoordinates.getLast().setMaxVal(toSplit.getMaxX());



        CoordinatePair bottomMost = new CoordinatePair();
        bottomMost.setMinVal(toSplit.getMinY());
        yCoordinates.add(bottomMost);

        if(toSplit.getMinY() < splitBy.getMinY() && toSplit.getMaxY() >= splitBy.getMinY()){
            Integer bottomMaxY = splitBy.getMinY() - 1; //Will be the maxY of any parcels below the barren parcel.
            yCoordinates.getLast().setMaxVal(bottomMaxY);
            CoordinatePair next = new CoordinatePair();
            next.setMinVal(splitBy.getMinY());
            yCoordinates.add(next);
        }

        if(toSplit.getMaxY() > splitBy.getMaxY() && toSplit.getMinY() <= splitBy.getMaxY()){
            Integer topMinY = splitBy.getMaxY() + 1; //Will be the minY of any parcels above the barren parcel.
            yCoordinates.getLast().setMaxVal(splitBy.getMaxY());
            CoordinatePair next = new CoordinatePair();
            next.setMinVal(topMinY);
            yCoordinates.add(next);
        }

        yCoordinates.getLast().setMaxVal(toSplit.getMaxY());


        LinkedList<Parcel> splitParcels = new LinkedList<>();

        for(CoordinatePair yCoordinate : yCoordinates){
            for(CoordinatePair xCoordinate : xCoordinates){

                Integer splitMinX = xCoordinate.getMinVal();
                Integer splitMinY = yCoordinate.getMinVal();
                Integer splitMaxX = xCoordinate.getMaxVal();
                Integer splitMaxY = yCoordinate.getMaxVal();

                boolean isBarrenParcel = toSplit.isBarren();

                if(splitMinX <= splitBy.getMaxX() && splitMaxX >= splitBy.getMinX() &&
                     splitMinY <= splitBy.getMaxY() && splitMaxY >= splitBy.getMinY()){
                    isBarrenParcel = true;
                }

                Parcel parcel = new Parcel(
                        splitMinX,
                        splitMinY,
                        splitMaxX,
                        splitMaxY,
                        isBarrenParcel);

                splitParcels.add(parcel);
            }
        }

        return splitParcels;

    }

}
