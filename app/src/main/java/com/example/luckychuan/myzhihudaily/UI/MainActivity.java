package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Theme;
import com.example.luckychuan.myzhihudaily.presenter.GetThemePresenter;
import com.example.luckychuan.myzhihudaily.view.ThemeView;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ThemeView, View.OnClickListener, BaseFragment.OnTitleChangeListener {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;

    private GetThemePresenter mThemePresenter;
    private NavigationView mNavigationView;
    private List<Theme.Data> mDrawerItems;

    private FragmentManager mManager;
    private HomeFragment mHomeFragment;
    private ThemeFragment mThemeFragment;
    private FavoriteFragment mFavoriteFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化抽屉和标题
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        LinearLayout favoriteLayout = (LinearLayout) mNavigationView.getHeaderView(0).findViewById(R.id.favorite_layout);
        favoriteLayout.setOnClickListener(this);

        //请求抽屉主题日报item
        mThemePresenter = new GetThemePresenter(this);
        mThemePresenter.attach(this);
        mThemePresenter.requestData();

        //初始化fragment
        mManager = getSupportFragmentManager();
        showHomeFragment();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setCheckable(true);
        item.setChecked(true);
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.first_page) {
            showHomeFragment();
        } else {
            for (Theme.Data data : mDrawerItems) {
                if (id == data.getId()) {
                    showThemeFragment(id);
                    break;
                }
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showHomeFragment() {

        FragmentTransaction transaction = mManager.beginTransaction();

        if(mThemeFragment!=null){
            transaction.hide(mThemeFragment);
        }
        if(mFavoriteFragment !=null){
            transaction.hide(mFavoriteFragment);
        }

        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            mHomeFragment.setTitleChangeListener(this);
            transaction.add(R.id.fragment_layout, mHomeFragment);
        }else{
            transaction.show(mHomeFragment);
        }

        transaction.commit();

    }

    private void showThemeFragment(int id) {
        FragmentTransaction transaction = mManager.beginTransaction();

        transaction.hide(mHomeFragment);
        if(mFavoriteFragment !=null){
            transaction.hide(mFavoriteFragment);
        }

        if (mThemeFragment == null) {
            mThemeFragment = new ThemeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", id+"");
            mThemeFragment.setArguments(bundle);
            mThemeFragment.setTitleChangeListener(this);
            transaction.add(R.id.fragment_layout, mThemeFragment);
        } else {
            transaction.show(mThemeFragment);
            mThemeFragment.refreshData(id+"");
        }

        transaction.commit();

    }

    private void showFavoriteFragment(){
        FragmentTransaction transaction = mManager.beginTransaction();

        transaction.hide(mHomeFragment);
        if(mThemeFragment!=null){
            transaction.hide(mThemeFragment);
        }

        if (mFavoriteFragment == null) {
            mFavoriteFragment = new FavoriteFragment();
            mFavoriteFragment.setTitleChangeListener(this);
            transaction.add(R.id.fragment_layout, mFavoriteFragment);
        }else{
            transaction.show(mFavoriteFragment);
        }

        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


    }


    /**
     * 更新抽屉item
     *
     * @param theme
     */
    @Override
    public void setDrawerItem(Theme theme) {
        mDrawerItems = theme.getDataList();
        Menu menu = mNavigationView.getMenu();
        for (Theme.Data data : mDrawerItems) {
            menu.add(R.id.group, data.getId(), 0, data.getName());
        }
    }

    @Override
    public void showErrorMsg(String error) {
        //由于知乎日报在首页并没有提示错误，此方法不写内容
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThemePresenter.detach();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.favorite_layout:
                showFavoriteFragment();
                break;
        }

    }

    @Override
    public void changeToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }
}
