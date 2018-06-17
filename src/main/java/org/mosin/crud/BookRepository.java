package org.mosin.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    List<Book> findByTitleStartsWithIgnoreCase(String title);
}
