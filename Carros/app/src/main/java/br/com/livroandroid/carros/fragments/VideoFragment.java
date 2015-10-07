package br.com.livroandroid.carros.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
        Carro c = getArguments().getParcelable("carro");
        if (c != null) {
            videoView.setVideoURI(Uri.parse(c.urlVideo));
            videoView.setMediaController(new MediaController(getContext()));
            videoView.start();
            toast("start: " + c.urlVideo);
        }
        return view;
    }
}

