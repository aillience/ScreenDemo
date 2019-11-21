package com.cq.aillience.screendemo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wyq.markXml.MarkXmlUtil;

public class MainActivity extends AppCompatActivity {

    // 需要支持的屏幕尺寸(高x宽)
//    private String supportDimensions = "1334x750,1920x1080,2094x1080,2220x1080,1280x720,2030x1080,2560x1440," +
//            "2076x1080,1776x1080,2118x1080,2392x1440,2218x1080,2040x1080,1808x1080,2150x1080,2116x1080," +
//            "2016x1080,1812x1080,2792x1440,1344x720,1794x1080,2058x1080,2008x1080,1184x720,2672x1440,2712x1440," +
//            "2034x1080,2960x1440,1360x720,2048x1536,1280x800,2154x1080,1758x1080,2114x1080,2004x1080,1208x720," +
//            "2240x1080,1802x1080,2160x1080,2768x1440,2244x1080,1980x1080,1920x1200" + "2560x1600,2678x1440,2038x1080," +
//            "1384x720,2412x1440,1320x720,1998x1080,1792x1080,1024x768,2190x1080,2371x1440,2074x1080,2400x1440,960x540," +
//            "1788x1080,1798x1080,2009x1080,1232x800,2417x1440,2452x1600,1216x800,2368x1440,1480x720,2340x1080,800x480," +
//            "2214x1080,2952x1440,1358x720,2280x1080,1952x1536,2140x1080,2052x1080,1184x768,888x480,1332x720,1824x1200," +
//            "2200x1080,2032x1080,2733x1440,1080x2009,2646x1440,2454x1600,1396x720,2316x1080,1368x720";
    private String supportDimensions = "1334x750,2244x1080,2240x1080";
    // 上下文
    private Context mContext;
    // 异步任务
    private MarkTask mMarkTask = null;
//    MarkXmlUtil markXmlUtil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        TextView tv_go = findViewById(R.id.tv_go);
        tv_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GenerateValueFiles(750, 1334, "").generate();
//                if (mMarkTask == null) {
//                    mMarkTask = new MarkTask();
//                }
//                if (mMarkTask.getStatus() != AsyncTask.Status.RUNNING) {
//                    mMarkTask.execute();
//                }
            }
        });
    }


    /**
     * 异步任务
     */
    private class MarkTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            /**
             * 如果不需要生成默认的dp值，上下文传null即可
             */
            // com.wyq.markXml.MarkXmlUtil.markXmlFile(null, 720, 1280, supportDimensions);
            /**
             * 参数一：传递context，可以生成默认的dp值（当设备找不到指定分辨率的资源文件时，可使用默认dp值），默认dp值是根据基本尺寸来定义的
             * 参数二：基本宽度（UI作图的宽度）
             * 参数三：基本高度(UI作图的高度)
             * 参数四：自定义需要支持适配的屏幕分辨率（高x宽加逗号隔开）
             * 参数五：是否需要支持负数？true代表支持，false代表不需要支持
             */
            // 例如，我们公司的UI设计师是在 1920 x 1080 分辨率上做效果图，则传入相对应的分辨率尺寸
            com.wyq.markXml.MarkXmlUtil.markXmlFile(mContext, 750, 1334, supportDimensions, true);
            /**
             * 文件全部生产完毕，也添加完毕，现在应该怎么使用呢？
             * 你会发现，如果你公司的UI设计师是在720x1280屏幕分辨率上作图时，你传入的基本宽度应该是720，你传入的基本高度应该是1280
             * 这个时候，你会发现在res/values-1280x720文件夹里的不论是宽还是高，它们的name和value是一致的，而在其他values下的name和value是不一致的，
             * 其实这就是适配效果，你只需要传入720x1280分辨率上的大小即可自动适配完成，因为当你传入720x1280上的值后，它在其他分辨率上相对应的尺寸也会被计算出来。
             */
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(mContext, "生成xml文件完毕!", Toast.LENGTH_SHORT).show();
            mMarkTask = null;
        }
    }
}
