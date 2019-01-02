package ru.job4j.carstore.persistence;

import org.junit.*;
import ru.job4j.carstore.models.annotated.Car;
import ru.job4j.carstore.models.annotated.CarBody;
import ru.job4j.carstore.models.annotated.Engine;
import ru.job4j.carstore.models.annotated.Transmission;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class CarGenericDaoTest {
    private  static final Database DB = Database.INSTANCE;
    private static final Engine ENGINE = new Engine();
    private static final CarBody BODY = new CarBody();
    private static final Transmission TRSN = new Transmission();

    private IDao<Car> carIDao;
    private Car car;

    @BeforeClass
    public static void init() {
        ENGINE.setId(1L);
        BODY.setId(1L);
        TRSN.setId(1L);
    }

    @Before
    public void setUp() {
        this.car = new Car("second test", ENGINE, TRSN, BODY);
        carIDao = new GenericDao<>(Car.class);
    }

    @After
    public void shutDown() {
        this.carIDao.delete(this.car);
        this.car = null;
        this.carIDao = null;
    }

    @Test
    public void whenReadExistingCarDataThenShouldGetIt() {

        Car result = carIDao.readById(1);

        assertThat(result.getName(), is("test car"));
        assertThat(result.getBody().getId(), is(2L));
        assertThat(result.getEngine().getId(), is(1L));
        assertThat(result.getTransmission().getId(), is(3L));
    }

    @Test
    public void whenTryReadNonexistingCarDataThenShouldGetNull() {
        Car result = this.carIDao.readById(25L);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void whenTrySaveCarDataThenItShouldBeAdded() {
        this.carIDao.saveOrUpdate(this.car);
        Car result = this.carIDao.readById(this.car.getId());

        assertThat(result.getName(), is(this.car.getName()));
    }

    @Test
    public void whenTryUpdateExistingCarDataThenItShouldBeChanged() {
        this.carIDao.saveOrUpdate(this.car);
        this.car.setName("new test name");

        this.carIDao.saveOrUpdate(this.car);
        Car result = this.carIDao.readById(this.car.getId());

        assertThat(result.getName(), is(this.car.getName()));
    }

}