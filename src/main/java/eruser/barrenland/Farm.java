package eruser.barrenland;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by erikruser on 9/10/16.
 */
public class Farm {

    private LinkedList<Parcel> parcels = new LinkedList<>();

    public Farm(Integer minX, Integer minY, Integer maxX, Integer maxY){
        parcels.add(new Parcel(minX, minY, maxX, maxY, false));
    }

    public void addBarrenParcel(Parcel barrenParcel){

        ListIterator<Parcel> parcelsIterator = parcels.listIterator();

        while(parcelsIterator.hasNext()){
            Parcel nextParcel = parcelsIterator.next();
            LinkedList<Parcel> splitParcels = ParcelUtil.splitParcel(nextParcel, barrenParcel);
            parcelsIterator.remove();
            for(Parcel splitParcel : splitParcels){
                parcelsIterator.add(splitParcel);
            }
        }
    }

    public LinkedList<Parcel> getParcels(){
        return parcels;
    }

}
