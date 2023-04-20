package com.example.ders1_2.repository;

import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@AllArgsConstructor
public class Custom_filtr {

    @PersistenceContext
    private EntityManager entityManager;


    ////////////////////////////////////////////////
    //string queryni icra edir:
    public List<book> findbyQueryString(String queryy) {
        TypedQuery<book> query = entityManager.createQuery(queryy, book.class);
        return query.getResultList();
    }

    public String findAllquery() {

        return "SELECT m FROM book m where ";
    }

    public String addnamefiltr(String queryy, String name) {
        queryy = queryy + "m.name LIKE '%" + name + "%'";
        return queryy;
    }

    public String addyearfiltr(String queryy, String year) {
        queryy = queryy + "m.year=" + year+"";
        return queryy;
    }

    public String addcountfiltr(String queryy, String count) {
        queryy = queryy + "m.count=" + count+"";
        return queryy;
    }
    /////////////////////////////////////

}

