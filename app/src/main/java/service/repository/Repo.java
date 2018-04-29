package service.repository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hadis.t on 4/29/2018.
 */

@Entity
public class Repo {
    @PrimaryKey(autoGenerate = true)
    public int id;
//    @ColumnInfo(name = "category")
//    public String category;
    @ColumnInfo(name = "details")
    public String details;

    @Ignore
    public Repo(int id,String details){
        this.id=id;
//        this.category=category;
        this.details=details;
    }
    public Repo(){

    }
}
