package com.raddarapp.presentation.android.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.adapter.CategoriesAdapter;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressLint("ValidFragment")
public class DialogCategories extends DialogFragment implements CategoriesAdapter.OnSelectListener {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    public static final int HEIGHT_DIALOG = 460;
    private static final int WIDTH_DIALOG = 240;
    public static String TAG = "DialogCategories";
    private Context context;

    private RecyclerView recyclerCategories;
    private List<FootprintEmojiCategory> categoriesList;
    private CategoriesAdapter categoriesAdapter;
    private TextView selectCategory;
    private CategoriesAdapter.EmojiCategoriesAdapterListener listener;

    private DimenUtils dimenUtils = new DimenUtils();

    private DialogCategories(Context context, CategoriesAdapter.EmojiCategoriesAdapterListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MenuDialogComingSoon);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = (int) dimenUtils.dpToPx(context, WIDTH_DIALOG);
        params.height = (int) dimenUtils.dpToPx(context, HEIGHT_DIALOG);

        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_card_white);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_categories, null);

        builder.setView(view);

        Dialog dialog = builder.create();

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        recyclerCategories = (RecyclerView) view.findViewById(R.id.recycler_categories);
        selectCategory = (TextView) view.findViewById(R.id.select_category);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerCategories.setHasFixedSize(true);
        recyclerCategories.setLayoutManager(layoutManager);

        EmojiUtils emojiUtils = new EmojiUtils();
        categoriesList = new ArrayList<>(emojiUtils.getFootprintEmojiCategories().values());

        Comparator<FootprintEmojiCategory> orderByName = (one, two) -> one.getName().compareTo(two.getName());
        Collections.sort(categoriesList, orderByName);

        categoriesAdapter = new CategoriesAdapter(context, categoriesList, listener, this);

        recyclerCategories.setAdapter(categoriesAdapter);

        initializeFonts();

        return dialog;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(context, FONT_NAME, selectCategory);
    }

    public static void open(Context context, FragmentManager fm, CategoriesAdapter.EmojiCategoriesAdapterListener listener) {
        DialogCategories dialogComingSoon = new DialogCategories(context, listener);
        dialogComingSoon.show(fm, TAG);
    }

    @Override
    public void onSelectListener() {
        dismiss();
    }
}