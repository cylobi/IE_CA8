package org.ie.mizdooni.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.ie.mizdooni.model.UserModel;
import java.io.IOException;

public class CommandResponse {

    public boolean success;
    public Object data;

    public CommandResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
    public CommandResponse(boolean success, String data) {
        this.success = success;
        this.data = data;
    }



    public String toString(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            var jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
            return jsonString;
        }

        catch (JsonParseException e) { e.printStackTrace();}
        catch (JsonMappingException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        return "";
    }
}
