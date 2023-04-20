package com.example.ders1_2.repository;

import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository

public interface bookrepository extends JpaRepository<book,Long>{
    List<Bookdto> findBypublisher(String publisherName);

    //QEYD ASAGIDAKI SORGU ADI TAM DUZ YAZANDA GETIRIR
   // Page<book> findByname(String lastname, Pageable pagingInfo);

    //ASAGIDAKI SORGU ADI LIKE ILE GETIRIR
    @Query("SELECT m FROM book m WHERE m.name LIKE %:name%")
    List<book> findByname(String name);

    @Query("SELECT m FROM book m WHERE m.name LIKE %:name%")
    List<book> findbook(String name);

//    ///PAGESIZ LIKE ILE BOOKLARI BOOK ADINA GORE  ve iline gore AXTARIR
//    @Query("SELECT m FROM book m WHERE (m.name is null or m.name LIKE %:name%) and (m.year :signyear :year) and (m.count :signcount :count)")
//    List<book> findBynamewp(String name,Integer year ,Integer count,String signyear,String signcount);


//    ///PAGESIZ LIKE ILE BOOKLARI BOOK ADINA GORE  ve iline gore AXTARIR
//    @Query("SELECT m FROM book m WHERE (m.name is null or m.name LIKE %:name%) and (m.year = :year) and (m.count = :count)")
//    List<book> findBynamewp(String name,Integer year ,Integer count,String signyear,String signcount);

//    @Query("SELECT m FROM book m WHERE(m.name is null or  m.name LIKE %:name%) and ( m.year :signyear :year) and (m.count=:count)" )
//    List<book> findBynamewp2(String name,Integer year,Integer count,String signyear);

    @Query("SELECT m FROM book m WHERE( m.name LIKE %:name%) and ( m.year= :year) and (m.count=:count)" )
    List<book> findBynamewp2(String name,Integer year,Integer count);

//    @Query( "select * from book where nvl(year,-99999)=nvl(:year,-99999) and nvl(count,-99999)=nvl(:count,-99999);")
//    List<book> findBynamewp3(String name,String year,String count);


    @Query("SELECT m FROM book m WHERE m.name LIKE %:name%")
    Page<book> findByname2(String name, Pageable pagingInfo);

    book findByid(Long id);
//    book findById(Long id);

//    @Query("select a from YourEntity a where(?1 is null or a.name = ?1)
//
//
//       and (?2 is null or a.age= ?2)
//       and (?3 is null or a.salary = ?3)
//       and (?4 is null or a.description = ?4)
//       and (?5 is null or a.joiningDate = ?5)
//       and (?6 is null or a.gender = ?6)
//       ")

//    @Query("SELECT u FROM User u WHERE  (?1 is null or u.name like %?1%) and (?2 is null or  u.age > ?2)")
//    @Query("SELECT m FROM book m WHERE m.name LIKE %name%")
//    List<book> findFiltered(String name, Integer year);


//    @Query("SELECT m FROM book m WHERE m.name LIKE %name%")
//    Page<book> findByname2(String name, Pageable pagingInfo);

//    @Query(value = "SELECT u FROM book u WHERE ( u.name like '%?1%') ", nativeQuery = true)
//    @Query(value = "SELECT * FROM book u WHERE ( name like %:name%) ", nativeQuery = true)

//    @Query(nativeQuery = true, value ="Select * from book as c where c.name like ':name'")
//    @Query("select u from book u where u.name like %?1%")
//    @Query("select u from book u where u.name =?1")
//      @Query("SELECT u FROM book u WHERE u.name =?1")

//    @Query("SELECT m FROM book m  :f1 ")
//    List<book> findbookforfiltr(String f1);

//    default List<book> findPersonByName(String name) {
//        EntityManager entityManager = getEntityManager();
//        String sql = "SELECT * FROM person WHERE name = '" + name + "'";
//        Query query = entityManager.createNativeQuery(sql, book.class) ;
//        return query.getResultList();
//
//        Query q = em.createNativeQuery("SELECT a.firstname, a.lastname FROM Author a");
//        List<Object[]> authors = q.getResultList();
//    }
//
//    default public List<book> findBookByName(String name) {
//        EntityManager entityManager = getEntityManager();
//        TypedQuery<book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name", book.class);
//        query.setParameter("name", name);
//        return query.getResultList();
//    }
//    EntityManager getEntityManager();

//    default public List<book> findBookByName(String name) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
//        EntityManager em = entityManagerFactory.createEntityManager();
//        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.dept = ?1");
//        query.setParameter(1, dept);
//        List<Employee> resultList = query.getResultList();
//        resultList.forEach(System.out::println);
//        em.close();
//    }
//    EntityManager getEntityManager();



}