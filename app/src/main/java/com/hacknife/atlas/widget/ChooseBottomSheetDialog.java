package com.hacknife.atlas.widget;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hacknife.atlas.R;

public class ChooseBottomSheetDialog extends BottomSheetDialog {


    public interface OnChooseBottomSheetDialogConfirmListener {
        void onClick();
    }

    public interface OnChooseBottomSheetDialogCancelListener {
        void onClick();
    }

    OnChooseBottomSheetDialogConfirmListener onChooseBottomSheetDialogConfirmListener;
    OnChooseBottomSheetDialogCancelListener onChooseBottomSheetDialogCancelListener;
    CharSequence title;
    CharSequence content;
    int colorPrimary;
    int colorPrimaryDark;

    public ChooseBottomSheetDialog(@NonNull Context context) {
        this(context, R.style.ChooseBottomSheetDialog);
        TypedValue colorPrimaryValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, colorPrimaryValue, true);
        colorPrimary = colorPrimaryValue.resourceId;

        TypedValue colorPrimaryDarkValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, colorPrimaryDarkValue, true);
        colorPrimaryDark = colorPrimaryDarkValue.resourceId;
    }

    public ChooseBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
        setContentView(R.layout.layout_bottom_sheet_dialog_choose);
        getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
    }

    public ChooseBottomSheetDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public ChooseBottomSheetDialog content(CharSequence content) {
        this.content = content;
        return this;
    }

    public ChooseBottomSheetDialog setOnChooseBottomSheetDialogCancelListener(OnChooseBottomSheetDialogCancelListener onChooseBottomSheetDialogCancelListener) {
        this.onChooseBottomSheetDialogCancelListener = onChooseBottomSheetDialogCancelListener;
        return this;
    }

    public ChooseBottomSheetDialog setOnChooseBottomSheetDialogConfirmListener(OnChooseBottomSheetDialogConfirmListener onChooseBottomSheetDialogConfirmListener) {
        this.onChooseBottomSheetDialogConfirmListener = onChooseBottomSheetDialogConfirmListener;
        return this;
    }


    @Override
    public void show() {
        super.show();
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        ((TextView) findViewById(R.id.tv_title)).setTextColor(getContext().getResources().getColor(colorPrimaryDark));
        ((TextView) findViewById(R.id.tv_content)).setText(content);
        ((TextView) findViewById(R.id.tv_content)).setTextColor(getContext().getResources().getColor(colorPrimary));


        ((TextView) findViewById(R.id.tv_confirm)).setTextColor(getContext().getResources().getColor(colorPrimaryDark));
        ((TextView) findViewById(R.id.tv_cancel)).setTextColor(getContext().getResources().getColor(colorPrimaryDark));
        findViewById(R.id.tv_confirm).setOnClickListener(v -> {
            dismiss();
            if (onChooseBottomSheetDialogConfirmListener != null)
                onChooseBottomSheetDialogConfirmListener.onClick();
        });
        findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            dismiss();
            if (onChooseBottomSheetDialogCancelListener != null)
                onChooseBottomSheetDialogCancelListener.onClick();
        });
    }
}
