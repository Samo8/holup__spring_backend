package sk.zvjs.holup.accommodation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccommodationRepositoryImpl implements AccommodationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Accommodation> findAccommodationsByAllParams(
            Set<String> types,
            String gender,
            Set<String> regions,
            Set<String> districts
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Accommodation> query = cb.createQuery(Accommodation.class);
        Root<Accommodation> accommodation = query.from(Accommodation.class);

        List<Predicate> allPredicates = new ArrayList<>();

        if (types != null && !types.isEmpty()) {
            List<Predicate> typesPredicates = new ArrayList<>();
            listParamsQueryBuilder(cb, types, accommodation.get("type"), typesPredicates);
            allPredicates.add(cb.or(typesPredicates.toArray(new Predicate[0])));
        }

        if (gender != null) {
            Path<String> genderPath = accommodation.get("gender");
            var genderPredicate = cb.equal(genderPath, gender);
            allPredicates.add(cb.and(genderPredicate));
        }

        if (regions != null && !regions.isEmpty()) {
            List<Predicate> regionsPredicates = new ArrayList<>();
            listParamsQueryBuilder(cb, regions, accommodation.get("address").get("region"), regionsPredicates);
            allPredicates.add(cb.or(regionsPredicates.toArray(new Predicate[0])));
        }

        if (districts != null && !districts.isEmpty()) {
            List<Predicate> districtsPredicates = new ArrayList<>();
            listParamsQueryBuilder(cb, districts, accommodation.get("address").get("district"), districtsPredicates);
            allPredicates.add(cb.or(districtsPredicates.toArray(new Predicate[0])));
        }
        query.select(accommodation).where(allPredicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }

    private void listParamsQueryBuilder(
            CriteriaBuilder cb,
            Set<String> params,
            Path<String> typePath,
            List<Predicate> predicates
    ) {
        for (String param : params) {
            predicates.add(cb.like(typePath, param));
        }
    }
}
