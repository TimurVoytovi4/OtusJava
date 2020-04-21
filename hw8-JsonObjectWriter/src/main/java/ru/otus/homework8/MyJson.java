package ru.otus.homework8;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class MyJson {

    public void toJson(User[] users) {
        var ab = Json.createArrayBuilder();
        for (User user : users) {
            var ob = Json.createObjectBuilder();
            ob.add("name", user.getName());
            ob.add("lastName", user.getLastName());
            var listAb = Json.createArrayBuilder();
            for (int val : user.getParameters()) {
                listAb.add(val);
            }
            ob.add("parameters", listAb);
            var mapOb = Json.createArrayBuilder();
            for (Map.Entry<String, Integer> elem : user.getContacts().entrySet()) {
                var mapElem = Json.createObjectBuilder();
                mapElem.add(elem.getKey(), elem.getValue());
                mapOb.add(mapElem);
            }
            ob.add("contacts",mapOb);
            ab.add(ob);
        }

        var jsonArray = ab.build();

        var config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        var jwf = Json.createWriterFactory(config);
        var sw = new StringWriter();

        try (var jsonWriter = jwf.createWriter(sw)) {

            jsonWriter.writeArray(jsonArray);

            System.out.println(sw);
        }
    }
}

