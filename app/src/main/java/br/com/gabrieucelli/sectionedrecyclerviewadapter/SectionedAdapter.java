package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Gabriel on 20/05/2017.
 */

public abstract class SectionedAdapter<H extends RecyclerView.ViewHolder,
        VHL extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SECTION_HEADER = -1;
    public static final int TYPE_LIST_VERTICAL = -2;
    public static final int TYPE_LIST_HORIZONTAL = -3;


    @Override
    public int getItemCount() {
        return countItems();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;

        if (isSectionHeaderViewType(viewType)) {
            viewHolder = onCreateSectionHeaderViewHolder(parent, viewType);
        } else if (isListHorizontalViewType(viewType)) {
            viewHolder = onCreateListHorizontalHolder(parent, viewType);
        } else {
            viewHolder = onCreateListVerticalHolder(parent, viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d("log123", "  " + position);

        if (getItemViewType(position) == TYPE_SECTION_HEADER) {
            onBindSectionHeaderViewHolder((H) holder, position / 2);
        } else if (getItemViewType(position) == TYPE_LIST_HORIZONTAL) {
            onBindItemHorizontalViewHolder((VHL) holder, position / 2);
        } else {
            onBindItemVerticalViewHolder((VHL) holder, position / 2);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return TYPE_SECTION_HEADER;
        else
            return getTypeList(position / 2);
    }

    private int countItems() {
        return getSectionCount() * 2;
    }

    protected boolean isSectionHeaderViewType(int viewType) {
        return viewType == TYPE_SECTION_HEADER;
    }

    protected boolean isListVerticalViewType(int viewType) {
        return viewType == TYPE_LIST_VERTICAL;
    }

    protected boolean isListHorizontalViewType(int viewType) {
        return viewType == TYPE_LIST_HORIZONTAL;
    }

    protected abstract int getSectionCount();

    protected abstract int getTypeList(int section);

    protected abstract H onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract VHL onCreateListVerticalHolder(ViewGroup parent, int viewType);

    protected abstract VHL onCreateListHorizontalHolder(ViewGroup parent, int viewType);

    protected abstract void onBindSectionHeaderViewHolder(H holder, int section);

    protected abstract void onBindItemVerticalViewHolder(VHL holder, int section);

    protected abstract void onBindItemHorizontalViewHolder(VHL holder, int section);

}
