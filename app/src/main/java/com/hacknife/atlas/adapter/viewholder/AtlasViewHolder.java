package com.hacknife.atlas.adapter.viewholder;


import android.view.View;
import android.view.ViewGroup;

import com.hacknife.atlas.adapter.base.BaseRecyclerViewHolder;
import com.hacknife.atlas.bean.Atlas;
import com.hacknife.atlas.databinding.ItemAtlasBinding;
import com.hacknife.atlas.helper.AppConfig;
import com.hacknife.atlas.helper.Constant;

public class AtlasViewHolder extends BaseRecyclerViewHolder<Atlas, ItemAtlasBinding> {


    public AtlasViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Atlas entity) {
        ViewGroup.LayoutParams params = binding.ivCover.getLayoutParams();
        params.width = (AppConfig.width - 4 * AppConfig.SPACE) / (AppConfig.styleCollection == 0 ? 2 : 3);
        params.height = (int) (params.width * (AppConfig.styleCollectionHeight * 1f / Constant.STYLE_COLLECTION_HEIGHT_BASE));
        binding.ivCover.setLayoutParams(params);
        binding.setEntity(entity);
    }


}