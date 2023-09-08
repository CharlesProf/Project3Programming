/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Interface for extracting data from the Sisu API.
 */
public interface iAPI {
    public JsonObject getJsonObjectFromApi(String urlString);

    public List<String> fetchModuleGroupIdsFromAPI(String apiUrl);

    public List<StudyModule> fetchStudyModulesFromAPI(String apiUrl);

    public List<CourseModule> fetchCourseModulesFromAPI(String apiUrl);
}
