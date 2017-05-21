package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by Gabriel on 20/05/2017.
 */

public abstract class SectionedAdapter<H extends RecyclerView.ViewHolder,
        HV extends RecyclerView.ViewHolder,
        HH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SECTION_HEADER = -1;
    public static final int TYPE_LIST_VERTICAL = -2;
    public static final int TYPE_LIST_HORIZONTAL = -3;

    @IntDef({TYPE_SECTION_HEADER, TYPE_LIST_VERTICAL, TYPE_LIST_HORIZONTAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    @Override
    public int getItemCount() {
        return countItems();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (isSectionHeaderViewType(viewType))
            return onCreateSectionHeaderViewHolder(parent, viewType);
        else
            return onCreateListViewHolder(parent, viewType);

    }

    private ListViewHolder onCreateListViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_list, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(v, isListHorizontalViewType(viewType));
        listViewHolder.setupLayoutManager(parent.getContext());
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_SECTION_HEADER) {
            onBindSectionHeaderViewHolder((H) holder, position / 2);
        } else {
            ListViewHolder listViewHolder = (ListViewHolder) holder;
            listViewHolder.onBind(position / 2);
        }

    }

    @Override
    public
    @Type
    int getItemViewType(int position) {
        if (position % 2 == 0)
            return TYPE_SECTION_HEADER;
        else
            return getTypeList(position / 2);
    }

    private int countItems() {
        return getSectionCount() * 2;
    }

    private class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private boolean isHorizontal;
        private List list;
        private int section;

        private ListAdapter(boolean isHorizontal, int section) {
            this.isHorizontal = isHorizontal;
            this.section = section;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (isHorizontal)
                return onCreateItemListHorizontalHolder(parent, viewType);
            else
                return onCreateItemListVerticalHolder(parent, viewType);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if (isHorizontal)
                onBindItemHorizontalViewHolder((HH) holder, section, position);
            else
                onBindItemVerticalViewHolder((HV) holder, section, position);
        }

        private void setList(List list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private boolean isHorizontal;
        private ListAdapter listAdapter;

        private ListViewHolder(View itemView, boolean isHorizontal) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
            this.isHorizontal = isHorizontal;
        }

        private void setupLayoutManager(Context context) {
            mLayoutManager = new LinearLayoutManager(context, isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
        }

        public ListAdapter getListAdapter() {
            return listAdapter;
        }

        private void onBind(int section) {
            listAdapter = new ListAdapter(isHorizontal, section);
            listAdapter.setList(getListItens(section));
            recyclerView.setAdapter(listAdapter);
        }

    }

    public void notifySectionChanged(int section) {
        notifyItemChanged(section * 2);
    }

    public void notifySectionInserted(int section) {
        notifyItemInserted(section * 2);
    }

    public void notifySectionRemoved(int section) {
        notifyItemRemoved(section * 2);
    }

    private boolean isSectionHeaderViewType(@Type int viewType) {
        return viewType == TYPE_SECTION_HEADER;
    }

    protected boolean isListVerticalViewType(@Type int viewType) {
        return viewType == TYPE_LIST_VERTICAL;
    }

    private boolean isListHorizontalViewType(@Type int viewType) {
        return viewType == TYPE_LIST_HORIZONTAL;
    }

    protected abstract int getSectionCount();

    protected abstract
    @Type
    int getTypeList(int section);

    protected abstract List getListItens(int section);

    protected abstract H onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType);

    protected abstract HV onCreateItemListVerticalHolder(ViewGroup parent, int viewType);

    protected abstract HH onCreateItemListHorizontalHolder(ViewGroup parent, int viewType);

    protected abstract void onBindSectionHeaderViewHolder(H holder, int section);

    protected abstract void onBindItemVerticalViewHolder(HV holder, int section, int position);

    protected abstract void onBindItemHorizontalViewHolder(HH holder, int section, int position);

}
