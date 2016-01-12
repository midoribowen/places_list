import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("places", request.session().attribute("places"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/places", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      ArrayList<Places> places = request.session().attribute("places");

      if (places == null) {
        places = new ArrayList<Places>();
        request.session().attribute("places", places);
      }

      String location = request.queryParams("location");
      Places newPlaces = new Places(location);
      request.session().attribute("place", newPlaces);

      places.add(newPlaces);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
