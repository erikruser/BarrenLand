package eruser.barrenland;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by erikruser on 9/10/16.
 */
public class Farm {

    private LinkedList<LinkedList<Parcel>> fertileRegions = new LinkedList<>();

    public Farm(Integer minX, Integer minY, Integer maxX, Integer maxY){

        LinkedList<Parcel> initialParcel = new LinkedList<>();
        initialParcel.add(new Parcel(minX, minY, maxX, maxY, false));
        fertileRegions.add(initialParcel);

    }

    public void addBarrenParcel(Parcel barrenParcel){

        ListIterator<LinkedList<Parcel>> regionsIterator = fertileRegions.listIterator();

        while(regionsIterator.hasNext()) {

            LinkedList<Parcel> parcels = regionsIterator.next();

            ListIterator<Parcel> parcelsIterator = parcels.listIterator();

            boolean hasSplitParcel = false;

            while (parcelsIterator.hasNext()) {
                Parcel nextParcel = parcelsIterator.next();
                LinkedList<Parcel> splitParcels = ParcelUtil.splitParcel(nextParcel, barrenParcel);
                if(null != splitParcels) {
                    hasSplitParcel = true;
                    parcelsIterator.remove();
                    for (Parcel splitParcel : splitParcels) {
                        parcelsIterator.add(splitParcel);
                    }

                    if(null == nextParcel.getNorth()){
                        nextParcel.getNorthSplit().forEach(p -> p.getSouthSplit().addAll(splitParcels));
                    }else{
                        nextParcel.getNorth().getSouthSplit().addAll(splitParcels);
                    }

                    if(null == nextParcel.getWest()){
                        nextParcel.getWestSplit().forEach(p -> p.getEastSplit().addAll(splitParcels));
                    }else{
                        nextParcel.getWest().getEastSplit().addAll(splitParcels);
                    }

                    if(null == nextParcel.getSouth()){
                        nextParcel.getSouthSplit().forEach(p -> p.getNorthSplit().addAll(splitParcels));
                    }else{
                        nextParcel.getSouth().getNorthSplit().addAll(splitParcels);
                    }

                    if(null == nextParcel.getEast()){
                        nextParcel.getEastSplit().forEach(p -> p.getWestSplit().addAll(splitParcels));
                    }else{
                        nextParcel.getEast().getWestSplit().addAll(splitParcels);
                    }

                }
            }

            if(hasSplitParcel) {

                parcels.forEach(Parcel::reconcileNeighbors);

                LinkedList<LinkedList<Parcel>> splitRegions = ParcelUtil.splitRegion(parcels);

                regionsIterator.remove();
                for (LinkedList<Parcel> region : splitRegions) {
                    regionsIterator.add(region);
                }
            }

        }
    }

    public LinkedList<LinkedList<Parcel>> getParcels(){
        return fertileRegions;
    }

}
