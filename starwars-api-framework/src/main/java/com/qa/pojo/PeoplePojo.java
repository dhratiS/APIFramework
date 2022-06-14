package com.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class PeoplePojo {
    public int count;
    public String next;
    public Object previous;
    public ArrayList<Result> results;
    public PeoplePojo(){

    }
}
