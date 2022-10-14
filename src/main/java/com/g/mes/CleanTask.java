package com.g.mes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CleanTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CleanTask.class);

    private EntityManagerFactory sessionFactory;

    private Integer maxHistory; // 数据保存年限，单位：年，0表示全部保留

    public CleanTask(EntityManagerFactory sessionFactory, Integer maxHistory) {
        this.maxHistory = maxHistory;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void run() {
        log.info("清理历史数据任务开始...");

        if (maxHistory > 0) {
            EntityManager entityManager = null;
            boolean permit = false;
            try {
                LocalDateTime ts = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).minusDays(maxHistory);
                log.info("删除{}前的数据", ts);

                entityManager = sessionFactory.createEntityManager();
                entityManager.getTransaction().begin();

                Query query = entityManager.createQuery("DELETE FROM FlowmeterDatEntity d WHERE d.ctime < :time");
                query.setParameter("time", ts);
                int deleted = query.executeUpdate();
                log.info("删除{}条FlowmeterDat记录", deleted);

                query = entityManager.createQuery("DELETE FROM PowermeterDatEntity d WHERE d.ctime < :time");
                query.setParameter("time", ts);
                deleted = query.executeUpdate();
                log.info("删除{}条PowermeterDat记录", deleted);

                query = entityManager.createQuery("DELETE FROM SteamFlowmeterDatEntity d WHERE d.ctime < :time");
                query.setParameter("time", ts);
                deleted = query.executeUpdate();
                log.info("删除{}条SteamFlowmeterDatEntity记录", deleted);

                query = entityManager.createQuery("DELETE FROM WaterFlowmeterDatEntity d WHERE d.ctime < :time");
                query.setParameter("time", ts);
                deleted = query.executeUpdate();
                log.info("删除{}条WaterFlowmeterDatEntity记录", deleted);

                entityManager.getTransaction().commit();
                log.info("清理历史数据任务结束!");
            } catch (Exception ignore) {
                log.warn("删除历史数据失败!", ignore);
                entityManager.getTransaction().rollback();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }
        }
    }
}
