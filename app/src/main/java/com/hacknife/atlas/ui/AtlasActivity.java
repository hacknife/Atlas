package com.hacknife.atlas.ui;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.AtlasAdapter;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.DataSelector;
import com.hacknife.atlas.bean.DataSource;
import com.hacknife.atlas.bean.DataSourceLite;
import com.hacknife.atlas.bus.ChangeDataSourceEvent;
import com.hacknife.atlas.bus.DownloadEvent;
import com.hacknife.atlas.bus.RxBus;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.ScreenHelper;
import com.hacknife.atlas.http.Consumer;
import com.hacknife.atlas.service.DownloadService;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IAtlasView;
import com.hacknife.atlas.ui.viewmodel.impl.AtlasViewModel;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;
import com.hacknife.atlas.databinding.ActivityAtlasBinding;
import com.hacknife.onlite.OnLiteFactory;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class AtlasActivity extends BaseActivity<IAtlasViewModel, ActivityAtlasBinding> implements IAtlasView, NavigationView.OnNavigationItemSelectedListener {
    AtlasAdapter adapter;


    @Override
    protected IAtlasViewModel performViewModel() {
        return new AtlasViewModel(this, dataBinding);
    }

    @Override
    protected ActivityAtlasBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_atlas);
    }

    @Override
    protected void init() {
//        String content = "[\n" +
//                "{\n" +
//                "\t\"id\": \"1\",\n" +
//                "\t\"name\": \"国产美女\",\n" +
//                "\t\"host\": \"https://www.999mm.cn\",\n" +
//                "\t\"atlas\": \"https://www.999mm.cn/a/guochanmeinv/\",\n" +
//                "\t\"page_url\": \"list_4_%d.html\",\n" +
//                "\t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                "\t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                "\t\"atlasCover\": [\"img\", \"src\"],\n" +
//                "\t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                "\t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                "\t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                "\t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                "\t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                "\t            \"Accept-Encoding:deflate, br\",\n" +
//                "\t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                "\t            \"Upgrade-Insecure-Requests:1\"\n" +
//                "\t           ],\n" +
//                "\t\"enableCookie\": \"1\"\n" +
//                "},\n" +
//                "{\n" +
//                "\t\"id\": \"2\",\n" +
//                "\t\"name\": \"日韩美女\",\n" +
//                "\t\"host\": \"https://www.999mm.cn\",\n" +
//                "\t\"atlas\": \"https://www.999mm.cn/a/rihanmeinv/\",\n" +
//                "\t\"page_url\": \"list_1_%d.html\",\n" +
//                "\t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                "\t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                "\t\"atlasCover\": [\"img\", \"src\"],\n" +
//                "\t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                "\t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                "\t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                "\t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                "\t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                "\t            \"Accept-Encoding:deflate, br\",\n" +
//                "\t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                "\t            \"Upgrade-Insecure-Requests:1\"\n" +
//                "\t           ],\n" +
//                "\t\"enableCookie\": \"1\"\n" +
//                "},\n" +
//                "{\n" +
//                "\t\"id\": \"3\",\n" +
//                "\t\"name\": \"果团网\",\n" +
//                "\t\"host\": \"https://www.999mm.cn\",\n" +
//                "\t\"atlas\": \"https://www.999mm.cn/jg/\",\n" +
//                "\t\"page_url\": \"guotuanwang_92_%d.html\",\n" +
//                "\t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                "\t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                "\t\"atlasCover\": [\"img\", \"src\"],\n" +
//                "\t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                "\t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                "\t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                " \t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                " \t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                " \t            \"Accept-Encoding:deflate, br\",\n" +
//                " \t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                " \t            \"Upgrade-Insecure-Requests:1\"\n" +
//                " \t           ],\n" +
//                "    \"enableCookie\": \"1\"\n" +
//                "},\n" +
//                "{\n" +
//                "\t\"id\": \"4\",\n" +
//                "\t\"name\": \"爱尤物\",\n" +
//                "\t\"host\": \"https://www.999mm.cn\",\n" +
//                "\t\"atlas\": \"https://www.999mm.cn/jg/\",\n" +
//                "\t\"page_url\": \"aiyouwu_102_%d.html\",\n" +
//                "\t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                "\t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                "\t\"atlasCover\": [\"img\", \"src\"],\n" +
//                "\t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                "\t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                "\t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                "\t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                "\t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                "\t            \"Accept-Encoding:deflate, br\",\n" +
//                "\t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                "\t            \"Upgrade-Insecure-Requests:1\"\n" +
//                "\t           ],\n" +
//                "\t\"enableCookie\": \"1\"\n" +
//                "},\n" +
//                "{\n" +
//                " \t\"id\": \"5\",\n" +
//                " \t\"name\": \"秀人网\",\n" +
//                " \t\"host\": \"https://www.999mm.cn\",\n" +
//                " \t\"atlas\": \"https://www.999mm.cn/jg/\",\n" +
//                " \t\"page_url\": \"xiurenwang_78_%d.html\",\n" +
//                " \t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                " \t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                " \t\"atlasCover\": [\"img\", \"src\"],\n" +
//                " \t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                " \t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                " \t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                "\t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                "\t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                "\t            \"Accept-Encoding:deflate, br\",\n" +
//                "\t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                "\t            \"Upgrade-Insecure-Requests:1\"\n" +
//                "\t           ],\n" +
//                "\t\"enableCookie\": \"1\"\n" +
//                "},\n" +
//                "{\n" +
//                " \t\"id\": \"6\",\n" +
//                " \t\"name\": \"台湾美女\",\n" +
//                " \t\"host\": \"https://www.999mm.cn\",\n" +
//                " \t\"atlas\": \"https://www.999mm.cn/jg/\",\n" +
//                " \t\"page_url\": \"taiwanmeinv_67_%d.html\",\n" +
//                " \t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
//                " \t\"atlasTitle\": [\"img\", \"alt\"],\n" +
//                " \t\"atlasCover\": [\"img\", \"src\"],\n" +
//                " \t\"atlasUrl\": [\"a\", \"href\"],\n" +
//                " \t\"nextPageSelect\": [\"#pages\", \"a<last>\", \"href\"],\n" +
//                " \t\"imagesSelect\": [\".content\", \"img\", \"src\"],\n" +
//                "\t\"headers\": [\"User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3741.400 QQBrowser/10.5.3863.400\",\n" +
//                "\t            \"Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\",\n" +
//                "\t            \"Accept-Encoding:deflate, br\",\n" +
//                "\t            \"Accept-Language:zh-CN,zh;q=0.9\",\n" +
//                "\t            \"Upgrade-Insecure-Requests:1\"\n" +
//                "\t           ],\n" +
//                "\t\"enableCookie\": \"1\"\n" +
//                "}\n" +
//                "]\n";
//
//        List<DataSource> dataSources = new Gson().fromJson(content,new TypeToken<List<DataSource>>(){}.getType());
//        OnLiteFactory.create(DataSourceLite.class).insert(dataSources);

        AppConfig.width = ScreenHelper.width(this) - AppConfig.SPACE * 4;
        dataBinding.drawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        adapter = new AtlasAdapter();
        dataBinding.menu.setNavigationItemSelectedListener(this);
        dataBinding.toolbar.setNavigationOnClickListener(view -> dataBinding.drawer.openMenu(true));
        dataBinding.rcAtlas.setAdapter(adapter);
        dataBinding.rcAtlas.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.rcAtlas.addItemDecoration(new StaggeredDividerItemDecoration(2, 10, true));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore());
        adapter.setOnRecyclerViewListener((OnItemClickListener<Atlas>) t -> startActivity(ImageActivity.class, Constant.URL, t));
        dataBinding.refresh.autoRefresh();
        RxBus.toObservable(ChangeDataSourceEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(changeDataSourceEvent -> viewModel.refresh());
        RxBus.toObservable(ChangeDataSourceEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(e -> dataBinding.toolbar.setTitle(DataSelector.get().name));
        if (DataSelector.get().name != null)
            dataBinding.toolbar.setTitle(DataSelector.get().name);
        dataBinding.toolbar.setOnMenuItemClickListener(item -> {
            startService(new Intent(AtlasActivity.this, DownloadService.class));
            RxBus.post(new DownloadEvent(adapter.data()));
            return true;
        });
        RxBus.toObservable(DownloadEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DownloadEvent>(disposable) {
                    @Override
                    public void onNext(DownloadEvent downloadEvent) {
                        if (downloadEvent.atlases.size() > 1) return;
                        if (adapter.data().contains(downloadEvent.atlases.get(0))) {
                            int index = adapter.data().indexOf(downloadEvent.atlases.get(0));
                            adapter.data().get(index).setCached(downloadEvent.atlases.get(0).getCached());
                            adapter.notifyItemChanged(index);
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_datasource)
            startActivity(DataSourceActivity.class);
        else if (id == R.id.menu_download)
            startActivity(DownActivity.class);
        else if (id == R.id.menu_theme)
            startActivity(ThemeActivity.class);
        dataBinding.drawer.closeMenu(true);
        return true;
    }


}
