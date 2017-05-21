package br.com.gabrieucelli.sectionedrecyclerviewadapter;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayMap<String, List<String>> map = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        map.put("Secao 1", Collections.nCopies(10, "Oi"));
        map.put("Secao 2", Collections.nCopies(10, "Tudo bem?"));
        map.put("Secao 3", Collections.nCopies(10, "Tudo"));
        map.put("Secao 4", Collections.nCopies(10, "e você?"));

        recycler_view.setAdapter(new Adapter(map, this));
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}
