package com.example.favorites.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class FavoritesWebApplication {

	public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {

		SpringApplication.run(FavoritesWebApplication.class, args);

	}
}
