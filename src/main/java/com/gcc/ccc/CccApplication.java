package com.gcc.ccc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CccApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> data = readFile("level1/level1_2.in");
		int mapSize = Integer.parseInt(data.get(0));
		int coordinatesAmount = Integer.parseInt(data.get(mapSize+1));
		for(int i = mapSize+2; i <  mapSize+2+ coordinatesAmount;i++) {
			String[] coordinate = data.get(i).split(",");
			int x = Integer.parseInt(coordinate[0]);
			int y = Integer.parseInt(coordinate[1]);
			String outLine = "" + data.get(y+1).charAt(x);
			System.out.println(outLine);
		}
	}

	private List<String> readFile(String input) {
		ClassLoader classLoader = getClass().getClassLoader();
		List<String>  data = new ArrayList<>();
		try {
			File myObj = new File(classLoader.getResource(input).getFile());
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data.add(myReader.nextLine());
				System.out.println(data);
			}
			myReader.close();
			return data;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}
}
