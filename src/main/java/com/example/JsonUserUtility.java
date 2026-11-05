package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUserUtility {

    public static void main(String[] args) {
        File file = new File("users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // formatted JSON

        try {
            // 2. Read JSON array -> List<User>
            List<User> users = mapper.readValue(file, new TypeReference<List<User>>() {});
            System.out.println("Before update:");
            users.forEach(System.out::println);

            // 3. Modify entries programmatically
            for (User u : users) {
                if (u.getId() == 1) {
                    u.setName("Pranjal Updated");
                    u.setEmail("pranjal.new@example.com");
                }
                if (u.getAge() < 22) {
                    u.setAge(u.getAge() + 1);
                }
            }

            // 4. Write back as formatted JSON
            mapper.writeValue(file, users);

            System.out.println("\nAfter update:");
            users.forEach(System.out::println);
            System.out.println("\nusers.json updated successfully!");

        } catch (IOException e) {
            // 5. Error handling for I/O
            System.err.println("File read/write error: " + e.getMessage());
        }
    }
}