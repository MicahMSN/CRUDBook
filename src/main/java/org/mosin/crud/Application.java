package org.mosin.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book("Война и мир1", "Про мир и про войну", "Лев Толстой", "9781977684233", "1869", false));
			bookRepository.save(new Book("Идиот", "Про слабоумие", "Достоевский", "9782070107155", "1868", true));
			bookRepository.save(new Book("Война и мир2", "Про мир и про войну", "Лев Толстой", "9781977633233", "1869", false));
			bookRepository.save(new Book("Колобок", "Про хлеб", "Народ", "9782070107335", "1111", true));
			bookRepository.save(new Book("Курочка Ряба", "Про яйцо", "Народ", "9782072207335", "1001", false));
			bookRepository.save(new Book("PEAA", "Что-то интересное", "ФАУЛЕР", "9782072207310", "2002", false));
			bookRepository.save(new Book("XP программирование", "Тесты", "Кен Бек", "9782072207323", "2012", false));
			bookRepository.save(new Book("Философия Java", "Не про кофе", "Брюс Екел", "9782072207322", "1998", false));
			bookRepository.save(new Book("Design Patterns", "Скелеты в шкафу", "Банда четырех", "9782072207311", "1995", false));
			bookRepository.save(new Book("Spring in Action", "О любви к весне", "Крейг Уоллс", "9782072207123", "2005", false));
		};
	}

}
