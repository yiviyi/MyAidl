package com.eve.myserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Guy implements Parcelable {
    private int age;
    private String name;

    protected Guy(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public Guy() {
    }

    public Guy(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static final Creator<Guy> CREATOR = new Creator<Guy>() {
        @Override
        public Guy createFromParcel(Parcel in) {
            return new Guy(in);
        }

        @Override
        public Guy[] newArray(int size) {
            return new Guy[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }


    //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
    public void readFromParcel(Parcel dest) {
        age = dest.readInt();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name:" + name + "  ,  age:" + age;
    }
}
