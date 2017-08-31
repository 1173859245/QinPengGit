package com.qp.inst_life.activity;

import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qp.inst_life.adapter.MyFragmentPagerAdapter;
import com.qp.inst_life.fragment.Fragment1;
import com.qp.inst_life.fragment.Fragment2;
import com.qp.inst_life.fragment.Fragment3;
import com.qp.inst_life.fragment.Fragment4;
import com.qp.inst_life.R;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewpager;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView title_text;
    private LinearLayout tab1;
    private LinearLayout tab2;
    private LinearLayout tab3;
    private LinearLayout tab4;
    private long exitTime = 0;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private ImageView imageView_showDrawer;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RelativeLayout relativeLayout;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();

    }
   private  void  initView(){
       relativeLayout=(RelativeLayout)findViewById(R.id.activity_main);
         imageView_showDrawer=(ImageView)findViewById(R.id.shouDreaw);
       drawerLayout=(DrawerLayout)findViewById(R.id.myDrawer) ;
       navigationView=(NavigationView)findViewById(R.id.navigationView);
       viewpager=(ViewPager)findViewById(R.id.viewpager);
       imageView1=(ImageView)findViewById(R.id.imageView1);
       imageView2=(ImageView)findViewById(R.id.imageView2);
       imageView3=(ImageView)findViewById(R.id.imageView3);
       imageView4=(ImageView)findViewById(R.id.imageView4);
       text1=(TextView)findViewById(R.id.text1);
       text2=(TextView)findViewById(R.id.text22);
       text3=(TextView)findViewById(R.id.text3);
       text4=(TextView)findViewById(R.id.text4);
       title_text=(TextView)findViewById(R.id.title_text);
       tab1=(LinearLayout)findViewById(R.id.tab1);
       tab2=(LinearLayout)findViewById(R.id.tab2);
       tab3=(LinearLayout)findViewById(R.id.tab3);
       tab4=(LinearLayout)findViewById(R.id.tab4);
       tab1.setOnClickListener(this);
       tab2.setOnClickListener(this);
       tab3.setOnClickListener(this);
       tab4.setOnClickListener(this);
       imageView_showDrawer.setOnClickListener(this);
       navigationView.setNavigationItemSelectedListener(this);


   }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void initFragment() {
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragments.add(fragment4);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment1);

        myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager(), fragments);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面在滑动过程中不停触发该方法：position：当前滑动到的位置，positionOffset：偏移量的百分比，positionOffsetPixels：偏移量的数值
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            //ViewPager跳转到新页面时触发该方法，position表示新页面的位置。
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        title_text.setText("新闻");
                        imageView1.setImageResource(R.mipmap.ico_play_hover);
                        text1.setTextColor(getResources().getColor(R.color.presuressTextColor));
                        imageView2.setImageResource(R.mipmap.ico_52_study_link);
                        text2.setTextColor(getResources().getColor(R.color.textColor));
                        imageView3.setImageResource(R.mipmap.ico_school_link);
                        text3.setTextColor(getResources().getColor(R.color.textColor));
                        imageView4.setImageResource(R.mipmap.ico_my_link);
                        text4.setTextColor(getResources().getColor(R.color.textColor));
                        break;
                    case 1:
                        title_text.setText("精选");
                        imageView1.setImageResource(R.mipmap.ico_play_link);
                        text1.setTextColor(getResources().getColor(R.color.textColor));
                        imageView2.setImageResource(R.mipmap.ico_52_study_hover);
                        text2.setTextColor(getResources().getColor(R.color.presuressTextColor));
                        imageView3.setImageResource(R.mipmap.ico_school_link);
                        text3.setTextColor(getResources().getColor(R.color.textColor));
                        imageView4.setImageResource(R.mipmap.ico_my_link);
                        text4.setTextColor(getResources().getColor(R.color.textColor));
                        break;
                    case 2:
                        title_text.setText("星座");
                        imageView1.setImageResource(R.mipmap.ico_play_link);
                        text1.setTextColor(getResources().getColor(R.color.textColor));
                        imageView2.setImageResource(R.mipmap.ico_52_study_link);
                        text2.setTextColor(getResources().getColor(R.color.textColor));
                        imageView3.setImageResource(R.mipmap.ico_school_hover);
                        text3.setTextColor(getResources().getColor(R.color.presuressTextColor));
                        imageView4.setImageResource(R.mipmap.ico_my_link);
                        text4.setTextColor(getResources().getColor(R.color.textColor));
                        break;
                    case 3:
                        title_text.setText("笑话");
                        imageView1.setImageResource(R.mipmap.ico_play_link);
                        text1.setTextColor(getResources().getColor(R.color.textColor));
                        imageView2.setImageResource(R.mipmap.ico_52_study_link);
                        text2.setTextColor(getResources().getColor(R.color.textColor));
                        imageView3.setImageResource(R.mipmap.ico_school_link);
                        text3.setTextColor(getResources().getColor(R.color.textColor));
                        imageView4.setImageResource(R.mipmap.ico_my_hover);
                        text4.setTextColor(getResources().getColor(R.color.presuressTextColor));
                        break;
                }

            }

            @Override
            //当页面的滑动状态改变时该方法会被触发，页面的滑动状态有3个：“0”表示什么都不做，“1”表示开始滑动，“2”表示结束滑动。
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setAdapter(myFragmentPagerAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                title_text.setText("新闻");
                viewpager.setCurrentItem(0);
                break;
            case R.id.tab2:
                title_text.setText("精选");
                viewpager.setCurrentItem(1);
                break;
            case R.id.tab3:
                title_text.setText("星座");
                viewpager.setCurrentItem(2);
                break;
            case R.id.tab4:
                viewpager.setCurrentItem(3);
                break;
            case R.id.shouDreaw:
                title_text.setText("笑话");
                drawerLayout.openDrawer(GravityCompat.START);
            break;



        }


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_news:
                title_text.setText("还是未知");
                item.setChecked(true);
                item.setVisible(false);
                break;

        }
        drawerLayout.closeDrawers();

        return false;
    }

    /**
     * 按两次退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Snackbar.make(relativeLayout, "在按一次退出程序！", Snackbar.LENGTH_SHORT)
                    .setAction("提示", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .setDuration(2000)
                    .show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
