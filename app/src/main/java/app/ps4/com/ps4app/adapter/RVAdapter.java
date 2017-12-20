package app.ps4.com.ps4app.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import app.ps4.com.ps4app.R;
import app.ps4.com.ps4app.helper.Helper;
import app.ps4.com.ps4app.model.Trophy;
import app.ps4.com.ps4app.presenter.MainActivityPresenter;
import app.ps4.com.ps4app.view.MainActivity;
import app.ps4.com.ps4app.view.MainActivityView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TrophyViewHolder> {

    public static class TrophyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        ImageView titleImage;
        TextView name;
        TextView progress;
        ProgressBar progressBar;


        TrophyViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.trophySmall);
            titleImage = itemView.findViewById(R.id.titleImage);
            name = itemView.findViewById(R.id.name);
            progress = itemView.findViewById(R.id.progress);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private ArrayList<Trophy> trophies;
    private MainActivityPresenter map;

    public RVAdapter( ArrayList<Trophy> trophies, MainActivityPresenter map){
        this.trophies = trophies;
        this.map = map;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TrophyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trophy_small, viewGroup, false);
        TrophyViewHolder tvh = new TrophyViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TrophyViewHolder trophyViewHolder, int i) {
            final int iFinal = i;
            trophyViewHolder.name.setText(trophies.get(i).getName());
            trophyViewHolder.titleImage.setImageBitmap(Helper.decodeBase64(trophies.get(i).getImage()));
            //the values below should be calculated from the trophy's bronze+gold+silver and total
            trophyViewHolder.progressBar.setProgress(63,true);
            trophyViewHolder.progress.setText("63 %");
            trophyViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.openTrophy(trophies.get(iFinal));
                }
            });
    }

    @Override
    public int getItemCount() {
        return trophies.size();
    }
}