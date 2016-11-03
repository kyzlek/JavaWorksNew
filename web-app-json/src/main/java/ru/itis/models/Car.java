package ru.itis.models;

import com.google.common.base.MoreObjects;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class Car {

    private int idAuto;
    private String model;
    private String power;

    public Car(int idAuto, String model, String power) {
        this.idAuto = idAuto;
        this.model = model;
        this.power = power;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public String getModel() {
        return model;
    }

    public String getPower() {
        return power;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("idAuto", this.idAuto)
                .add("model", this.model)
                .add("power", this.power)
                .toString();
    }
}
