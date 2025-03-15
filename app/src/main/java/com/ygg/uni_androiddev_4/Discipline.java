package com.ygg.uni_androiddev_4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Discipline implements Parcelable {
    private String name;
    private String lessonDay;
    private String time;
    private String teacher;
    private String comment;

    public Discipline(String name, String lessonDay, String time, String teacher, String comment) {
        this.name = name;
        this.lessonDay = lessonDay;
        this.time = time;
        this.teacher = teacher;
        this.comment = comment;
    }
    public Discipline(String name, String lessonDay, String time, String teacher) {
        this(name, lessonDay, time, teacher, "None");
    }
    public Discipline(Parcel source) {
        name = source.readString();
        lessonDay = source.readString();
        time = source.readString();
        teacher = source.readString();
        comment = source.readString();
    }

    public String getName() {
        return name;
    }
    public String getLessonDay() {
        return lessonDay;
    }
    public String getTime() {
        return time;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getComment() {
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lessonDay);
        dest.writeString(time);
        dest.writeString(teacher);
        dest.writeString(comment);
    }

    public static final Parcelable.Creator<Discipline> CREATOR = new Parcelable.Creator<Discipline>() {

        @Override
        public Discipline createFromParcel(Parcel source) {
            return new Discipline(source);
        }

        @Override
        public Discipline[] newArray(int size) {
            return new Discipline[size];
        }
    };
}
