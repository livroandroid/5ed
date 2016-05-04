package br.com.livroandroid.hellodesignsupportlib;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);

        setUpToolbar();

        setUpNavDrawer();

        setUpNavigationView();

        String[] items = new String[]{
                "Floating Action Button + Snackbar",
                "CoordinatorLayout",
                "RecyclerView + FAB",
                "TabLayout",
                "CollapsingToolbar"
        };

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    private void setUpNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        final Context context = this;
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.nav_item_ex1:
                        show(new Intent(context, ExemploFloatingButtonActivity.class));
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    case R.id.nav_item_ex2:
                        show(new Intent(context, ExemploCoordinatorLayoutActivity.class));
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    case R.id.nav_item_ex3:
                        show(new Intent(context, ExemploRecyclerViewActivity.class));
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    case R.id.nav_item_ex4:
                        show(new Intent(context, ExemploTabLayoutActivity.class));
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    case R.id.nav_item_ex5:
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        show(new Intent(context, ExemploCollapsingToolbarActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpNavDrawer() {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

//        if (!mUserLearnedDrawer) {
//            mDrawerLayout.openDrawer(GravityCompat.START);
//            mUserLearnedDrawer = true;
//            saveSharedSetting(this, PREF_USER_LEARNED_DRAWER, "true");
//        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            switch (position) {
                case 0:
                    show(new Intent(this, ExemploFloatingButtonActivity.class));
                    break;
                case 1:
                    show(new Intent(this, ExemploCoordinatorLayoutActivity.class));
                    break;
                case 2:
                    show(new Intent(this, ExemploRecyclerViewActivity.class));
                    break;
                case 3:
                    show(new Intent(this, ExemploTabLayoutActivity.class));
                    break;
                case 4:
                    show(new Intent(this, ExemploCollapsingToolbarActivity.class));
                    break;
                default:
                    finish();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void show(Intent intent) {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions
                            .makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("livroandroid", "Mem Total: " + (Runtime.getRuntime().totalMemory() / 1024) + " mb");
        Log.d("livroandroid","Mem Free: " + (Runtime.getRuntime().freeMemory() / 1024) + " mb");
    }
}