package fi.tuni.prog3.sisu;

import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the SisuAPI interface.
 */
class SisuAPIImpl implements iAPI {
    public JsonObject getJsonObjectFromApi(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return JsonParser.parseString(response.toString()).getAsJsonObject();
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> fetchModuleGroupIdsFromAPI(String apiUrl) {
        List<String> moduleGroupIds = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject program = element.getAsJsonObject();
                    JsonObject rule = program.getAsJsonObject("rule");
                    JsonArray moduleRules = rule.getAsJsonArray("rules");
                    if (moduleRules != null) {
                        for (JsonElement moduleRule : moduleRules) {
                            JsonObject module = moduleRule.getAsJsonObject();
                            JsonElement moduleGroupIdElement = module.get("moduleGroupId");
                            if (moduleGroupIdElement != null && !moduleGroupIdElement.isJsonNull()) { // Add null check
                                String moduleGroupId = moduleGroupIdElement.getAsString();
                                moduleGroupIds.add(moduleGroupId);
                            }
                        }
                    }
                }
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moduleGroupIds;
    }

    public List<StudyModule> fetchStudyModulesFromAPI(String apiUrl) {
        List<StudyModule> studyModules = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject program = element.getAsJsonObject();


                    JsonObject nameObject = program.getAsJsonObject("name");
                    JsonElement nameElement = nameObject.get("en");
                    String name = nameElement != null && !nameElement.isJsonNull() ? nameElement.getAsString() : "";
                    if (name.isEmpty()) {
                        nameElement = nameObject.get("fi");
                        name = nameElement != null && !nameElement.isJsonNull() ? nameElement.getAsString() : "";
                    }

                    // Extract the moduleGroupIds
                    JsonObject rule = program.getAsJsonObject("rule");
                    JsonArray moduleRules = rule != null ? rule.getAsJsonArray("rules") : null;
                    List<String> moduleGroupIds = new ArrayList<>();
                    if (moduleRules != null) {
                        for (JsonElement moduleRule : moduleRules) {
                            JsonObject module = moduleRule.getAsJsonObject();
                            JsonElement moduleGroupIdElement = module.get("moduleGroupId");
                            if (moduleGroupIdElement != null && !moduleGroupIdElement.isJsonNull()) {
                                if (moduleGroupIdElement.isJsonArray()) {
                                    JsonArray moduleGroupIdArray = moduleGroupIdElement.getAsJsonArray();
                                    for (JsonElement groupIdElement : moduleGroupIdArray) {
                                        if (groupIdElement != null && !groupIdElement.isJsonNull()) {
                                            String moduleGroupId = groupIdElement.getAsString();
                                            moduleGroupIds.add(moduleGroupId);
                                        }
                                    }
                                } else if (moduleGroupIdElement.isJsonPrimitive()) {
                                    String moduleGroupId = moduleGroupIdElement.getAsString();
                                    moduleGroupIds.add(moduleGroupId);
                                }
                            }
                        }
                    }


                    StudyModule studyModule = new StudyModule(name, new ArrayList<>(moduleGroupIds));
                    studyModules.add(studyModule);


                }
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studyModules;
    }


    public List<CourseModule> fetchCourseModulesFromAPI(String apiUrl){
        List<CourseModule> courseModules = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    JsonObject program = element.getAsJsonObject();


                    JsonObject nameObject = program.getAsJsonObject("name");
                    JsonElement nameElement = nameObject.get("en");
                    String name = nameElement != null && !nameElement.isJsonNull() ? nameElement.getAsString() : "";
                    if (name.isEmpty()) {
                        nameElement = nameObject.get("fi");
                        name = nameElement != null && !nameElement.isJsonNull() ? nameElement.getAsString() : "";
                    }


                    String code = "";
                    JsonElement codeElement = program.get("code");
                    if (codeElement != null && !codeElement.isJsonNull()) {
                        code = codeElement.getAsString();
                    }
                    JsonObject credits = program.getAsJsonObject("targetCredits");
                    int minCredits = 0;
                    int maxCredits = 0;
                    if (credits != null) {
                        JsonElement minElement = credits.get("min");
                        minCredits = (minElement != null && !minElement.isJsonNull()) ? minElement.getAsInt() : 0;

                        if (credits.has("max")) {
                            JsonElement maxElement = credits.get("max");
                            maxCredits = (maxElement != null && !maxElement.isJsonNull()) ? maxElement.getAsInt() : 0;
                        }
                    }


                    CourseModule courseModule = new CourseModule(name,minCredits,maxCredits,code);
                    courseModules.add(courseModule);

                }
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courseModules;
    }
}
