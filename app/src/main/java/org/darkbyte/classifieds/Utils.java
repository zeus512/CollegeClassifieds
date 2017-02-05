package org.darkbyte.classifieds;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Jatin on 9/13/2016.
 */
public class Utils {
    // Created A Static Retrofit Service Method For Getting reference to the retrofit service method

    public static boolean checkData(String string) {
        return !string.isEmpty() && string.trim().length() != 0;
    }


    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    public static String getStringFromBitmap(Bitmap bitmap){
        return Base64.encodeToString(getBytesFromBitmap(bitmap),
                Base64.NO_WRAP);
    }

    public static  Bitmap getBitmapFromBytes(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}

