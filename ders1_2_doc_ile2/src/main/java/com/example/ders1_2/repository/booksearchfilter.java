package com.example.ders1_2.repository;

import com.example.ders1_2.model.book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class booksearchfilter {
    private final EntityManager en;

    public List<book> findallbyquery3(String name,Integer year, Integer count)
    {
        
        CriteriaBuilder criteriaBuilder=en.getCriteriaBuilder();
        CriteriaQuery<book> criteriaQuery=criteriaBuilder.createQuery(book.class);
        //select * from book
        Root<book>  root= criteriaQuery.from(book.class);
        // where clause
        Predicate namepred=null, yearpred=null, countpred=null;
        List<Predicate> allpreds=new ArrayList<>();

        if (name!=null)
        {
            namepred=criteriaBuilder.like(root.get("name"),"%"+name+"%");
            allpreds.add(namepred);
        }
        if (year!=null)
        {
            yearpred=criteriaBuilder.equal(root.get("year"),year);

            allpreds.add(yearpred);
        }
        if (count!=null)
        {
            countpred=criteriaBuilder.equal(root.get("count"),count);
            allpreds.add(countpred);
        }

        Predicate predfinal=criteriaBuilder.like(root.get("name"),"%"+""+"%");
        for (Predicate o: allpreds) {
            predfinal=criteriaBuilder.and(predfinal,o);
        }

//        Predicate firstnamefiltr=criteriaBuilder.or(namepred);
//        Predicate yearfiltr=criteriaBuilder.and(firstnamefiltr,yearpred);
//        Predicate countfiltr=criteriaBuilder.and(yearfiltr,countpred);
//        Predicate andpredicate=criteriaBuilder.and(firstname,yearpred,countpred);

        criteriaQuery.where(predfinal);
        TypedQuery<book> query=en.createQuery(criteriaQuery);
        return query.getResultList();


    }


}
