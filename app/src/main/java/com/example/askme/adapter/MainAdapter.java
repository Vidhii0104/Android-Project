package com.example.askme.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.askme.Member;
import com.example.askme.R;
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
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Member> memberList;
    private Context ctx;

    public MainAdapter(List<Member> memberList, Context ctx) {
        this.memberList = memberList;
        this.ctx = ctx;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Member member = memberList.get(position);
        if (!TextUtils.isEmpty(member.getTitle())) {
            holder.mTextView.setText(member.getTitle());
        } else {
            holder.mTextView.setText("");
        }

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(ctx).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            SimpleExoPlayer exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(ctx);
            Uri video = Uri.parse(member.getUrl());
//            DefaultHttpDataSource dataSourceFactory = new DefaultHttpDataSource("video");
            DataSource.Factory datasourceFactroy = new DefaultDataSourceFactory(ctx, Util.getUserAgent(ctx, ctx.getString(R.string.app_name)));
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(video, datasourceFactroy,extractorsFactory,null,null);
            holder.mExoplayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("ViewHolder", "ExoPlayer Error " + e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    /**
     * Inner Class for a recycler view
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private PlayerView mExoplayerView;
        TextView mTextView;

        ViewHolder(View v) {
            super(v);
            mExoplayerView = v.findViewById(R.id.exoplayer_view);
            mTextView = v.findViewById(R.id.Titletv);
        }
    }
}