package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class iReadAndWriteToFileImplement implements iReadAndWriteToFile{

    @Override
    public boolean readFromFile(String fileName, StudentData studentData) throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        try (FileReader reader = new FileReader(fileName)) {
            StudentData loadedData = gson.fromJson(reader, StudentData.class);
            if (loadedData != null) {
                // Assign the loaded data to the provided studentData object
                studentData.setName(loadedData.getName());
                studentData.setId(loadedData.getId());
                studentData.setStartDate(loadedData.getStartDate());
                studentData.setEndDate(loadedData.getEndDate());
                studentData.setSettingsData(loadedData.getSettingsData());
                studentData.setList(loadedData.getList());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Return false if loading fails

    }

    @Override
    public boolean writeToFile(String fileName,StudentData studentData) throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(studentData, writer);
            System.out.println("successfully wrote " +studentData);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}
