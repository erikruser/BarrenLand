package eruser.barrenland;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by erikruser on 9/10/16.
 */
public class FarmTest {
    @Test
    public void addBarrenParcel() throws Exception {

        Farm farm = new Farm(0,0,399,599);

        farm.addBarrenParcel(new Parcel(0, 292, 399, 307, true));

        LinkedList<Parcel> splitParcels = farm.getParcels();

        for(Parcel parcel : splitParcels){
            System.out.println("[" + parcel.getMinX() + "," + parcel.getMinY() + "],[" +
                    parcel.getMaxX() + "," + parcel.getMaxY() + "] " + (parcel.isBarren() ? "*" : ""));
        }

    }

    @Test
    public void addMultipleBarrenParcels() {

        Farm farm = new Farm(0,0,399,599);
        farm.addBarrenParcel(new Parcel(48, 192, 351, 207, true));
        farm.addBarrenParcel(new Parcel(48, 392, 351, 407, true));
        farm.addBarrenParcel(new Parcel(120, 52, 135, 547, true));
        farm.addBarrenParcel(new Parcel(260, 52, 275, 547, true));


        LinkedList<Parcel> splitParcels = farm.getParcels();

        for(Parcel parcel : splitParcels){
            System.out.println("[" + parcel.getMinX() + "," + parcel.getMinY() + "],[" +
                    parcel.getMaxX() + "," + parcel.getMaxY() + "] " + (parcel.isBarren() ? "*" : ""));
        }
    }

}