package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = { "id", "firstName", "lastName", "country", "age" };
        String inputCsvFile = "data.csv";
        String outputJsonFile = "data.json";

        // Вот она, единственная строка объявления + инициализации:
        List <Employee> employees = parseCSV(columnMapping, inputCsvFile);

        String json = listToJson(employees);
        writeToFile(json, outputJsonFile);
        System.out.println(outputJsonFile + " успешно создан.");
    }

    private static List <Employee> parseCSV(String[] mapping, String fileName) {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy <Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(mapping);

            CsvToBean <Employee> csvToBean = new CsvToBeanBuilder <Employee> (reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(0)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения CSV-файла", e);
        }
    }

    private static String listToJson(List <Employee> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken <List <Employee>>() {}.getType();
        return gson.toJson(list, listType);
    }

    private static void writeToFile(String data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка записи JSON-файла", e);
        }
    }
}