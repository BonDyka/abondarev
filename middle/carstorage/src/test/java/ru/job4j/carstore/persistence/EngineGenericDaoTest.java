package ru.job4j.carstore.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.carstore.models.annotated.Engine;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class EngineGenericDaoTest {
    private  static final Database DB = Database.INSTANCE;

    private Engine engine;
    private IDao<Engine> dao;

    @BeforeClass
    public static void classSetUp() {
        MigrationUtil.setMigration();
    }

    @Before
    public void setUp() {
        this.engine = new Engine("DIESEL", 140, 1.7D);
        dao = new GenericDao<>(Engine.class);
    }

    @After
    public void shotDown() {
        dao.delete(engine);
    }

    //--------- TEST SECTION -------
    @Test
    public void whenAddNewEntityThenItShouldBeAdded() {
        dao.saveOrUpdate(this.engine);

        assertThat(this.engine.getId(), not(0));
    }

    @Test
    public void whenUpdateStoredEntityThenShouldUpdateIt() {
        this.dao.saveOrUpdate(this.engine);
        this.engine.setType("PETROL");
        this.dao.saveOrUpdate(this.engine);

        Engine result = this.dao.readById(this.engine.getId());

        assertThat(result.getType(), is(this.engine.getType()));
    }

    @Test
    public void whenReadExistingEntityThenShouldGetIt() {
        Engine result = this.dao.readById(1);

        assertThat(result.getType(), is("DIESEL"));
    }

    @Test
    public void whenTryReadNonExistedEntityThenShouldGetNull() {
        Engine result = this.dao.readById(2);

        assertNull(result);
    }

    @Test
    public void whenReadAllThenSholdGetListOfEntities() {
        int result = dao.readAll().size();

        assertThat(result, is(1));
    }
}