package com.phonet.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private double cash;

    private double withDraw;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
//    @OneToOne
//    private HistoryAccount historyAccount;


    public UserAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getWithDraw() {
        return withDraw;
    }

    public void setWithDraw(double withDraw) {
        this.withDraw = withDraw;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", cash=" + cash +
                ", dateTime=" + dateTime +
                '}';
    }
}
