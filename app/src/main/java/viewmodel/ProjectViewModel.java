package viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.List;

import service.repository.ProjectRepository;
import service.repository.Repo;


/**
 * Created by hadis.t on 4/29/2018.
 */

public class ProjectViewModel extends AndroidViewModel {
    private final LiveData<List<Repo>> projectObservable;

    public ObservableField<List<Repo>> project = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        projectObservable = ProjectRepository.getInstance().getProjectDetails(application.getApplicationContext());
    }

    public LiveData<List<Repo>> getProjectObservable() {
        return projectObservable;
    }

    public void setProject(List<Repo> repo) {
        this.project.set(repo);
    }


}
