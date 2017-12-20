package app.ps4.com.ps4app.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.ps4.com.ps4app.R;
import app.ps4.com.ps4app.presenter.MainActivityPresenter;
import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private View viewMenu, viewTrophies, viewTrophy;
    private int shortAnimationDuration;
    private RecyclerView recyclerView;

    MainActivityPresenter presenter = new MainActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        viewMenu = this.findViewById(R.id.main_menu_view);
        viewTrophies = this.findViewById(R.id.trophy_list_view);
        viewTrophy = this.findViewById(R.id.trophy_view);
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        recyclerView = this.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        presenter.onCreate();
    }

    @Override
    public void onBackPressed() {
        if (viewTrophies.getVisibility() == View.VISIBLE) {
            crossfade(viewMenu, viewTrophies);
        } else if (viewTrophy.getVisibility() == View.VISIBLE) {
            crossfade(viewTrophies, viewTrophy);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    @Override
    public void openTrophy(String name, Bitmap bmp, String trophies, int progress){
        ImageView mainImage = findViewById(R.id.mainImage);
        mainImage.setImageBitmap(bmp);
        TextView gameName = findViewById(R.id.titleGame);
        gameName.setText(name);
        TextView gameTrophies = findViewById(R.id.totalTrophies);
        gameTrophies.setText(trophies);
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        progressBar.setProgress(progress);
        ImageView cLayoutBg = findViewById(R.id.bg) ;
        Blurry.with(this)
                .radius(10)
                .sampling(8)
                .from(bmp)
                .into(cLayoutBg);
        cLayoutBg.setScaleType(ImageView.ScaleType.FIT_XY);
        crossfade(viewTrophy,viewTrophies);
    }

    public void closeTrhophies(View v){
        crossfade(viewMenu, viewTrophies);
    }

    public void openTrohpies(View v) {
        crossfade(viewTrophies, viewMenu);
    }

    private void crossfade(View viewShow, final View viewHide) {

        viewShow.setAlpha(0f);
        viewShow.setVisibility(View.VISIBLE);

        viewShow.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        viewHide.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewHide.setVisibility(View.GONE);
                    }
                });
    }


}
