package model;

import java.io.Serializable;

/**
 * Created by Hoang Quyen on 9/27/2016.
 */
public class Job implements Serializable{
    private String name;
    private String notes;
    private String status;

    public Job( ) {

    }

    public Job(String name, String notes, String status) {
        this.name = name;
        this.notes = notes;
        this.status = status;
    }

//    public Job(String name, String notes) {
//        this.name = name;
//        this.notes = notes;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Task name: "+ this.name+ "            Task notes "+ this.notes+  "\n"+
                "Status " + this.status;
    }
}
