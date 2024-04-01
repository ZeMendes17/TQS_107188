package pt.ua.deti.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "reservation")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String reservationToken;

    @Column(nullable = false)
    private String tripCode;

    @Column(nullable = false)
    private String seat;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // constructors
    public Reservation() {
    }
    
    public Reservation(String reservationToken, String date, User user) {
        this.reservationToken = reservationToken;
        // the reservationToken is like: BR-PO-005_3B
        // BR-PO-005 is the tripCode
        // 3B is the seat
        this.tripCode = reservationToken.substring(0, reservationToken.indexOf('_'));
        this.seat = reservationToken.substring(reservationToken.indexOf('_') + 1);
        this.date = date;
        this.user = user;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationToken() {
        return reservationToken;
    }

    public void setReservationToken(String reservationToken) {
        this.reservationToken = reservationToken;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", reservationToken=" + reservationToken + ", seat=" + seat + ", tripCode="
                + tripCode + ", date=" + date + "]";
    }
}
