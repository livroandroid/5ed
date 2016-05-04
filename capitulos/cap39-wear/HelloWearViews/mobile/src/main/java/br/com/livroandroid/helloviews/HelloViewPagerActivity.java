package br.com.livroandroid.helloviews;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;

import java.util.List;

import br.com.livroandroid.shared.Carro;
import livroandroid.lib.wear.WearUtil;

/**
 * Created by ricardo on 19/04/15.
 */
public class HelloViewPagerActivity extends Activity implements MessageApi.MessageListener {
    // Sample dataset for the list
    private List<Carro> carros;
    private WearUtil wearUtil;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_viewpager);

        viewPager =
                (ViewPager) findViewById(R.id.viewPager);

        carros = Carro.getListEsportivos();

        viewPager.setAdapter(new CarrosPagerAdapter(this,carros));

        wearUtil = new WearUtil(this);
        wearUtil.setMessageListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                wearUtil.sendMessage("/page",new byte[]{(byte) position});
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
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
    public void onMessageReceived(MessageEvent messageEvent) {
        if("/page".equals(messageEvent.getPath())) {
            final int position = messageEvent.getData()[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(position);
                }
            });
        }
    }
}