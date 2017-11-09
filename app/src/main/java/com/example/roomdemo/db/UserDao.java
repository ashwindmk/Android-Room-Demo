package com.example.roomdemo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by ashwin on 9/11/17.
 */

@Dao
public interface UserDao {

    // Find
    @Query("select * from user")
    List<User> loadAllUsers();

    @Query("select * from user where id = :id")
    User loadUserById(int id);

    @Query("SELECT * FROM User WHERE age < :age")
    List<User> loadUsersYoungerThan(int age);

    // Insert
    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    // Delete
    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM User")
    void deleteAll();

}
