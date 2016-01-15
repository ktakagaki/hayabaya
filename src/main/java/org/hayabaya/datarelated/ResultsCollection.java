package org.hayabaya.datarelated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cain on 25/09/15.
 */
public class ResultsCollection {
    private static ResultsCollection instance = null;

    public Results popFromResultsList() {
        return resultsList.pop();
    }

    public void addToResultsList(Results result) {
        resultsList.add(result);
    }

    public static void setInstance(ResultsCollection instance) {
        ResultsCollection.instance = instance;
    }

    public LinkedList<Results> getResultsList() {
        return resultsList;
    }

<<<<<<< HEAD
    private LinkedList<Results> resultsList = new LinkedList<Results>();
=======
    private LinkedList<Results> resultsList = new LinkedList<>();
>>>>>>> dev

    protected ResultsCollection(){

    }

    public static ResultsCollection getInstance(){
        if(instance == null){
            instance = new ResultsCollection();
        }
        return instance;
    }
}
