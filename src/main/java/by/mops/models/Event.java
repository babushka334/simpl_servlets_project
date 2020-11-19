package by.mops.models;

import java.io.Serializable;

public class Event implements Serializable {
    private Long id;
    private String team1;
    private String team2;
    private String status;
    private String result;
    private int total;
    public Event(){}



    public Event(Long id, String team1, String team2, String status) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.status = status;
    }

    public Event(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Event(Long id) {
        this.id = id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }



    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                '}';
    }
}
