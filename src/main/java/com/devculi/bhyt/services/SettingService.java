package com.devculi.bhyt.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SettingService {
    public static final String FILE_NAME = "setting.json";
    public static double BASE_BY_PERCENT;
    public static double MIN_POORER_SUPPORT_IN_PERCENT;
    public static double MIN_STUDENT_SUPPORT_IN_PERCENT;
    public static double MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT;
    public static double FIRST_PERSON_RATE;
    public static double SECOND_PERSON_RATE;
    public static double THIRD_PERSON_RATE;
    public static double FOURTH_PERSON_RATE;
    public static double FROM_FIFTH_PERSON_RATE;

    public Object readConfig() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            //Read JSON file
            JSONObject configObject = (JSONObject) jsonParser.parse(reader);
            BASE_BY_PERCENT = (double) configObject.get("BASE_BY_PERCENT");
            MIN_POORER_SUPPORT_IN_PERCENT = (double) configObject.get("MIN_POORER_SUPPORT_IN_PERCENT");
            MIN_STUDENT_SUPPORT_IN_PERCENT = (double) configObject.get("MIN_STUDENT_SUPPORT_IN_PERCENT");
            MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT = (double) configObject.get("MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT");
            FIRST_PERSON_RATE = (double) configObject.get("FIRST_PERSON_RATE");
            SECOND_PERSON_RATE = (double) configObject.get("SECOND_PERSON_RATE");
            THIRD_PERSON_RATE = (double) configObject.get("THIRD_PERSON_RATE");
            FOURTH_PERSON_RATE = (double) configObject.get("FOURTH_PERSON_RATE");
            FROM_FIFTH_PERSON_RATE = (double) configObject.get("FROM_FIFTH_PERSON_RATE");
            return configObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public void updateConfig() {
        JSONObject newConfig = new JSONObject();
        writeConfig(newConfig);
    }


    private void writeConfig(JSONObject newConfig) {
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(newConfig.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getConfigAsObject() {
        Map map = new HashMap<String, Object>();
        map.put("BASE_BY_PERCENT", BASE_BY_PERCENT);
        map.put("MIN_POORER_SUPPORT_IN_PERCENT", MIN_POORER_SUPPORT_IN_PERCENT);
        map.put("MIN_STUDENT_SUPPORT_IN_PERCENT", MIN_STUDENT_SUPPORT_IN_PERCENT);
        map.put("MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT", MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT);
        map.put("FIRST_PERSON_RATE", FIRST_PERSON_RATE);
        map.put("SECOND_PERSON_RATE", SECOND_PERSON_RATE);
        map.put("THIRD_PERSON_RATE", THIRD_PERSON_RATE);
        map.put("FOURTH_PERSON_RATE", FOURTH_PERSON_RATE);
        map.put("FROM_FIFTH_PERSON_RATE", FROM_FIFTH_PERSON_RATE);
        return map;
    }
}
