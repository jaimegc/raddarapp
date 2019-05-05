package com.raddarapp.presentation.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.vanniktech.emoji.EmojiTextView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> implements Filterable {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private Context context;
    private List<FootprintEmojiCategory> emojiCategoryList;
    private List<FootprintEmojiCategory> emojiCategoryListFiltered;
    private EmojiCategoriesAdapterListener emojiCategoriesListener;
    private OnSelectListener selectListener;

    public CategoriesAdapter(Context context, List<FootprintEmojiCategory> emojiCategoryList,
            EmojiCategoriesAdapterListener emojiCategoriesListener, OnSelectListener selectListener) {
        this.context = context;
        this.emojiCategoriesListener = emojiCategoriesListener;
        this.emojiCategoryList = emojiCategoryList;
        this.emojiCategoryListFiltered = emojiCategoryList;
        this.selectListener = selectListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FootprintEmojiCategory emojiCategory = emojiCategoryListFiltered.get(position);

        holder.emojiName.setText(emojiCategory.getName());
        holder.emojiImage.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
    }

    @Override
    public int getItemCount() {
        return emojiCategoryListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    emojiCategoryListFiltered = emojiCategoryList;
                } else {
                    List<FootprintEmojiCategory> filteredList = new ArrayList<>();
                    for (FootprintEmojiCategory row : emojiCategoryList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    emojiCategoryListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = emojiCategoryListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                emojiCategoryListFiltered = (ArrayList<FootprintEmojiCategory>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView emojiName;
        public EmojiTextView emojiImage;

        public MyViewHolder(View view) {
            super(view);
            emojiName = view.findViewById(R.id.emoji_name);
            emojiImage = view.findViewById(R.id.emoji_image);

            view.setOnClickListener(v -> {
                emojiCategoriesListener.onEmojiCategorySelected(emojiCategoryListFiltered.get(getAdapterPosition()));
                selectListener.onSelectListener();
            });

            FontUtils fontUtils = new FontUtils();

            fontUtils.applyFont(context, FONT_NAME, emojiName);
        }
    }

    public interface EmojiCategoriesAdapterListener {
        void onEmojiCategorySelected(FootprintEmojiCategory emojiCategory);
    }

    public interface OnSelectListener {
        void onSelectListener();
    }
}