package br.com.livroandroid.hellomaterial;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by Ricardo Lecheta on 28/12/2014.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class RevealEffect {

    public static void show(View view, long animDuration) {
        show(view, animDuration, null);
    }

    public static void show(final View view, long animDuration,Animator.AnimatorListener listener) {
        // Centro da view
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // Define o arco para a animação
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // Cria a animação
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        if(listener != null) {
            anim.addListener(listener);
        } else {
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //view.setVisibility(View.VISIBLE);
                }
            });
        }

        // Inicia a animação
        view.setVisibility(View.VISIBLE);
        anim.setDuration(animDuration);
        anim.start();
    }

    public static void hide(final View view, long animDuration) {
        hide(view, animDuration, null);
    }

    public static void hide(final View view, long animDuration,Animator.AnimatorListener listener) {
        // Centro da view
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // Define o arco para a animação
        int initialRadius = view.getWidth();

        // Cria a animação
        Animator anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

        // Quando a animação terminar, esconde a view
        if(listener == null) {
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            anim.addListener(listener);
        }

        // Inicia a animação
        anim.setDuration(animDuration);
        anim.start();
    }
}
