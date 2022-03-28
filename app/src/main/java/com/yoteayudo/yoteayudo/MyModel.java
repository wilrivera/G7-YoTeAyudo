package com.yoteayudo.yoteayudo;

public class MyModel {
    Integer ImageRes;
    String Name;

    public MyModel(Integer imageRes, String name) {
        ImageRes = imageRes;
        Name = name;
    }

    public Integer getImageRes() {
        return ImageRes;
    }

    public void setImageRes(Integer imageRes) {
        ImageRes = imageRes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
