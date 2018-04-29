package service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import database.RepoDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.model.ModelWebserviceResponse;

/**
 * Created by hadis.t on 4/29/2018.
 */

public class ProjectRepository {

    private ApiService apiService;
    private static ProjectRepository projectRepository;

    private ProjectRepository() {
        //TODO this apiService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static ProjectRepository getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }
    public LiveData<List<Repo>> getProjectDetails(final Context context) {
        final MutableLiveData<List<Repo>> data = new MutableLiveData<>();

        apiService.get().enqueue(new Callback<ModelWebserviceResponse>() {
            @Override
            public void onResponse(Call<ModelWebserviceResponse> call, Response<ModelWebserviceResponse> response) {
                simulateDelay();
                Log.e("LOG","success");

                ArrayList<Repo>repos=new ArrayList<Repo>();
                for (int i = 0; i<response.body().getData().size(); i++) {
                    Repo repo = new Repo(response.body().getData().get(i).getJokeId(), response.body().getData().get(i).getJokeDetails());
                    repos.add(repo);
                }
                RepoDao repoDao=RepoDatabase
                        .getInstance(context)
                        .getRepoDao();

                repoDao.deleteAll();
                repoDao.insert(repos);

                data.setValue(repos);
            }

            @Override
            public void onFailure(Call<ModelWebserviceResponse> call, Throwable t) {
                // TODO better error handling in part #2 ...
                Log.e("LOG","failed");
//                data.setValue(null);
                data.setValue(RepoDatabase
                        .getInstance(context)
                        .getRepoDao()
                        .getAllRepos());
            }
        });

        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
