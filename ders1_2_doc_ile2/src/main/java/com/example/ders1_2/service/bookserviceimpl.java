package com.example.ders1_2.service;


import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.Author;
import com.example.ders1_2.model.book;
import com.example.ders1_2.model.publisher;
import com.example.ders1_2.repository.authorrepository;
import com.example.ders1_2.repository.bookrepository;
import com.example.ders1_2.repository.publisherrepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service//bunun vasitesi ile dependency injection bas verir
@Slf4j
public class bookserviceimpl implements bookservice {
    private final bookrepository repository;  //???? BU NECE IMPLEMENTASIYASIZ ISLEYIR YENIki @RequiredArgsConstructor normal isleyir imp olmadan
    private final ModelMapper mapper;
    private final authorrepository authorrepository;
    private final publisherrepository publisherrepository;

    @Override
    public List<Bookdto> allbooks() {
        List<book> byid = repository.findAll();
        List<Bookdto> dto = new ArrayList<>();
        for (int i = 0; i < byid.size(); i++) {
            dto.add(mapper.map(byid.get(i), Bookdto.class));
        }

        return dto;
    }

    @Override
    public List<book> allbooksW() {
        List<book> byid = repository.findAll();

        return byid;
    }

    @Override
    public List<book> myjavafiltr(Integer year, Integer count) {
        List<book> all=repository.findAll();
        List <book> filtr=new ArrayList<>();
        for (book x:all)
        {
            if (year!=null)
            {
                if (x.getYear()==year)
                {
                    if (count!=null)
                    {
                        if (x.getCount()==count)
                        {
                            filtr.add(x);
                        }
                    }
                }
            }


        }
        return  filtr;
    }

    @Override
    public List<book> myjavafiltwithnvl(String name, Integer year, Integer count) {
//       String yearr,countt;
//        if (year==null)
//        {
//            yearr="year";
//        }
//        else {
//            yearr=year.toString();
//        }
//        if(count==null)
//        {
//            countt="count";
//        }
//        else
//        {
//            countt=count.toString();
//        }
//        return repository.findBynamewp3(name,yearr,countt);
        return repository.findBynamewp2(name,year,count);
    }


// /{name}/{year}/{count}
    //
//    @Override
//    public List<book> findwithfilter(String f1) {
//        //select * from book where m.name=harry
//        f1="m.name="+f1;
//        return repository.findbookforfiltr(f1);
//    }

    @Override
    public List<List<String>> allbooks2() {
        List<book> byid = repository.findAll();
        List<Bookdto> dto = new ArrayList<>();
        for (int i = 0; i < byid.size(); i++) {
            dto.add(mapper.map(byid.get(i), Bookdto.class));
        }
        List<List<String>> netice = new ArrayList<>();
        for (int i = 0; i < byid.size(); i++) {

            dto.add(mapper.map(byid.get(i), Bookdto.class));
            List<String> netice2 = new ArrayList<>();
            netice2.add(dto.get(i).getName());
            netice2.add(dto.get(i).getYear().toString());
            netice2.add(dto.get(i).getAuthor().toString());
            netice2.add(dto.get(i).getPublisher().toString());
            netice.add(netice2);
        }
        return netice;
    }

    @Override
    public Bookdto detailsbook(Long id) {
        book byid = repository.getById(id);
        Bookdto dto = new Bookdto();
        dto = mapper.map(byid, Bookdto.class);
        return dto;
    }


    //private final ModelMapper mapper=new ModelMapper(); connfig->bean yaratmasaq bele de etmek olar ki implemanntasiyasi yaransin ki requiredarg islesin
    @Override
    public Bookdto getbookbyid(Long id) {
        book byid = repository.findById(id).get();
//        book byid=repository.findByid(id);
        Bookdto dto = mapper.map(byid, Bookdto.class);
        return dto;
        ///////////mapper olmasa asagidaki kodlar ile  bir bir yazmalisan hamisini
//        studentdto a=new studentdto();
//        a.setAge(byid.getAge());
//        a.setName(byid.getName());
//        a.setId(byid.getId());
//        return a;
    }

//    @Override
//    public bookdto createbook(bookdto dto) {
//        book book=mapper.map(dto,book.class);
//        book save=repository.save(book);
//        return mapper.map(save,bookdto.class);
//
//    }

//    @Override
//    public Bookdto createbook(Bookdto dto) {
//        book book = mapper.map(dto, book.class);
//        Author author = book.getAuthor();
//
//        author = authorrepository.findById(author.getId()).orElse(null);
//        book.setAuthor(author);
//
//        publisher publisher = book.getPublisher();
//        publisher = publisherrepository.findById(publisher.getId()).orElse(null);
//        book.setPublisher(publisher);
//
//        book savedBook = repository.save(book);
//        return mapper.map(savedBook, Bookdto.class);
//
//
//    }

    @Override
    public Bookdto createbook(Bookdto dto) {
        book book = mapper.map(dto, book.class);
        Author author = book.getAuthor();
//NECE EDEK KI HER DEFE YOXLAMASIN CHILD CLASS MEMBER VAR YA YOX.BIR DEFE SORGU GETSIN SONRA GELEN ALL LIST
//        ILE CHECK EDEK

        List<book> allbook = repository.findAll();
        Author author1 = null;
        for (book x : allbook) {
            if (author.getId() == x.getId()) {
                author1 = x.getAuthor();
            }
        }
        book.setAuthor(author1);


        publisher publisher = book.getPublisher();
        publisher publisher1=null;
        for (book x : allbook) {
            if (publisher.getId() == x.getId()) {
                publisher1 = x.getPublisher();
            }
        }
      //  publisher = publisherrepository.findById(publisher.getId()).orElse(null);
        book.setPublisher(publisher1);

        book savedBook = repository.save(book);
        return mapper.map(savedBook, Bookdto.class);


    }


    @Override
    public void deletebook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Bookdto updatebook(Bookdto dto) {
        book book = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("data tapilmadi"));
        book.setYear(dto.getYear());
        book.setName(dto.getName());/// BUNLARI MAPPER ILE YOXLA
        book save = repository.save(book);
        return mapper.map(save, Bookdto.class);
    }

    @Override
    public List<Bookdto> findByPub_Name(String name) {
        return repository.findBypublisher(name);
    }

    @Override
    public List<book> findByname(String name) {

        return repository.findByname(name);
    }

//    @Override
//    public List<book> findBynamewp(String name,Integer year ,Integer count,String signyear,String signcount) {
//
//        return repository.findBynamewp(name, year,count,signyear,signcount);
//    }

//    @Override
//    public List<book> findBynamewp(String name,Integer year ,Integer count,String signyear,String signcount) {
//
//        return repository.findBynamewp2(name, year ,count,signyear);
//    }

    @Override
    public List<book> findnameandyear(String name) {

       // return repository.findByname2(name,pageable);
        return repository.findbook(name);
    }

//    @Override
//    public List<book> findBookByName(String name) {
//        return repository.findBookByName(name);
//    }


}
