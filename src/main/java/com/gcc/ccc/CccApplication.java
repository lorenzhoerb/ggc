package com.gcc.ccc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CccApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> data = readFile("level2/level2_5.in");
		int mapSize = Integer.parseInt(data.get(0));
		int coordinatesAmount = Integer.parseInt(data.get(mapSize + 1));
		char[][] map = new char[mapSize][mapSize];
		for (int i = 0; i < mapSize;i++){
			map[i] = data.get(i+1).toCharArray();
		}
		// Define the output file path
		String outputPath = Paths.get("src", "main", "resources", "output.txt").toString();

		try {
			FileWriter writer = new FileWriter(outputPath);
			for (int i = mapSize + 2; i < mapSize + 2 + coordinatesAmount; i++) {
				int[][] coordinates = parseCoordinates(data.get(i));

				IslandChecker ic = new IslandChecker(map);
				// Write to the output file
				String tmp = ic.areOnSameIsland(coordinates[0], coordinates[1]) ? "SAME" : "DIFFERENT";
				writer.write(tmp + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int[][] parseCoordinates(String line) {
		String[] stringCoordinates = line.split(" ");
		int[][] coordinates = new int[stringCoordinates.length][2];
		for (int i = 0; i < stringCoordinates.length; i++) {
			String[] strCoordinates = stringCoordinates[i].split(",");
			int x = Integer.parseInt(strCoordinates[0]);
			int y = Integer.parseInt(strCoordinates[1]);
			coordinates[i][0] = x;
			coordinates[i][1] = y;
		}
		return coordinates;
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

	private boolean shipRouteTwice(int[][] route){
		for (int i = 0; i < route.length; i++) {
			for (int j = i+1; j < route.length; j++) {
				if(same(route[i], route[j]));
				return false;
			}
		}
		return true;
	}
	private boolean same(int[] r1, int[] r2){
		return (r1[0] == r2[0] && r1[1] == r2[1]);
	}
}
