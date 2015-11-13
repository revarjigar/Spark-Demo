package main.java;


import static spark.Spark.get;
import static spark.Spark.post;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import spark.Request;
import spark.Response;
import spark.Route;

import main.java.*;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App
{

    private static final int HTTP_BAD_REQUEST = 400;

    interface Validable {
        boolean isValid();
    }

    @Data
    static class NewPostPayload {
        private String firstName;
        private String lastName;
        private String age;
        private String gender;
        private String phone;

        public boolean isValid() {
        	return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && 
        			 age != null && !age.isEmpty() &&  gender != null && !gender.isEmpty() &&
        					 phone != null && !phone.isEmpty();
        }
    }

    public static class Model {
        private int nextId = 1;
        private Map<Integer, Post> posts = new HashMap<>();

        @Data
        class Post {
            private int id;
            private String firstName;
            private String lastName;
            private String age;
            private String gennder;
            private String phone;
        }

        public int createPost(String firstName,String lastName, String age,String gender,String phone){
            int id = nextId++;
            Post post = new Post();
            post.setId(id);
            post.setFirstName(firstName);
            post.setLastName(lastName);
            post.setAge(age);
            post.setGender(gender);
            post.setPhone(phone);
            posts.put(id, post);
            return id;
        }

        public List<Post> getAllPosts(){
            return posts.keySet().stream().sorted().map((id) -> posts.get(id)).collect(Collectors.toList());
        }
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            return sw.toString();
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public static void main( String[] args) {
        Model model = new Model();

        post("/posts", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                NewPostPayload creation = mapper.readValue(request.body(), NewPostPayload.class);
                if (!creation.isValid()) {
                    response.status(HTTP_BAD_REQUEST);
                    return "";
                }
                int id = model.createPost(creation.getFirstName(), creation.getlastName(), creation.getAge(),creation.getGender(),creation.getPhone());
                response.status(200);
                response.type("application/json");
                return id;
            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return "";
            }
        });

        get("/posts", (request, response) -> {
            response.status(200);
            response.type("application/json");
            return dataToJson(model.getAllPosts());
        });
    }
}
