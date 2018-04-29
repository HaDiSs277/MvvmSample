package service.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by hadis.t on 4/29/2018.
 */

@Dao
public interface RepoDao {
    @Query("select * from Repo")
    List<Repo> getAllRepos();

    @Insert
    void insert(List<Repo> lstRepo);

    @Update
    void update(List<Repo> repo);

    @Query("DELETE FROM Repo")
       void deleteAll();
}
