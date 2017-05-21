package br.com.gabrieucelli.sectionedrecyclerviewadapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.gabrieucelli.sectionedrecyclerviewadapter.R;

/**
 * Created by Gabriel on 20/05/2017.
 */

public class SectionViewHolder extends RecyclerView.ViewHolder {

    public TextView text;

    public SectionViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text_section);
    }
}