package ru.itis.dao;

import ru.itis.models.Car;

import java.util.List;

public interface CarDaoJdbc {

    List<Car> getAllCars();

    boolean checkModel(String model);
    void add(Car car);
    void delete(Car car);
    void update(Car car);

}
