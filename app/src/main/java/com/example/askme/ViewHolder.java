package com.example.askme;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    SimpleExoPlayer exoPlayer;
    private PlayerView mExoplayerView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setVideo(final Context ctx, String title, final String url){
        TextView mTextView = mView.findViewById(R.id.Titletv);
        mExoplayerView = mView.findViewById(R.id.exoplayer_view);
        mTextView.setText(title);
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(ctx).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(ctx);
            Uri video = Uri.parse(url);
            DefaultHttpDataSource dataSourceFactory = new DefaultHttpDataSource("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(video, (DataSource.Factory) dataSourceFactory,extractorsFactory,null,null);
            mExoplayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        }catch (Exception e){
            Log.e("ViewHolder","ExoPlayer Error " +e.toString());
        }
    }

}
