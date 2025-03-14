package com.ygg.uni_androiddev_4;

public class Discipline {
    private String name;
    private String lessonDay;
    private String time;
    private String teacher;

    public Discipline(String name, String lessonDay, String time, String teacher) {
        this.name = name;
        this.lessonDay = lessonDay;
        this.time = time;
        this.teacher = teacher;
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
}
