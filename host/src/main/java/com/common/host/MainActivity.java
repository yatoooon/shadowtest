package com.common.host;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import com.tencent.shadow.dynamic.host.EnterCallback;
import com.tencent.shadow.dynamic.host.PluginManager;

public class MainActivity extends AppCompatActivity {
    public static final int FROM_ID_START_ACTIVITY = 1001;
    public static final int FROM_ID_CALL_SERVICE = 1002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager pluginManager = MyApplication.getPluginManager();
        /**
         * @param context context
         * @param formId  标识本次请求的来源位置，用于区分入口
         * @param bundle  参数列表, 建议在参数列表加入自己的验证
         * @param callback 用于从PluginManager实现中返回View
         */
        Bundle bundle = new Bundle();
        // 插件 zip，这几个参数也都可以不传，直接在 PluginManager 中硬编码
        bundle.putString("plugin_path", "/data/local/tmp/plugin-debug.zip");
        // partKey 每个插件都有自己的 partKey 用来区分多个插件，如何配置在下面讲到
        bundle.putString("part_key", "plugin");
        // 路径举例：com.google.samples.apps.sunflower.GardenActivity
        bundle.putString("activity_class_name", "com.common.app.PluginActivity");
        // 要传入到插件里的参数
        bundle.putBundle("extra_to_plugin_bundle", new Bundle());

        pluginManager.enter(MainActivity.this,FROM_ID_START_ACTIVITY, bundle, new EnterCallback() {
            @Override
            public void onShowLoadingView(View view) {
            }

            @Override
            public void onCloseLoadingView() {

            }

            @Override
            public void onEnterComplete() {
                // 启动成功
            }
        });


    }
}