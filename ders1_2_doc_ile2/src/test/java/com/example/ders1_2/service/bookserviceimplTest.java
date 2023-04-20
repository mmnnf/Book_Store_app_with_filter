package com.example.ders1_2.service;

import com.example.ders1_2.dto.Bookdto;
import com.example.ders1_2.model.Author;
import com.example.ders1_2.model.book;
import com.example.ders1_2.repository.bookrepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class bookserviceimplTest {
    @Mock
//    @MockBean(name="repository")
    private bookrepository repository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    bookserviceimpl servis;

    @Test
    public void givenIdWhenGetProductThenSuccess() {
        //Arange
        book book = new book();
        book.setYear(1998);
        book.setName("beyaz_zanbaklar_olkesi");
        Author aut = Author.builder()
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        book.setAuthor(aut);

        Bookdto bookdto = new Bookdto();
        bookdto.setYear(1998);
        bookdto.setName("beyaz_zanbaklar_olkesi");
        Author aut2 = Author.builder()
                .id(1l)
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        bookdto.setAuthor(aut2);
        when(repository.findById(anyLong())).thenReturn(Optional.of(book));
        when(mapper.map(book, Bookdto.class)).thenReturn(bookdto);

        //Akt

        Bookdto response = servis.getbookbyid(1l);

        //Assert

        Assertions.assertEquals(response.getName(), book.getName());
        Assertions.assertEquals(response.getYear(), book.getYear());
        Assertions.assertEquals(response.getAuthor().getName(), book.getAuthor().getName());
    }

    @Test
    public void WhenCreateThenSuccess2() {
        //Arange
        book book = new book();
        book.setYear(1998);
        book.setName("beyaz_zanbaklar_olkesi");
        Author aut = Author.builder()
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        book.setAuthor(aut);

        Bookdto bookdto = new Bookdto();
        bookdto.setYear(1998);
        bookdto.setName("beyaz_zanbaklar_olkesi");
        Author aut2 = Author.builder()
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        bookdto.setAuthor(aut2);
        when(mapper.map(bookdto, book.class)).thenReturn(book);
        when(mapper.map(book, Bookdto.class)).thenReturn(bookdto);
        when(repository.save(any())).thenReturn(book);

        //Akt
        Bookdto b1 = servis.createbook(bookdto);

        //Assert
        Assertions.assertEquals(b1, bookdto);

    }

    @Test
    public void WhenGetAllThenSuccess() {
        //Arange

        book book = new book();
        book.setYear(1998);
        book.setName("beyaz_zanbaklar_olkesi");
        Author aut = Author.builder()
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        book.setAuthor(aut);

        book book2 = new book();
        book2.setYear(1998);
        book2.setName("beyaz_zanbaklar_olkesi2");
        Author aut2 = Author.builder()
                .name("jhon 2")
                .surname("stevan 2")
                .birthdate(1978)
                .build();
        book.setAuthor(aut);

        Bookdto bookdto = new Bookdto();
        bookdto.setYear(1998);
        bookdto.setName("beyaz_zanbaklar_olkesi");
        Author aut3 = Author.builder()
                .name("jhon ")
                .surname("stevan")
                .birthdate(1977)
                .build();
        bookdto.setAuthor(aut3);

        Bookdto bookdto2 = new Bookdto();
        bookdto2.setYear(1998);
        bookdto2.setName("beyaz_zanbaklar_olkesi2");
        Author aut4 = Author.builder()
                .name("jhon 2")
                .surname("stevan 2")
                .birthdate(1978)
                .build();
        bookdto2.setAuthor(aut4);

        when(mapper.map(bookdto, book.class)).thenReturn(book);
        when(mapper.map(book, Bookdto.class)).thenReturn(bookdto);
        when(mapper.map(bookdto2, book.class)).thenReturn(book2);
        when(mapper.map(book2, Bookdto.class)).thenReturn(bookdto2);
        when(repository.save(any())).thenReturn(book);
        when(repository.findAll()).thenReturn(List.of(book,book2));

        //Akt
        Bookdto b1 = servis.createbook(bookdto);
        Bookdto b2 = servis.createbook(bookdto2);
        List<Bookdto> result=servis.allbooks();

        //Assert
        Assertions.assertEquals(result, List.of(bookdto,bookdto2));
    }
}


