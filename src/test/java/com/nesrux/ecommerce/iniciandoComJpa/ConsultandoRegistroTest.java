package com.nesrux.ecommerce.iniciandoComJpa;

import com.nesrux.ecommerce.model.Produto;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultandoRegistroTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

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

    @Test
    public void testarConsulta() {
        Produto produto = entityManager.find(Produto.class, 1L);
        System.out.println(produto.getNome());
    }
}
