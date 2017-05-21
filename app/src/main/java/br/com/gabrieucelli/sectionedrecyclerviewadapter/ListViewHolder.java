package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Gabriel on 20/05/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> list;
    private boolean horizontal;

    public ListViewHolder(View itemView) {
        super(itemView);
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
    }

    public void setLayoutManager(Context context, boolean horizontal) {
        this.horizontal = horizontal;
        mLayoutManager = new LinearLayoutManager(context, horizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void onBind() {
        SimpleAdapter simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    private class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v;

            if(horizontal) {
                v = inflater.inflate(R.layout.item_list_horizontal, parent, false);
                return new ItemHorizontalViewHolder(v);
            }
            else {
                v = inflater.inflate(R.layout.item_list_vertical, parent, false);
                return new ItemVerticalViewHolder(v);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if(holder instanceof ItemVerticalViewHolder) {
                ItemVerticalViewHolder itemVertical = (ItemVerticalViewHolder) holder;
                itemVertical.text.setText("item vertical " + position);
            }

            if(holder instanceof ItemHorizontalViewHolder) {
                ItemHorizontalViewHolder itemHorizontal = (ItemHorizontalViewHolder) holder;
                itemHorizontal.text.setText("item horizontal " + position);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class ItemVerticalViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ItemVerticalViewHolder(View itemView) {
            super(itemView);
            text =  (TextView)itemView.findViewById(R.id.text_list_vertical);
        }
    }

    private class ItemHorizontalViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ItemHorizontalViewHolder(View itemView) {
            super(itemView);
            text =  (TextView)itemView.findViewById(R.id.text_list_horizontal);
        }
    }

}
