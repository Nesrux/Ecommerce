package util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {
    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    /*antes da classe ser executada*/
    @BeforeClass
    public static void setUpBeforeClasse() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    /*depois da classe ser executada*/
    @AfterClass
    public static void tearDownAfterClasse() {
        entityManagerFactory.close();
    }

    /*antes de cada método*/
    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    /*depois de cada método*/
    @After
    public void teardown() {
        entityManager.close();
    }
}
