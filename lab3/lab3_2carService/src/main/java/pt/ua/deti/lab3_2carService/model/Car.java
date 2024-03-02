package pt.ua.deti.lab3_2carService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long carId;

    @Column
    private String maker;

    @Column
    private String model;

    // empty contructor
    public Car() {}

    // base constructor
    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    // getters and setters
    public Long getCarId() {
        return carId;
    }
    public String getMaker() {
        return maker;
    }
    public String getModel() {
        return model;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public void setMaker(String maker) {
        this.maker = maker;
    }
    public void setModel(String model) {
        this.model = model;
    }


    // overridden methods
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Car car = (Car) other;
        return carId.equals(car.carId) && maker.equals(car.maker) && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (carId == null ? 0 : carId.hashCode());
        hash = 31 * hash + (maker == null ? 0 : maker.hashCode());
        hash = 31 * hash + (model == null ? 0 : model.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
