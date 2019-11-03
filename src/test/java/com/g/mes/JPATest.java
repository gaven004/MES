package com.g.mes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class JPATest {
    private Random random = new Random();
    private EntityManagerFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        sessionFactory = Persistence.createEntityManagerFactory("com.g.mes.jpa");
    }

    @After
    public void tearDown() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void save() throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        FlowmeterDatEntity flowmeterDat = new FlowmeterDatEntity();
        flowmeterDat.setDeviceId(random.nextInt(100));
        flowmeterDat.setQ(10.0f);
        flowmeterDat.setV(6.2f);
        flowmeterDat.setAccqPos(365.0);
        flowmeterDat.setAccqNeg(2.0);
        entityManager.persist(flowmeterDat);

        PowermeterDatEntity powermeterDat = new PowermeterDatEntity();
        powermeterDat.setDeviceId(random.nextInt(100));
        powermeterDat.setCtime(LocalDateTime.now());
        entityManager.persist(powermeterDat);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void query() throws Exception {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<FlowmeterDatEntity> query = entityManager.createQuery("from FlowmeterDatEntity", FlowmeterDatEntity.class).setMaxResults(10);
        List<FlowmeterDatEntity> result = query.getResultList();
        for (FlowmeterDatEntity dat : result) {
            System.out.println(dat);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void deleteWithCondition() throws Exception {
        LocalDateTime ts = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withDayOfMonth(1).minusYears(3);

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("DELETE FROM FlowmeterDatEntity d WHERE d.ctime < :time");
        query.setParameter("time", ts);
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}