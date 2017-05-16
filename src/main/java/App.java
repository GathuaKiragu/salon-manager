import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

// Setting port for deployment to Heroku
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);

// route to homepage
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route to link stylist with client
    get("/stylists/:stylist_id/clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// assigns client to specific stylist
    post("/stylists/:stylist_id/clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      String availability = request.queryParams("availability");
      String clientImage = request.queryParams("clientImage");
      String style = request.queryParams("style");
      Stylist stylist = Stylist.find(client.getStyListId());
      client.update(name, phone, availability, clientImage, style);
      String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//route to display client has been successfully saved to the database and has been assigned to stylist
    post("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      String availability = request.queryParams("availability");
      String style = request.queryParams("style");
      String clientImage = request.queryParams("clientImage");
      Client newClient = new Client(name, phone, availability, style, clientImage, stylist.getId());
      newClient.save();
      model.put("stylist", stylist);
      model.put("template", "templates/stylist-client-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route to get display individual clients
  get("/clients/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    model.put("client", client);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

//route to show that stylist information has been saved to the database successfully.
    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String tel = request.queryParams("tel");
      String availability = request.queryParams("availability");
      String specialty = request.queryParams("specialty");
      String image = request.queryParams("image");
      Stylist newStylist = new Stylist(name, tel, availability, specialty,image);
      newStylist.save();
      model.put("template", "templates/stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route to display all clients
  get("/clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("clients", Client.all());
    model.put("template", "templates/clients.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// Route to create new client and assigning the client to the stylist
  get("/stylists/:id/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("template", "templates/stylist-client-form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

// route to delete stylist
    post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params("id")));
      Stylist stylist = Stylist.find(client.getStyListId());
      client.delete();
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/new", (req, res) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("template", "templates/stylist-registration-form.vtl");
      return new VelocityTemplateEngine()
          .render(new ModelAndView(model, layout));
  });
  get("/client/new", (req, res) -> {
    Map<String, Object> model = new HashMap<>();
    model.put("template", "templates/client-registration-form.vtl");
    return new VelocityTemplateEngine()
        .render(new ModelAndView(model, layout));
});
//   post("/client-success", (request, response) -> {
//     Map<String, Object> model = new HashMap<String, Object>();
//     Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
//     String name = request.queryParams("name");
//     String phone = request.queryParams("phone");
//     String availability = request.queryParams("availability");
//     String style = request.queryParams("style");
//     String clientImage = request.queryParams("clientImage");
//     Client newClient = new Client(name, phone, availability, style, clientImage, stylist.getId());
//     newClient.save();
//     model.put("template", "templates/client-success.vtl");
//     return new ModelAndView(model, layout);
// }, new VelocityTemplateEngine());
  post("/clients", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    String phone = request.queryParams("phone");
    String availability = request.queryParams("availability");
    String style = request.queryParams("style");
    String clientImage = request.queryParams("clientImage");
    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
    Client newClient = new Client(name, phone, availability, style, clientImage, stylist.getId());
    newClient.save();
    model.put("stylist", stylist);
    model.put("template", "templates/client-success.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    post("/stylist-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String tel = request.queryParams("tel");
      String availability = request.queryParams("availability");
      String specialty = request.queryParams("specialty");
      String image = request.queryParams("image");
      Stylist newStylist = new Stylist(name, tel, availability, specialty,image);
      newStylist.save();
      model.put("template", "templates/stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
