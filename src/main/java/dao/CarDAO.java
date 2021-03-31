package dao;

import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private String URL = "jdbc:mysql://localhost:3306/cars?autoReconnect=true&useSSL=FALSE&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "root";

    private static final String INSERT_CARS_SQL = "insert into cars (make, model, year, mileage) values (?, ?, ?, ?)";
    private static final String SELECT_CAR_BY_ID = "select * from cars where id = ?";
    private static final String SELECT_ALL_CARS = "select * from cars";
    private static final String UPDATE_CARS_SQL = "update cars set make = ?, model = ?, year = ?, mileage = ? where id = ?";
    private static final String DELETE_CARS_SQL = "delete from cars where id = ?";

    protected Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    //create or insert car
    public void insertCar(Car car) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_SQL)) {
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getYear());
            preparedStatement.setInt(4, car.getMileage());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //update car
    public boolean updateCar(Car car) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARS_SQL)) {
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getYear());
            preparedStatement.setInt(4, car.getMileage());
            preparedStatement.setInt(5, car.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated;
    }
    //select car
    public Car selectCar(int id) {
        Car car = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String make = rs.getString("make");
                String model = rs.getString("model");
                String year = rs.getString("year");
                int mileage = rs.getInt("mileage");
                car = new Car(id, make, model, year, mileage);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return car;
    }
    //select cars
    public List<Car> selectAllCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String year = rs.getString("year");
                int mileage = rs.getInt("mileage");
                cars.add(new Car(id, make, model, year, mileage));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cars;
    }
    //delete car
    public boolean deleteCar(int id){
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted;
    }
}
