package com.jax;

import com.jax.grpc.proto.Author;
import com.jax.grpc.proto.Book;

import java.util.ArrayList;
import java.util.List;

public class TempDb {
    public static List<Author> getAuthorsFromTempDb() {
        return new ArrayList<Author>() {
            {
                add(Author.newBuilder().setAuthorId(1).setBookId(1).setFirstName("Xim").setLastName("Nguyen Thi").setGender("Nu").build() );
                add(Author.newBuilder().setAuthorId(2).setBookId(2).setFirstName("Xim Xim").setLastName("Nguyen Thi").setGender("Nu").build() );
                add(Author.newBuilder().setAuthorId(3).setBookId(3).setFirstName("Xim Xim Xim").setLastName("Nguyen Thi").setGender("Nu").build() );
                add(Author.newBuilder().setAuthorId(4).setBookId(4).setFirstName("Xim Xim Xim Xim").setLastName("Nguyen Thi").setGender("Nu").build() );
                add(Author.newBuilder().setAuthorId(5).setBookId(5).setFirstName("Xim Xim Xim Xim Xim").setLastName("Nguyen Thi").setGender("Nu").build() );
            }
        };
    }
    public static List<Book> getBooksFromTempDb() {
        return new ArrayList<Book>(){
            {
                add(Book.newBuilder().setBookId(1).setTitle("Book Xim").setPrice(100f).setPages(20).setAuthorId(1).build());
                add(Book.newBuilder().setBookId(2).setTitle("Book Xim Xim").setPrice(1000f).setPages(201).setAuthorId(2).build());
                add(Book.newBuilder().setBookId(3).setTitle("Book Xim Xim Xim").setPrice(10000f).setPages(202).setAuthorId(3).build());
                add(Book.newBuilder().setBookId(4).setTitle("Book Xim Xim Xim Xim").setPrice(100000f).setPages(203).setAuthorId(4).build());
                add(Book.newBuilder().setBookId(5).setTitle("Book Xim Xim Xim Xim Xim").setPrice(1000000f).setPages(204).setAuthorId(5).build());
            }
        };
    }
}
