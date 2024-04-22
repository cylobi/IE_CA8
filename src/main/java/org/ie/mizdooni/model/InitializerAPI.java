package org.ie.mizdooni.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ie.mizdooni.utils.CommandResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class BaseEndpointGetter {
//    public abstract String getUrl();
    protected List< Map<String, Object> > getObjectsFromApi(String url){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Header", "header1");


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
            List< Map<String, Object> > l = mapper.readValue(jsonBody, List.class);
            System.out.println(l);
            return l;
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }


    protected BaseModel convertMap(Map<String, Object> jsonMap, Class<?> modelClass){
        try {
            var newObject = modelClass.getDeclaredConstructor().newInstance();
            for (var key : jsonMap.keySet()) {
                var field = modelClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(newObject, jsonMap.get(key) );
            }
            System.out.println(newObject);
            return (BaseModel)newObject;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

class TablesGetter extends BaseEndpointGetter{

    protected Map<String, Object> fixFieldNameAndTypes(Map<String, Object> jsonMap){
        var newMap = new LinkedHashMap<String, Object>();
        for (var oldKey : jsonMap.keySet() ){
            var oldValue = jsonMap.get(oldKey);
            String newKey = oldKey;
            Object newValue = oldValue;
            if(oldKey.equals("managerUserName")){
                newKey = "managerUsername";
            }

            newMap.put(newKey, newValue);
        }
        return newMap;
    }

    public List<TableModel> fetch(){
        var fetchedData = getObjectsFromApi("http://91.107.137.117:55/tables");
        System.out.println(fetchedData);
            var mapper = new ObjectMapper();
            var fixedMap = fixFieldNameAndTypes(fetchedData.get(0));
            var modelObject = convertMap(fixedMap, TableModel.class);
            var modelList =
                    fetchedData.parallelStream().map (
                            mapIter ->
                                convertMap( fixFieldNameAndTypes(mapIter), TableModel.class )
                            ).toList();
            for (var iter : modelList){
                TableModel.addObject((TableModel)iter);
            }
            return null;
    }
}

public class InitializerAPI {
    protected String fetchData(String url){
        return "";
    }

    public void initializeModels(){
        var a = new TablesGetter();
        a.fetch();
    }
}
