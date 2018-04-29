package database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import service.repository.Repo;
import service.repository.RepoDao;

/**
 * Created by hadis.t on 4/29/2018.
 */
@Database(entities = {Repo.class},version =1)
public abstract  class RepoDatabase extends RoomDatabase {
    private static final String DB_NAME="repoDatabase.db";
    private static volatile RepoDatabase instance;

    public static synchronized RepoDatabase getInstance(Context context){
        if (instance==null){
            instance=create(context);
        }
        return instance;
    }

    private static RepoDatabase create(final  Context context){
        return Room.databaseBuilder(
                context,
                RepoDatabase.class,
                DB_NAME).allowMainThreadQueries().build();
    }

    public abstract RepoDao getRepoDao();
}
