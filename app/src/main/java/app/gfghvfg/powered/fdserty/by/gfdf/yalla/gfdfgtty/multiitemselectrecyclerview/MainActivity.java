package app.gfghvfg.powered.fdserty.by.gfdf.yalla.gfdfgtty.multiitemselectrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        textView = findViewById(R.id.tv_mt);


        arrayList.addAll
                (Arrays.asList("One", "two", "three", "four", "five", "sixe", "seven", "eight", "nine", "ten", "eleven", "twelve", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", "twenty four", "twenty five"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());


        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new MainAdapter(this, arrayList, textView);


        recyclerView.setAdapter(adapter);


    }
}