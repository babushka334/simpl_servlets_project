package by.mops.models;

public class Bet {
    private Long id;
    private double coefficient;
    private double value;
    private Event event;
    private String type_of_bet;

    public Bet(Long id, double coefficient, double value, Event event, String type_of_bet) {
        this.id = id;
        this.coefficient = coefficient;
        this.value = value;
        this.event = event;
        this.type_of_bet = type_of_bet;
    }

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
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
                ", value=" + value +
                ", event=" + event +
                ", type_of_bet='" + type_of_bet + '\'' +
                '}';
    }
}
