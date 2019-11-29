package ru.job4j.cartrade.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.persistence.Database;

import java.util.*;
import java.util.stream.Collectors;

public class FilteredAnnouncementDao {
    private static final Logger LOG = LoggerFactory.getLogger(FilteredAnnouncementDao.class);

    private static final String MAIN_QUERY = "from Announcement an where an.car.name like :car_name"
            + " and (an.price between :price1 and :price2) and (an.car.engine.volume between :volume1 and :volume2)";

    public List<Announcement> filterAnnouncement(Map<String, String[]> filters) {
        List<Announcement> result;
        Session session = Database.INSTANCE.openSession();

        try (session) {
            Transaction tx = session.beginTransaction();
            Query<Announcement> query = session.createQuery(getFullQueryString(filters.keySet()), Announcement.class);
            setQueryRestrictionParams(query, filters);

            result = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }

        return result;
    }

    private void setQueryRestrictionParams(Query query, Map<String, String[]> params) {
        Iterator<Map.Entry<String, String[]>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            if ("engine_type".equals(entry.getKey())) {
                query.setParameter("types", Arrays.asList(entry.getValue()));
            } else if ("carbodys".equals(entry.getKey())) {
                query.setParameter("carbodys", toLongList(entry.getValue()));
            } else if ("trns".equals(entry.getKey())) {
                query.setParameter("trns", toLongList(entry.getValue()));
            } else if ("car_name".equals(entry.getKey())) {
                query.setParameter("car_name", "%" + entry.getValue()[0] + "%");
            } else if ("price".equals(entry.getKey())) {
                List<Integer> price = toIntsList(entry.getValue());
                query.setParameter("price1", price.get(0)).setParameter("price2", price.get(1));
            } else if ("volume".equals(entry.getKey())) {
                List<Double> volume = toDoubleList(entry.getValue());
                query.setParameter("volume1", volume.get(0)).setParameter("volume2", volume.get(1));
            }
        }
    }

    private String getFullQueryString(Set<String> keys) {
        StringBuilder sb = new StringBuilder(MAIN_QUERY);
        if (keys.contains("engine_type")) {
            sb.append(" and (an.car.engine.type in :types)");
        }
        if (keys.contains("carbodys")) {
            sb.append(" and (an.car.body.id in :carbodys)");
        }
        if (keys.contains("trns")) {
            sb.append(" and (an.car.transmission.id in :trns)");
        }
        return sb.toString();
    }

    private List<Integer> toIntsList(String[] arr) {
        return Arrays.stream(arr).map(Integer::valueOf).collect(Collectors.toList());
    }

    private List<Double> toDoubleList(String[] arr) {
        return Arrays.stream(arr).map(Double::valueOf).collect(Collectors.toList());
    }

    private List<Long> toLongList(String[] arr) {
        return Arrays.stream(arr).map(Long::valueOf).collect(Collectors.toList());
    }
}
