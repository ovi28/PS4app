package app.ps4.com.ps4app.helper;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Helper {

    public static Bitmap decodeBase64(String base64){
        String base64Image = base64.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
