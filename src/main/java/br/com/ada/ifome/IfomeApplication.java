package br.com.ada.ifome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@AllArgsConstructor
@SpringBootApplication
public class IfomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfomeApplication.class, args);
	}

}
