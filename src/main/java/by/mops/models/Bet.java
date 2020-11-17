package by.mops.models;

import java.io.Serializable;

public class Bet implements Serializable {
    private Long id;
    private double coefficient;
    private String value;
    private Event event;
    private String type_of_bet;
    private Long type_of_bet_id;


    public Bet(Long id, String value, double coefficient){
        this.id = id;
        this.coefficient = coefficient;
        this.value = value;
    }
    public Bet(String value, double coefficient, Event event, Long type_of_bet_id){
        this.event = event;
        this.coefficient = coefficient;
        this.value = value;
        this.type_of_bet_id = type_of_bet_id;
    }

    public Bet(Long id, double coefficient, String value, Event event, String type_of_bet, Long type_of_bet_id) {
        this.id = id;
        this.coefficient = coefficient;
        this.value = value;
        this.event = event;
        this.type_of_bet = type_of_bet;
        this.type_of_bet_id = type_of_bet_id;
    }



    public Long getType_of_bet_id() {
        return type_of_bet_id;
    }

    public void setType_of_bet_id(Long type_of_bet_id) {
        this.type_of_bet_id = type_of_bet_id;
    }

    public Bet(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getType_of_bet() {
        return type_of_bet;
    }

    public void setType_of_bet(String type_of_bet) {
        this.type_of_bet = type_of_bet;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", coefficient=" + coefficient +
                ", value='" + value + '\'' +
                ", event=" + event +
                ", type_of_bet='" + type_of_bet + '\'' +
                ", type_of_bet_id=" + type_of_bet_id +
                '}';
    }
}
