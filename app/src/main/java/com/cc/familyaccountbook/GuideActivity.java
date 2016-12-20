package com.cc.familyaccountbook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class GuideActivity extends Activity{
    private ViewPager viewPager;
    //装分页显示的view的数组
    private ArrayList<View>pageViews;
    private ImageView imageView;
    //将小圆点的图片用数组表示
    private  ImageView[] imageViews;
    //包裹滑动图片的LinearLayout
    private ViewGroup viewPics;

    private ViewGroup viewPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉标题栏全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //将要分页显示的view装入数组中
        LayoutInflater inflater=getLayoutInflater().from(this);
        pageViews=new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.viewpager_page1,null));
        pageViews.add(inflater.inflate(R.layout.viewpager_page2,null));
        //创建imageviews数组，大小是要显示的图片的数量
        imageViews=new ImageView[pageViews.size()];
        //从指定的XML文件中加载视图
        viewPics=(ViewGroup)inflater.inflate(R.layout.guide_activity,null);
        //实例化小圆点的linearlayout和viewpager
        viewPoints=(ViewGroup)viewPics.findViewById(R.id.viewGroup);
        viewPager=(ViewPager)viewPics.findViewById(R.id.guidePages);
        //添加小圆点的图片
        for (int i=0;i<pageViews.size();i++){
            imageView= new ImageView(GuideActivity.this);
            //设置小圆点imageview的参数
            //创建一个宽高都为100的布局
            imageView.setLayoutParams(new LayoutParams(100,100));
            imageView.setPadding(20,20,20,20);
            //将小圆点Layout添加到数组中
            imageViews[i]=imageView;
            //默认选中第一张图片，此时第一个小圆点也是选中状态，其他不是
            if(i==0){
                imageViews[i].setBackgroundResource(R.drawable.balle);
            }else{
                imageViews[i].setBackgroundResource(R.drawable.ball);
            }
            //将imageviews添加到小圆点视图组
            viewPoints.addView(imageViews[i]);
        }
        //显示滑动图片的视图
        setContentView(viewPics);
        //设置viewpager的适配器和监听事件
        viewPager.setAdapter(new GuidePageAdater());
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }
    private Button.OnClickListener Button_OnClickListener=new Button.OnClickListener(){
        public void onClick(View v){
            //setGuide();//设置已经引导
            //跳转
            Intent mIntent=new Intent();
            mIntent.setClass(GuideActivity.this,Login.class);
            GuideActivity.this.startActivity(mIntent);
            GuideActivity.this.finish();
        }
    };
    private static final String SHAREDPREFERENCES_NAME="my_pref";
    private static final String KEY_GUIDE_ACTIVITY="guide_activity";
    private void setGuide(){
        SharedPreferences settings=getSharedPreferences(SHAREDPREFERENCES_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString(KEY_GUIDE_ACTIVITY,"false");
        editor.apply();
    }

    class GuidePageAdater extends PagerAdapter {
        //销毁position位置的届面
        public void destoryItem(View v,int position,Object arg2){
            ((ViewPager)v).removeView(pageViews.get(position));
        }
        public void finishUpdate(View arg0){

        }
        //获取当前窗体界面数
        public int getCount(){
            return pageViews.size();
        }
        //初始化position位置的界面
        public Object instantiateItem(View v,int position){
            ((ViewPager)v).addView(pageViews.get(position));
            //测试页卡1内的按钮事件
            if(position==1){
                Button btn=(Button)findViewById(R.id.btn_close_guide);
                btn.setOnClickListener(Button_OnClickListener);
            }
            return pageViews.get(position);
        }
        //判断是否由对象生成界面
        public boolean isViewFromObject(View v,Object arg1){
            return v==arg1;
        }
        public void startUpdate(View arg0){

        }
        public int getItemPosition(Object object){
            return super.getItemPosition(object);
        }
        public void restoreState(Parcelable arg0,ClassLoader  arg1){

        }
        public Parcelable saveState(){
            return null;
        }
    }


    class GuidePageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {

        }
        public void onPageScolled(int arg0,float arg1,int arg2){

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        public void onPageSelected(int position){
            for(int i=0;i<imageViews.length;i++){
                imageViews[position].setBackgroundResource(R.drawable.balle);
                //不是当前选中的page,其小圆点设置为未选择状态
                if(position!=i){
                    imageViews[i].setBackgroundResource(R.drawable.ball);
                }
            }
        }

    }
}
