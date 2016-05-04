package br.com.livroandroid.helloviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import br.com.livroandroid.helloviews.ex1.CardFragmentActivity;
import br.com.livroandroid.helloviews.ex2.CustomCardFragmentActivity;
import br.com.livroandroid.helloviews.ex3.CardFrameActivity;
import br.com.livroandroid.helloviews.ex4.HelloListViewActivity;
import br.com.livroandroid.helloviews.ex5.HelloViewPagerActivity;
import br.com.livroandroid.helloviews.ex6.HelloGridViewPagerActivity;
import br.com.livroandroid.helloviews.ex7.FullScreenActivity;
import br.com.livroandroid.helloviews.ex8.ConfirmationDelayedActivity;
import livroandroid.lib.utils.NotificationUtil;
import livroandroid.lib.wear.WearUtil;

public class MainWearActivity extends Activity implements MessageApi.MessageListener {

    private static final String TAG = "wear";
    private TextView mTextView;
    private WearUtil wearUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wear);

        // Esta activity recebe mensagens do device pela Message API
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText("Utilize o device para abrir as activities no Wear!");

        wearUtil = new WearUtil(this);
        wearUtil.setMessageListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wearUtil.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wearUtil.disconnect();
    }

    @Override
    public void onMessageReceived(final MessageEvent messageEvent) {
        String msg = messageEvent.getPath();
        Log.d(TAG, "onMessageReceived(): " + messageEvent.getPath() +" : " + msg);
        if("/Notification".equals(msg)) {
            Log.d(TAG, "AH");
            NotificationUtil.create(this, R.mipmap.ic_launcher, "Livro Android", "Olá Wear");
        } else if("/CardFragment".equals(msg)) {
            startActivity(new Intent(this,CardFragmentActivity.class));
        } else if("/CustomCardFragment".equals(msg)) {
            startActivity(new Intent(this,CustomCardFragmentActivity.class));
        } else if("/CardFrame".equals(msg)) {
            startActivity(new Intent(this,CardFrameActivity.class));
        } else if("/ListView".equals(msg)) {
            startActivity(new Intent(this,HelloListViewActivity.class));
        } else if("/ViewPager".equals(msg)) {
            startActivity(new Intent(this,HelloViewPagerActivity.class));
        } else if("/GridViewPager".equals(msg)) {
            startActivity(new Intent(this,HelloGridViewPagerActivity.class));
        } else if("/GridViewPager".equals(msg)) {
            startActivity(new Intent(this,HelloGridViewPagerActivity.class));
        } else if("/Full Screen".equals(msg)) {
            startActivity(new Intent(this,FullScreenActivity.class));
        } else if("/Delayed Confirmation".equals(msg)) {
            startActivity(new Intent(this,ConfirmationDelayedActivity.class));
        } else if("/Confirmation Success".equals(msg)) {
            Intent intent = new Intent(this, ConfirmationActivity.class);
            intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                    ConfirmationActivity.SUCCESS_ANIMATION);
            intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Mensagem de sucesso!");
            startActivity(intent);
        } else if("/Confirmation Error".equals(msg)) {
            Intent intent = new Intent(this, ConfirmationActivity.class);
            intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                    ConfirmationActivity.FAILURE_ANIMATION);
            intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE, "Mensagem de erro!");
            startActivity(intent);
        }
    }
}
