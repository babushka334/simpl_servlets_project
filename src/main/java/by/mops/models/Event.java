package by.mops.models;

import java.io.Serializable;

public class Event implements Serializable {
    private Long id;
    private String team1;
    private String team2;

    public Event(){}

    public Event(Long id, String team1, String team2) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Event(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
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
