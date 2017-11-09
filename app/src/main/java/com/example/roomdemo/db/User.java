package com.example.roomdemo.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ashwin on 9/11/17.
 */

@Entity
public class User {

    public @PrimaryKey String id;
    public String name;
    public int age;

}
