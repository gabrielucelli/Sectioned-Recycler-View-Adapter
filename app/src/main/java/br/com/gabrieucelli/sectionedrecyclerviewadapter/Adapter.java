package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 20/05/2017.
 */

public class Adapter extends SectionedAdapter<Adapter.SectionViewHolder, ListViewHolder> {

    private Map<String, List<String>> map;

    public Adapter(Map<String, List<String>> map) {
        this.map = map;
    }

    @Override
    protected int getSectionCount() {
        return map.size();
    }

    @Override
    protected int getTypeList(int section) {
        return section % 2 == 0 ? TYPE_LIST_VERTICAL : TYPE_LIST_HORIZONTAL;
    }

    @Override
    protected SectionViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_section, parent, false);
        return new SectionViewHolder(v);
    }

    @Override
    protected ListViewHolder onCreateListVerticalHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_list, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(v);
        listViewHolder.setLayoutManager(parent.getContext(), false);
        return listViewHolder;
    }

    @Override
    protected ListViewHolder onCreateListHorizontalHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_list, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(v);
        listViewHolder.setLayoutManager(parent.getContext(), true);
        return listViewHolder;
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionViewHolder holder, int section) {
        holder.text.setText("Secao " + section);
    }

    @Override
    protected void onBindItemVerticalViewHolder(ListViewHolder holder, int section) {
        holder.setList(Collections.nCopies(10, "Oi"));
        holder.onBind();
    }

    @Override
    protected void onBindItemHorizontalViewHolder(ListViewHolder holder, int section) {
        holder.setList(Collections.nCopies(10, "Oi"));
        holder.onBind();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public SectionViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text_section);
        }
    }
}
