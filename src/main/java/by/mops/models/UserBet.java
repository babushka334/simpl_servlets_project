package by.mops.models;

public class UserBet {
    private Long id;
    private Long bet_id;
    private Long user_id;
    private double value;
    private String status;

    public UserBet(Long id, Long bet_id, Long user_id, double value, String status) {
        this.id = id;
        this.bet_id = bet_id;
        this.user_id = user_id;
        this.value = value;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBet_id() {
        return bet_id;
    }

    public void setBet_id(Long bet_id) {
        this.bet_id = bet_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserBet{" +
                "id=" + id +
                ", bet_id=" + bet_id +
                ", user_id=" + user_id +
                ", value=" + value +
                ", status='" + status + '\'' +
                '}';
    }
}
