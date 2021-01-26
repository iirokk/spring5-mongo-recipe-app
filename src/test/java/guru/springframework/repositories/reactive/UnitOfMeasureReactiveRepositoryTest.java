package guru.springframework.repositories.reactive;


import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    @Before
    public void setUp() {
        reactiveRepository.deleteAll().block();
    }

    @Test
    public void testUnitSave() {
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setDescription("test");
        reactiveRepository.save(unit).block();
        Long count = reactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setDescription("test");
        reactiveRepository.save(unit).block();
        UnitOfMeasure found = reactiveRepository.findByDescription("test").block();
        assertEquals("test", found.getDescription());
    }
}