package ru.itis.service;

import ru.itis.dao.CarDaoJdbc;
import ru.itis.models.Car;

import java.util.List;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class CarServiceImpl implements CarService {

    private CarDaoJdbc carDaoJdbc;

    public CarServiceImpl(CarDaoJdbc carDaoJdbc) {
        this.carDaoJdbc = carDaoJdbc;
    }


    public List<Car> getAllCars() {
        return carDaoJdbc.getAllCars();
    }

    public void add(Car car) { carDaoJdbc.add(car);    }

    public void delete(Car car) {

    }

    public boolean checkModel(String model){ return carDaoJdbc.checkModel(model);    }

    public void update(Car car) {

    }

}
