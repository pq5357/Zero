package sg.com.zero.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sg.com.zero.R;
import sg.com.zero.databinding.ItemMainIndexBinding;

/**
 * 主界面目录列表适配器
 * Created by sg on 2017/7/29.
 */

public class IndexRecycleViewAdapter extends RecyclerView.Adapter<IndexRecycleViewAdapter.IndexViewHolder> {

    private Context context;

    private LayoutInflater mLayoutInflater;

    public IndexRecycleViewAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndexViewHolder(mLayoutInflater.inflate(R.layout.item_main_index, parent ,false));
    }

    @Override
    public void onBindViewHolder(IndexViewHolder holder, int position) {
        holder.itemMainIndexBinding.tvIndex.setText(String.valueOf(position));
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public static class IndexViewHolder extends RecyclerView.ViewHolder{

        ItemMainIndexBinding itemMainIndexBinding;

        public IndexViewHolder(View itemView) {
            super(itemView);
            itemMainIndexBinding = DataBindingUtil.bind(itemView);
        }
    }
}
