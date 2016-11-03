package ru.itis.service;

import ru.itis.models.Car;

import java.util.List;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public interface CarService {
    List<Car> getAllCars();

    boolean checkModel(String model);
    void add(Car car);
    void delete(Car car);
    void update(Car car);
}
