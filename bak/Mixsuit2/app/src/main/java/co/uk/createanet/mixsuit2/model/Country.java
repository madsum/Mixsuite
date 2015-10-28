package co.uk.createanet.mixsuit2.model;

import android.graphics.Bitmap;

/**
 * Created by masum on 28/10/2015.
 */
public class Country {
    // String countryName;;
   public static int countryFlag;
    public static Bitmap bitmap;

    public Country(int countryFlag,  Bitmap bitmap) {
        this.countryFlag = countryFlag;
        this.bitmap = bitmap;
        // this.countryName = countryName;
    }
}