package view.adapter;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import service.repository.Repo;
import taherzadeh.com.mvvmsample.R;
import taherzadeh.com.mvvmsample.databinding.AdapterRecyclerJokesBinding;
import view.callback.OnItemClickCallBack;

/**
 * Created by hadis.t on 4/29/2018.
 */

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
        List<? extends Repo> projectList;
        private OnItemClickCallBack itemClickCallBack;

        public RecyclerAdapter(OnItemClickCallBack _listener) {
            itemClickCallBack = _listener;
        }

public void setProjectList(final List<? extends Repo> projectList) {
    if (this.projectList == null) {
        this.projectList = projectList;
        notifyItemRangeInserted(0, projectList.size());
    } else {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return RecyclerAdapter.this.projectList.size();
            }

            @Override
            public int getNewListSize() {
                return projectList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return RecyclerAdapter.this.projectList.get(oldItemPosition).id ==
                        projectList.get(newItemPosition).id;
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Repo project = projectList.get(newItemPosition);
                Repo old = projectList.get(oldItemPosition);
                return project.id == old.id
                        && Objects.equals(project.details ,old.details);
            }
        });
        this.projectList = projectList;
        result.dispatchUpdatesTo(this);
    }
}

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            AdapterRecyclerJokesBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_recycler_jokes, parent, false);
            binding.setCallback(itemClickCallBack);
            return new RecyclerViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            holder.binding.setProject(projectList.get(position));
            holder.binding.executePendingBindings();

        }

    @Override
    public int getItemCount() {
        return projectList == null ? 0 : projectList.size();
    }


    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
    final AdapterRecyclerJokesBinding binding;

    public RecyclerViewHolder(AdapterRecyclerJokesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

}
