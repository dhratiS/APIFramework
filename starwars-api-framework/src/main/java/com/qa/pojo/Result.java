package com.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
public class Result {
    public String name;
    public String height;
    public String mass;
    public String hair_color;
    public String skin_color;
    public String eye_color;
    public String birth_year;
    public String gender;
    public String homeworld;
    public ArrayList<String> films;
    public ArrayList<String> species;
    public ArrayList<String> vehicles;
    public ArrayList<String> starships;
    public Date created;
    public Date edited;
    public String url;
    public Result(){

    }
}