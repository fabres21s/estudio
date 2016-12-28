package bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ViewScoped
@ManagedBean
public class ScrollView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 8480640670347098073L;
	private List<Car> cars1;
    private List<Car> cars2;
    private List<Car> cars3;
    private List<Car> cars4;
    private List<Car> cars5;
    private List<Car> cars6;
     
    private CarService service = new CarService();
 
    @PostConstruct
    public void init() {
        cars1 = service.createCars(50);
        cars2 = service.createCars(10);
        cars3 = service.createCars(50);
        cars4 = service.createCars(50);
        cars5 = service.createCars(50);
        cars6 = service.createCars(200);
    }
 
    public List<Car> getCars1() {
        return cars1;
    }
 
    public List<Car> getCars2() {
        return cars2;
    }
 
    public List<Car> getCars3() {
        return cars3;
    }
 
    public List<Car> getCars4() {
        return cars4;
    }
 
    public List<Car> getCars5() {
        return cars5;
    }
 
    public List<Car> getCars6() {
        return cars6;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
}