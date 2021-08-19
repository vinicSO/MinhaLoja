package br.com.minhaloja;

import br.com.minhaloja.domain.*;
import br.com.minhaloja.enums.EstadoPagamento;
import br.com.minhaloja.enums.TipoCliente;
import br.com.minhaloja.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class MinhaLojaApplication{

	public static void main(String[] args) {
		SpringApplication.run(MinhaLojaApplication.class, args);
	}
}
