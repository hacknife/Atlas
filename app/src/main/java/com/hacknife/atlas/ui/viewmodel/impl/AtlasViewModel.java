package com.hacknife.atlas.ui.viewmodel.impl;

import android.util.Log;

import com.hacknife.atlas.adapter.AtlasAdapter;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.bean.AtlasResource;
import com.hacknife.atlas.ui.base.impl.BaseViewModel;
import com.hacknife.atlas.ui.model.IAtlasModel;
import com.hacknife.atlas.ui.model.impl.AtlasModel;
import com.hacknife.atlas.ui.view.IAtlasView;
import com.hacknife.atlas.ui.viewmodel.IAtlasViewModel;
import com.hacknife.atlas.databinding.ActivityAtlasBinding;

import java.util.List;

public class AtlasViewModel extends BaseViewModel<IAtlasView, IAtlasModel, ActivityAtlasBinding> implements IAtlasViewModel {
    int page = 1;
    String host;
    boolean needRefresh = false;

    public AtlasViewModel(IAtlasView view, ActivityAtlasBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityAtlasBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IAtlasModel createModel() {
        return new AtlasModel(this);
    }


    @Override
    public void atlas(List<Atlas> atlases) {
        if (atlases.size() > 0)
            page++;
        else
            binding.refresh.setNoMoreData(true);
        AtlasAdapter adapter = (AtlasAdapter) binding.rcAtlas.getAdapter();
        adapter.insert(atlases);
        binding.refresh.finishLoadMore(500);
    }

    @Override
    public void loadMore() {
        model.loadMore(page);
    }

    @Override
    public void refresh() {
        if (host == null || (!host.equals(AtlasResource.get().host)))
            needRefresh = true;
        else
            needRefresh = false;
        host = AtlasResource.get().host;
        model.refresh();
    }

    @Override
    public void refresh(List<Atlas> atlases) {
        AtlasAdapter adapter = (AtlasAdapter) binding.rcAtlas.getAdapter();
        if (adapter.data().size() > 0 && needRefresh) {
            binding.refresh.setNoMoreData(false);
            binding.refresh.finishRefresh(1000);
            return;
        }
        page = 2;
        adapter.bindData(atlases);
        Log.v("dzq", atlases.toString());
        binding.refresh.finishRefresh(1000);
    }
}

