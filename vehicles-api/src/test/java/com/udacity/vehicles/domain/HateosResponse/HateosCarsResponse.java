package com.udacity.vehicles.domain.HateosResponse;

import com.udacity.vehicles.domain.car.Car;

import java.util.List;

public class HateosCarsResponse {

    private Embedded _embedded;
    private Object _links;

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Object get_links() {
        return _links;
    }

    public void set_links(Object _links) {
        this._links = _links;
    }

    public class Embedded {
        private List<Car> carList;

        public List<Car> getCarList() {
            return carList;
        }

        public void setCarList(List<Car> carList) {
            this.carList = carList;
        }
    }

}
