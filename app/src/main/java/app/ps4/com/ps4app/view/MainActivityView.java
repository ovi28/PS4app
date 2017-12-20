package app.ps4.com.ps4app.view;


import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;

public interface MainActivityView {
        RecyclerView getRecyclerView();
        void openTrophy(String name,Bitmap bmp,String trophies,int progress );
}
