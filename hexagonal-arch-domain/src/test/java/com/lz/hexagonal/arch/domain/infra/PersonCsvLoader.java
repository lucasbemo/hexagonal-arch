package com.lz.hexagonal.arch.domain.infra;

import com.lz.hexagonal.arch.domain.person.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonCsvLoader {
    private final String filePath = "people.csv";

    public List<Person> loadPersonsFromCsv() throws IOException {
        List<Person> persons = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            // Skip header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                Person person = getPerson(line);
                persons.add(person);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV data from " + filePath, e);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSV data: " + e.getMessage(), e);
        }

        return persons;
    }

    private static Person getPerson(String line) {
        String[] values = line.split(",");

        String id = values[0].trim();
        String name = values[1].trim();
        String email = values[2].trim();
        String cpf = values[3].trim();
        String phone = values[4].trim();
        String birthDateStr = values[5].trim();
        String createdAt = values[6];

        // Assuming you have a method to parse LocalDate from String
        return new Person(
                UUID.fromString(id), name, email, cpf, phone, LocalDate.parse(birthDateStr)
                , LocalDateTime.parse(createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}