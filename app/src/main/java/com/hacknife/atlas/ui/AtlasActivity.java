package com.hacknife.atlas.ui;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hacknife.atlas.R;
import com.hacknife.atlas.adapter.AtlasAdapter;
import com.hacknife.atlas.adapter.RecycleGridDivider;
import com.hacknife.atlas.adapter.StaggeredDividerItemDecoration;
import com.hacknife.atlas.adapter.base.OnItemClickListener;
import com.hacknife.atlas.app.AtlasApplication;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasLite;
import com.hacknife.atlas.bean.AtlasLiteLite;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;
import com.hacknife.atlas.helper.ScreenHelper;
import com.hacknife.atlas.http.Api;
import com.hacknife.atlas.http.HttpClient;
import com.hacknife.atlas.ui.base.impl.BaseActivity;
import com.hacknife.atlas.ui.view.IAtlasView;
import com.hacknife.atlas.ui.viewmodel.impl.AtlasViewModel;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;
import com.hacknife.atlas.databinding.ActivityAtlasBinding;
import com.hacknife.imagepicker.ImagePicker;
import com.hacknife.onlite.OnLiteFactory;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
        AppConfig.width = ScreenHelper.width(this) - AppConfig.SPACE * 4;
        AtlasResource.init(
                "https://www.999mm.cn/",
                "https://www.999mm.cn/a/guochanmeinv/",
                "list_4_%d.html",
                new String[]{".main", ".boxs", ".img", "li", "a<0>"},
                new String[]{"img", "alt"},
                new String[]{"img", "src"},
                new String[]{"href"},
                new String[]{"#pages", "a<last>", "href"},
                new String[]{".content", "img", "src"}
        );
        String json = "[{\n" +
                "\t\"id\": \"1\",\n" +
                "\t\"name\": \"爱套图美女\",\n" +
                "\t\"host\": \"https://www.999mm.cn/\",\n" +
                "\t\"atlas\": \"https://www.999mm.cn/a/guochanmeinv/\",\n" +
                "\t\"page_url\": \"list_4_%d.html\",\n" +
                "\t\"atlasSelect\": [\".main\", \".boxs\", \".img\", \"li\", \"a<0>\"],\n" +
                "\t\"atlasTitle\": [\"img\", \"alt\"],\n" +
                "\t\"atlasCover\": [\"img\", \"src\"],\n" +
                "\t\"atlasUrl\": [\"href\"],\n" +
                "\t\"nextPageSelect\": [\"#pages\", \"a<clast>\", \"href\"],\n" +
                "\t\"imagesSelect\": [\".content\", \"img\", \"src\"]\n" +
                "}]";
        String TAG = "dzq";
        Gson gson = new Gson();
        List<AtlasLite> list = gson.fromJson(json, new TypeToken<List<AtlasLite>>() {
        }.getType());
        Log.i(TAG, "list: " + list.toString());
        long ret = OnLiteFactory.create(AtlasLiteLite.class).insert(list.get(0));
        Log.i(TAG, "ret: " + ret);
        Log.i(TAG, "select: " + OnLiteFactory.create(AtlasLiteLite.class).select(null).toString());
        dataBinding.drawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        adapter = new AtlasAdapter();
        dataBinding.menu.setNavigationItemSelectedListener(this);
        dataBinding.rcAtlas.setAdapter(adapter);
        dataBinding.rcAtlas.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.rcAtlas.addItemDecoration(new StaggeredDividerItemDecoration(2, 10, true));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh());
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore());
        adapter.setOnRecyclerViewListener((OnItemClickListener<Atlas>) t -> startActivity(ImageActivity.class, Constant.URL, t.getUrl(), Constant.TITLE, t.getTitle()));
        dataBinding.refresh.autoRefresh();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        startActivity(DataSourceActivity.class);
        dataBinding.drawer.closeMenu(true);
        return true;
    }
}
