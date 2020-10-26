package app.gfghvfg.powered.fdserty.by.gfdf.yalla.gfdfgtty.multiitemselectrecyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>
{

    Activity context;
    ArrayList<String> arrayList;
    TextView tv_Mt;
    MainViewModel mainViewModel;
    boolean isEnable = false, isSelectAll = false;

    ArrayList<String> selectAll = new ArrayList<>();


    public MainAdapter(Activity context, ArrayList<String> arrayList, TextView tv_Mt) {
        this.context = context;
        this.arrayList = arrayList;
        this.tv_Mt = tv_Mt;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);


        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MainViewModel.class);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.item.setText(arrayList.get(position));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!isEnable) {
                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                            MenuInflater menuInflater = actionMode.getMenuInflater();

                            menuInflater.inflate(R.menu.menu, menu);

                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(final ActionMode actionMode, Menu menu) {

                            isEnable = true;

                            clickItem(holder);

                            mainViewModel.getText().observe((LifecycleOwner) context, new Observer<String>() {
                                @Override
                                public void onChanged(String s) {

                                    actionMode.setTitle(String.format("%s Selected", s));


                                }
                            });


                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                            int id = menuItem.getItemId();

                            switch (id) {
                                case R.id.menu_del:
                                    for (String s : selectAll) {
                                        arrayList.remove(s);
                                    }

                                    if (arrayList.size() == 0) {
                                        tv_Mt.setVisibility(View.VISIBLE);
                                    }

                                    actionMode.finish();
                                    break;

                                case R.id.menu_select_all:
                                    if (selectAll.size() == arrayList.size()) {
                                        isSelectAll = false;
                                        selectAll.clear();
                                    } else {
                                        isSelectAll = true;

                                        selectAll.clear();

                                        selectAll.addAll(arrayList);
                                    }

                                    mainViewModel.setText(String.valueOf(selectAll.size()));


                                    notifyDataSetChanged();
                                    break;
                            }
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode actionMode) {

                            isEnable = false;
                            isSelectAll = false;
                            selectAll.clear();
                            notifyDataSetChanged();

                        }
                    };

                    ((AppCompatActivity) view.getContext()).startActionMode(callback);


                } else {
                    clickItem(holder);
                }


                return true;
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEnable) {
                    clickItem(holder);

                } else {
                    Toast.makeText(context, "You Clicked: " + arrayList.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (isSelectAll) {
            holder.check.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else
            {
            holder.check.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);

        }
    }

    private void clickItem(ViewHolder holder) {

        String selected = arrayList.get(holder.getAdapterPosition());

        if (holder.check.getVisibility() == View.GONE) {
            holder.check.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.LTGRAY);

            selectAll.add(selected);
        } else
            {
            holder.check.setVisibility(View.GONE);

            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            selectAll.remove(selected);


        }


        mainViewModel.setText(String.valueOf(selectAll.size()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        ImageView check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            item = itemView.findViewById(R.id.tv);
            check = itemView.findViewById(R.id.iv_check_box);

        }
    }
}
