package org.ie.mizdooni.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class BaseModelDownloader<ModelType extends BaseModel> {
    protected static String BASE_URL = "http://127.0.0.1:5500/";
    // protected static String BASE_URL = "http://localhost:5500/";

    protected abstract String getModelUrl();

    protected List<Map<String, Object>> getJsonsFromUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
        String jsonBody = response.getBody();
        System.out.print(jsonBody);
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> l = mapper.readValue(jsonBody, List.class);
            System.out.println(l);
            return l;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    abstract protected ModelType generateNewModelInstance();

    protected ModelType convertMap(Map<String, Object> jsonMap) {
        try {
            var newObject = generateNewModelInstance();
            for (var key : jsonMap.keySet()) {
                var field = newObject.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(newObject, jsonMap.get(key));
            }
            System.out.println(newObject);
            return newObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void printEndMessage(List<ModelType> modelList) {
        System.out.print(modelList.size());
        System.out.println(" " + this.getClass().getName() + " imported.");
    }

    protected void printStartMessage() {
        System.out.println("Downloading " + this.getClass().getName());
    }

    protected abstract List<ModelType> convertJsonsToModels(List<Map<String, Object>> jsonMaps);

    protected void importDataToModel() {
        printStartMessage();
        var fetchedData = getJsonsFromUrl(getModelUrl());
        var modelList = convertJsonsToModels(fetchedData);

        for (var iter : modelList) {
            addObject((ModelType) iter);
        }

        printEndMessage(modelList);
    }

    abstract protected void addObject(ModelType object);
}

class TableDownloader extends BaseModelDownloader<TableModel> {
    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        var newMap = new LinkedHashMap<String, Object>();
        for (var oldKey : jsonMap.keySet()) {
            var oldValue = jsonMap.get(oldKey);
            String newKey = oldKey;
            Object newValue = oldValue;
            if (oldKey.equals("managerUserName")) {
                newKey = "managerUsername";
            }

            newMap.put(newKey, newValue);
        }
        return newMap;
    }

    protected TableModel generateNewModelInstance() {
        return new TableModel();
    }

    protected List<TableModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(
                mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected String getModelUrl() {
        return BASE_URL + "tables";
    }

    protected void addObject(TableModel object) {
        TableModel.addObject(object);
    }
}

class RestaurantDownloader extends BaseModelDownloader<RestaurantModel> {
    protected String getModelUrl() {
        return BASE_URL + "restaurants";
    }

    protected RestaurantModel generateNewModelInstance() {
        return new RestaurantModel();
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        Map<String, Object> addressMap = (Map<String, Object>) jsonMap.get("address");
        var restaurantAddress = new RestaurantAddress();
        restaurantAddress.city = (String) addressMap.get("city");
        restaurantAddress.country = (String) addressMap.get("country");
        restaurantAddress.street = (String) addressMap.get("street");
        jsonMap.replace("address", restaurantAddress);

        String imageUrl = (String) jsonMap.get("image");
        jsonMap.remove("image");
        jsonMap.put("imageUrl", imageUrl);

        return jsonMap;
    }

    protected List<RestaurantModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(
                mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(RestaurantModel object) {
        RestaurantModel.addObject(object);
    }
}

class UserDownloader extends BaseModelDownloader<UserModel> {
    protected String getModelUrl() {
        return BASE_URL + "users";
    }

    protected UserModel generateNewModelInstance() {
        return new UserModel();
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        Map<String, Object> addressMap = (Map<String, Object>) jsonMap.get("address");
        var restaurantAddress = new UserModel.UserAddress();
        restaurantAddress.city = (String) addressMap.get("city");
        restaurantAddress.country = (String) addressMap.get("country");
        jsonMap.replace("address", restaurantAddress);

        var roleObject = jsonMap.get("role").equals("client") ? UserModel.UserRole.CLIENT : UserModel.UserRole.MANAGER;
        jsonMap.replace("role", roleObject);

        return jsonMap;
    }

    protected List<UserModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(
                mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(UserModel object) {
        UserModel.addObject(object);
    }
}

class ReviewDownloader extends BaseModelDownloader<ReviewModel> {
    protected String getModelUrl() {
        return BASE_URL + "reviews";
    }

    protected ReviewModel generateNewModelInstance() {
        return new ReviewModel();
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {

        return jsonMap;
    }

    protected List<ReviewModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(
                mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(ReviewModel object) {
        ReviewModel.addObject(object);
    }
}

class ReservationReader {
    public String readFileContent() {
        String filePath = "asset/reservations";
        try {
            String content = Files.readString(Paths.get(filePath));
            return content;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return "";
    }

    protected ReserveTableModel convertMap(Map<String, Object> jsonMap) {
        try {
            var newObject = new ReserveTableModel();

            for (var key : jsonMap.keySet()) {
                var field = newObject.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(newObject, jsonMap.get(key));
            }
            System.out.println(newObject);
            return newObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        String datetimeString = (String) jsonMap.get("datetime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date date = formatter.parse(datetimeString);
            jsonMap.replace("datetime", date);
        } catch (ParseException e) {
        }
        return jsonMap;
    }

    public void importDataToModel() {
        String content = readFileContent();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Map<String, Object>> l = mapper.readValue(content, List.class);
            System.out.println(l);
            mapper = new ObjectMapper();
            var objects = l.parallelStream().map(mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
            for (var iter : objects) {
                ReserveTableModel.addObject(iter);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

public class InitializerAPI {

    public void initializeModels() {
        var downloaders = Arrays.asList(new TableDownloader(), new RestaurantDownloader(), new UserDownloader(),
                new ReviewDownloader());
        for (var iter : downloaders) {
            iter.importDataToModel();
        }

        var reserveImporter = new ReservationReader();
        reserveImporter.importDataToModel();

        var user = UserModel.findByUsername("MohammadJavad_Afsari");
        UserModel.setLoginnedUser(user);
    }
}
