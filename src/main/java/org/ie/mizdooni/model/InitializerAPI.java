package org.ie.mizdooni.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.Float;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ie.mizdooni.dao.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class BaseModelDownloader<ModelType extends BaseModel> {
    protected static String BASE_URL = "http://91.107.137.117:55/";
    // protected static String BASE_URL = "http://localhost:5500/";

    protected abstract String getModelUrl();

    protected List<Map<String, Object>> getJsonsFromUrl(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
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

    protected Field findFieldFor(String key, ModelType newObject) {
        Class<?> clazz = newObject.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (key.equals(field.getName())) {
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    protected ModelType convertMap(Map<String, Object> jsonMap) {
        try {
            var newObject = generateNewModelInstance();
            for (var key : jsonMap.keySet()) {
                var field = findFieldFor(key, newObject);
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
    TableDao dao = new TableDao();

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
        newMap.remove("tableNumber");
        return newMap;
    }

    protected TableModel generateNewModelInstance() {
        return new TableModel();
    }

    protected List<TableModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected String getModelUrl() {
        return BASE_URL + "tables";
    }

    protected void addObject(TableModel object) {
        dao.create(object);
    }
}

class RestaurantDownloader extends BaseModelDownloader<RestaurantModel> {
    RestaurantDao restaurantDao = new RestaurantDao();

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
        return jsonMaps.parallelStream().map(mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(RestaurantModel object) {
        restaurantDao.create(object);
    }
}

class UserDownloader extends BaseModelDownloader<UserModel> {
    protected String getModelUrl() {
        return BASE_URL + "users";
    }

    ClientUserDao userDao = new ClientUserDao();

    protected UserModel generateNewModelInstance() {
        return new ClientUserModel();
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        Map<String, Object> addressMap = (Map<String, Object>) jsonMap.get("address");
        String city = (String) addressMap.get("city");
        String country = (String) addressMap.get("country");
        jsonMap.remove("address");
        jsonMap.put("city", city);
        jsonMap.put("country", country);
        jsonMap.remove("role");

        return jsonMap;
    }

    protected List<UserModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(UserModel object) {
        userDao.create((ClientUserModel) object);
    }
}

class ReviewDownloader extends BaseModelDownloader<ReviewModel> {
    private ReviewDao dao = new ReviewDao();

    protected String getModelUrl() {
        return BASE_URL + "reviews";
    }

    protected ReviewModel generateNewModelInstance() {
        return new ReviewModel();
    }

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap) {
        String[] rateKeys = { "foodRate", "serviceRate", "ambianceRate", "overallRate" };

        for (var key : rateKeys) {
            jsonMap.replace(key, Float.valueOf(jsonMap.get(key).toString()));
        }

        return jsonMap;
    }

    protected List<ReviewModel> convertJsonsToModels(List<Map<String, Object>> jsonMaps) {
        return jsonMaps.parallelStream().map(mapIter -> convertMap(fixFieldNameAndTypes(mapIter))).toList();
    }

    protected void addObject(ReviewModel object) {
        // ReviewModel.addObject(object);
        dao.create(object);
    }
}

class ReservationReader {
    private ReservationDao dao = new ReservationDao();
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
//                ReserveTableModel.addObject(iter);
                dao.create(iter);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

public class InitializerAPI {

    public void initializeModels() {
        var downloaders = Arrays.asList(new RestaurantDownloader(), new TableDownloader(), new UserDownloader(),
                new ReviewDownloader());
        for (var iter : downloaders) {
            iter.importDataToModel();
        }

//        var reserveImporter = new ReservationReader();
//        reserveImporter.importDataToModel();
        //
        // var user = UserModel.findByUsername("MohammadJavad_Afsari");
        // UserModel.setLoginnedUser(user);
    }
}
