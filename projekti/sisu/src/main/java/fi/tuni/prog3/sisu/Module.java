package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * Module represents a module in the Sisu API. It is used to store and fetch
 * data.
 * 
 */
public class Module {
    String name;
    String id;
    int targetCredits;
    String outcomes;
    String type;
    // String curriculumPeriodIds;
    // String validityPeriod;
    HashMap<String, Module> modules;
    HashMap<String, Course> courses;

    /**
     * Constructor for a new module
     * 
     * @param id is the id of the new module. Is used to differentiate.
     */
    public Module(String id) {
        this.id = id;
        modules = new HashMap<>();
        courses = new HashMap<>();
        decodeJson();
    }

    /**
     * @return this modules name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return this modules credits.
     */
    public int getTargetCredits() {
        return targetCredits;
    }

    /**
     * @return this modules outcomes.
     */
    public String getOutcomes() {
        return outcomes;
    }

    /**
     * TODO: oikein?
     * @return this modules HashMap<String, Module> containing neighbouring modules.
     */
    public HashMap<String, Module> getModules() {
        return modules;
    }

    /**
     * @return this modules HashMap<String, Course> containing this modules courses.
     */
    public HashMap<String, Course> getCourses() {
        return courses;
    }

    /**
     * @return this modules type.
     */
    public String getType() {
        return type;
    }

    /**
     * TODO:dokumentoi
     * 
     */
    private void decodeJson() {
        try {
            StringBuilder sb = new StringBuilder();
            JsonObject obj;
            if (id.startsWith("otm")) {
                GetJsonData getJson_Module = new GetJsonData(2, id);
                sb = getJson_Module.getJsonDataFromURL();
                obj = JsonParser.parseString(sb.toString()).getAsJsonObject();
            } else {
                GetJsonData getJson_Module = new GetJsonData(3, id);
                sb = getJson_Module.getJsonDataFromURL();
                obj = JsonParser.parseString(sb.toString()).getAsJsonArray().get(0).getAsJsonObject();
            }

            if (obj.getAsJsonObject("name").getAsJsonPrimitive("fi") == null) {
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("en").getAsString();
            }

            else {
                this.name = obj.getAsJsonObject("name").getAsJsonPrimitive("fi").getAsString();
            }
            this.type = obj.get("type").getAsString();

            switch (obj.getAsJsonPrimitive("type").getAsString()) {
                case "GroupingModule":
                    break;
                case "StudyModule":
                    if (!obj.get("targetCredits").isJsonNull()) {
                        this.targetCredits = obj.getAsJsonObject("targetCredits").getAsJsonPrimitive("min").getAsInt();
                    }

                    if (!obj.get("outcomes").isJsonNull()) {
                        if (obj.getAsJsonObject("outcomes").getAsJsonPrimitive("fi") == null) {
                            this.outcomes = obj.getAsJsonObject("outcomes").getAsJsonPrimitive("en").getAsString();
                        } else {
                            this.outcomes = obj.getAsJsonObject("outcomes").getAsJsonPrimitive("fi").getAsString();
                        }
                    }

                    break;
            }

            // Rulet läpi
            JsonArray arr = null;
            String rule = obj.getAsJsonObject("rule").get("type").getAsString();
            if (rule.equals("CreditsRule")) {
                arr = obj.getAsJsonObject("rule").getAsJsonObject("rule").getAsJsonArray("rules");
            } else if (rule.equals("CompositeRule")) {
                arr = obj.getAsJsonObject("rule").getAsJsonArray("rules");
            }
            compositeRule(arr);
        } catch (MalformedURLException e) {
        } catch (IOException e2) {
        }
    }

    /**
     * TODO: dokumentoi
     * @param arr
     */
    private void compositeRule(JsonArray arr) {
        Iterator<JsonElement> it = arr.iterator();
        while (it.hasNext()) {
            JsonObject jObject = it.next().getAsJsonObject();
            String type = jObject.get("type").getAsString();
            switch (type) {
                case "CompositeRule":
                    compositeRule(jObject.getAsJsonArray("rules"));
                    break;
                case "ModuleRule":
                    String moduleGroupId = jObject.get("moduleGroupId").getAsString();
                    Module m = new Module(moduleGroupId);
                    modules.put(m.getName(), m);
                    break;
                case "CourseUnitRule":
                    String courseId = jObject.get("courseUnitGroupId").getAsString();
                    Course c = new Course(courseId);
                    courses.put(c.getName(), c);
                    break;
                default:
                    break;
            }
        }

    }
}
