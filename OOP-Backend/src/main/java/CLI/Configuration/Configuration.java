package CLI.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {
    int maxTicketCapacity;
    int totalTickets;
    int ticketReleaseRate;
    int customerRetrievalRate;


    // Method to save configuration data to JSON
    public void saveToJson(String filePath) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        }
    }

    // Method to load configuration data from JSON
    public Configuration loadFromJson(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        }
    }
}


