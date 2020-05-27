package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public JSONResult buyCar(int id, int buyCount) {
        JSONResult result = null;
        Car existCar = carDao.findById(id);
        if (existCar.getCount() < buyCount) {
            result = JSONResult.errorMsg("车辆剩余数量少于客户购买数量");
        } else {
            Car car = new Car();
            car.setId(id);
            car.setCount(existCar.getCount() - buyCount);
            carDao.updateCountById(car);
            result = JSONResult.ok();
        }
        return result;
    }

    @Override
    public List<Car> listByCarName(String carName, int current, int size) {
        int page = (current - 1) * size;
        return carDao.listByCarName(carName, page, size);
    }
}
