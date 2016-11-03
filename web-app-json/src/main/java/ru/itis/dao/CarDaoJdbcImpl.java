package ru.itis.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.models.Car;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kyzlek_pc on 02.11.16.
 */
public class CarDaoJdbcImpl implements CarDaoJdbc {

    // language=SQL
    public static final String SQL_SELECT_ALL_CARS = "SELECT * FROM auto;";
    // language=SQL
    public static final String SQL_ADD_CAR = "INSERT INTO auto( model, power) VALUES (:model, :power);";
    // language=SQL
    public static final String SQL_CHECK_MODEL = "SELECT EXISTS (SELECT * FROM auto WHERE model = :model) checkModel";


    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public CarDaoJdbcImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public List<Car> getAllCars() {

        return this.namedJdbcTemplate.query(
                SQL_SELECT_ALL_CARS,
                new RowMapper<Car>() {

                    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Car(resultSet.getInt("id_auto"), resultSet.getString("model"), resultSet.getString("power"));
                    }
                }
        );

    }

    public void add(Car car) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", car.getModel());
        paramsMap.put("power", car.getPower());
        this.namedJdbcTemplate.update(
                SQL_ADD_CAR,
                paramsMap
        );

    }

    public boolean checkModel(String model){

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("model", model);
        Boolean bool = this.namedJdbcTemplate.queryForObject(
                SQL_CHECK_MODEL,
                paramsMap,
                new RowMapper<Boolean>() {

                    public Boolean mapRow(ResultSet resultSet, int i) throws SQLException {

                        if(resultSet.getString("checkModel").equals("t"))
                            return new Boolean(true);
                        else
                            return new Boolean(false);
                    }
                }
        );

        if(bool.booleanValue())
            return true;
        else
            return false;

    }

    public void update(Car car) {

    }

    public void delete(Car car) {

    }

}
