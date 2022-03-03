import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;



public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Squad> foundSquad = Squad.getAll();
            model.put("squads",foundSquad);

            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/list", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Squad> squads = Squad.getAll();

            model.put("squads", squads);
            return new ModelAndView(model, "allsquads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int idOfListToFind = Integer.parseInt(request.params("id"));
            Squad foundSquad = Squad.findById(idOfListToFind);
            Hero foundHero = Hero.findById(idOfListToFind);

            model.put("squad",foundSquad);
            model.put("hero", foundHero);

            return new ModelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        // Squads form
        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String squad = request.queryParams("squad");
            String cause = request.queryParams("cause");
            Squad newSquad = new Squad(squad, cause);
            model.put("squad",squad);
            model.put("cause", cause);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // routing Heroes
        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> foundHero = Hero.getAll();
            ArrayList<Squad> allSquads = Squad.getAll();
            model.put("heroes",foundHero);
            model.put("squads", allSquads);

            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());

        // routing All Heroes
        get("/heroes/list", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();

            model.put("heroes", heroes);
            return new ModelAndView(model, "allheroes.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        // routing Heroes form
        post("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String specialPower = request.queryParams("special-power");
            String weakness = request.queryParams("weakness");
            int selectedSquadId = Integer.parseInt(request.queryParams("selected-squad"));

            ArrayList<Squad> allSquads = Squad.getAll();

            Squad selectedSquad = Squad.findById(selectedSquadId);
            String squadName = selectedSquad.getName();

            editHero.setName(name);
            editHero.setAge(age);
            editHero.setSpecialPower(specialPower);
            editHero.setWeakness(weakness);
            editHero.setSquadMembership(squadName);

            model.put("editHero", editHero);
            model.put("name", name);
            model.put("age", age);
            model.put("specialPower", specialPower);
            model.put("weakness", weakness);
            model.put("squadName", squadName);

            return new ModelAndView(model, "hero-success.hbs");
        }, new HandlebarsTemplateEngine());

        // routing Heroes form
        post("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String specialPower = request.queryParams("special-power");
            String weakness = request.queryParams("weakness");
            int selectedSquadId = Integer.parseInt(request.queryParams("selected-squad"));

            ArrayList<Squad> allSquads = Squad.getAll();

            Squad selectedSquad = Squad.findById(selectedSquadId);
            String squadName = selectedSquad.getName();

            Hero newHero = new Hero(name, age, specialPower, weakness, squadName);

            model.put("name", name);
            model.put("age", age);
            model.put("specialPower", specialPower);
            model.put("weakness", weakness);
            model.put("squads", allSquads);
            model.put("squadName", squadName);
            model.put("newHero", newHero);

            return new ModelAndView(model, "hero-success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}



