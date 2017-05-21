package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.gabrieucelli.sectionedrecyclerviewadapter.viewholders.ItemHorizontalViewHolder;
import br.com.gabrieucelli.sectionedrecyclerviewadapter.viewholders.ItemVerticalViewHolder;
import br.com.gabrieucelli.sectionedrecyclerviewadapter.viewholders.SectionViewHolder;

/**
 * Created by Gabriel on 20/05/2017.
 */

public class Adapter extends SectionedAdapter<SectionViewHolder, ItemVerticalViewHolder, ItemHorizontalViewHolder> {

    private ArrayMap<String, List<String>> map;
    private Context context;

    public Adapter(ArrayMap<String, List<String>> map, Context context) {
        this.map = map;
        this.context = context;
    }

    @Override
    protected int getSectionCount() {
        return map.size();
    }

    @Override
    protected int getTypeList(int section) {
        return section % 2 == 0 ? TYPE_LIST_HORIZONTAL : TYPE_LIST_VERTICAL;
    }

    @Override
    protected List getListItens(int section) {
        return map.valueAt(section);
    }

    @Override
    protected SectionViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);

        return new SectionViewHolder(view);
    }

    @Override
    protected ItemVerticalViewHolder onCreateItemListVerticalHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_vertical, parent, false);

        return new ItemVerticalViewHolder(view);
    }

    @Override
    protected ItemHorizontalViewHolder onCreateItemListHorizontalHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_horizontal, parent, false);

        return new ItemHorizontalViewHolder(view);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionViewHolder holder, int section) {
        holder.text.setText("section " + section);
    }

    @Override
    protected void onBindItemVerticalViewHolder(ItemVerticalViewHolder holder, int section, int position) {
        holder.text.setText("item vertical " + section);
    }

    @Override
    protected void onBindItemHorizontalViewHolder(ItemHorizontalViewHolder holder, final int section, final int position) {

        holder.text.setText("item horizontal " + section);
        holder.setOnClickItemListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clickou na secao: " + section + " posicao: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
