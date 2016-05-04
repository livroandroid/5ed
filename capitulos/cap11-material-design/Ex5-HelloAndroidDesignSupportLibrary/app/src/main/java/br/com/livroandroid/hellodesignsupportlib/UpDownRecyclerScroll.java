package br.com.livroandroid.hellodesignsupportlib;

import android.support.v7.widget.RecyclerView;

/**
 * Created by rlecheta
 */
public abstract class UpDownRecyclerScroll extends RecyclerView.OnScrollListener {
    private static final float HIDE_THRESHOLD = 100;
    private static final float SHOW_THRESHOLD = 50;

    int scrollDist = 0;
    private boolean isVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (isVisible && scrollDist > HIDE_THRESHOLD) {
            //  Hide!
            hide();
            scrollDist = 0;
            isVisible = false;
        }
        //  -MINIMUM because scrolling up gives - dy values
        else if (!isVisible && scrollDist < -SHOW_THRESHOLD) {
            // Show!
            show();

            scrollDist = 0;
            isVisible = true;
        }

        //  Calcula scroll
        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }
    }

    public abstract void show();

    public abstract void hide();
}
