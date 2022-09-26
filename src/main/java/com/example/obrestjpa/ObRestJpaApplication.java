package com.example.obrestjpa;

import com.example.obrestjpa.entities.Book;
import com.example.obrestjpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestJpaApplication {

	public static void main(String[] args) {
		// contenedor de beans
		SpringApplication.run(ObRestJpaApplication.class, args);


		/*ApplicationContext context = SpringApplication.run(ObRestJpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);


		// CRUD

		//1. Crear libro
		Book book = new Book(null, "TLOR", "Tolkien", 1350, 49.99, LocalDate.of(1978, 10, 1), true);
		Book book2 = new Book(null, "The Hobbit", "Tolkien", 1200, 39.99, LocalDate.of(1988, 10, 1), true);


		//2. Almacenar libro
		repository.save(book);
		repository.save(book2);


		//3. Recuperar libros almacenados
		System.out.println(repository.findAll().size());


		//4. Eliminar un libro
		/*repository.delete(book);
		System.out.println(repository.findAll().size());*/


	}

}
