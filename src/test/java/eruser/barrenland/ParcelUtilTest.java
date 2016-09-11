package eruser.barrenland;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by erikruser on 9/10/16.
 */
public class ParcelUtilTest {

    @Test
    public void testSplitParcel() {

        Parcel testParcel = new Parcel(1, 5, 11, 10,false);
        Parcel barrenParcel = new Parcel(5, 1, 10, 11,true);

        LinkedList<Parcel> splitParcels = ParcelUtil.splitParcel(testParcel, barrenParcel);

        for(Parcel parcel : splitParcels){
            System.out.println("[" + parcel.getMinX() + "," + parcel.getMinY() + "],[" +
            parcel.getMaxX() + "," + parcel.getMaxY() + "] " + (parcel.isBarren() ? "*" : ""));
        }


    }


}