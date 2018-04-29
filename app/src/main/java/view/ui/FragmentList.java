package view.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import service.repository.Repo;
import taherzadeh.com.mvvmsample.R;
import taherzadeh.com.mvvmsample.databinding.FrgamentListBinding;
import view.adapter.RecyclerAdapter;
import view.callback.OnItemClickCallBack;
import viewmodel.ProjectViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class FragmentList extends LifecycleFragment  {
    public static final String TAG = "fragment-list";
    private FrgamentListBinding binding;
    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding=DataBindingUtil.inflate(inflater, R.layout.frgament_list,container,false);
        adapter=new RecyclerAdapter(itemClickCallBack);
        binding.projectList.setAdapter(adapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProjectViewModel viewModel =
                ViewModelProviders.of(this).get(ProjectViewModel.class);
        observeViewModel(viewModel);
    }

    private void observeViewModel(final ProjectViewModel viewModel){
        //observe project data
        viewModel.getProjectObservable().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repos) {
                if (repos!=null){
                    binding.setIsLoading(false);
                    adapter.setProjectList(repos);
                }
            }


        });
    }



    private final OnItemClickCallBack itemClickCallBack=new OnItemClickCallBack() {
        @Override
        public void OnItemClicked(View itemView, int position) {
            Toast.makeText(getActivity(),"clicked short",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void OnItemLongClicked(int position) {
            Toast.makeText(getActivity(),"clicked long",Toast.LENGTH_SHORT).show();

        }
    };
}
