package br.com.livroandroid.hellodesignsupportlib;

import android.content.Context;

/**
 * Created by Suleiman on 30-04-2015.
 */
public class Utils {

    public static int getToolbarHeight(Context context) {
        int height = (int) context.getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
        return height;
    }
}
