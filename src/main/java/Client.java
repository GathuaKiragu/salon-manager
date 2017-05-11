import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

 public class Client {
   private String name;
   private String phone;
   private String availability;
   private int id;
   private String clientImage;
   private String style;
   private int styListId;

    public Client(String name, String phone, String availability, String clientImage, String style, int styListId){
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.clientImage = clientImage;
        this.style = style;
        this.styListId = styListId;
     }

     public String getName() {
       return name;
     }
// This Returns the client phone number
     public String getPhone() {
       return phone;
     }
// This returns the availability of the client
     public String getAvailability() {
       return availability;
     }
// This returns the clients image
     public String getClientImage() {
       return clientImage;
     }
// This returns the stylist id
     public int getStyListId() {
       return styListId;
     }
// This returns clients style
     public String getStyle() {
       return style;
     }
// Returns Clients Id
     public int getId() {
       return id;
     }
// Connects clients class to database
     public static List<Client> all() {
       String sql = "SELECT id, name, phone, availability, clientImage, style, stylistId FROM clients;";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    @Override
    public boolean equals(Object otherClient) {
      if (!(otherClient instanceof Client)) {
        return false;
      } else {
        Client newClient = (Client) otherClient;
        return this.getName().equals(newClient.getName()) &&
                this.getPhone().equals(newClient.getPhone()) &&
                this.getAvailability().equals(newClient.getAvailability()) &&
                this.getClientImage().equals(newClient.getClientImage()) &&
                this.getStyle().equals(newClient.getStyle()) &&
                this.getId() == newClient.getId() &&
                this.getStyListId() == newClient.getStyListId();
      }
    }
    // method to save client record onto the database
    public void save() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO clients (name, phone, availability, clientImage, style, styListId) VALUES (:name, :phone, :availability, :clientImage, :style, :styListId)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .addParameter("phone", this.phone)
         .addParameter("availability", this.availability)
         .addParameter("clientImage", this.clientImage)
         .addParameter("style", this.style)
         .addParameter("styListId", this.styListId)
         .executeUpdate()
         .getKey();
     }
  }
  // method to find client record
          public static Client find(int id) {
            try(Connection con = DB.sql2o.open()) {
              String sql = "SELECT * FROM clients where id=:id";
              Client client = con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Client.class);
              return client;
            }
          }
    // method to update clients record
     public void update(String name, String phone, String availability, String clientImage, String style) {
       try(Connection con = DB.sql2o.open()) {
       String sql = "UPDATE clients SET (name, phone, availability, clientImage, style) = (:name, :phone, :availability, :image, :style) WHERE id = :id";
       con.createQuery(sql)
       .addParameter("name", name)
       .addParameter("phone", phone)
       .addParameter("availability", availability)
       .addParameter("clientImage", clientImage)
       .addParameter("style", style)
       .addParameter("id", id)
       .executeUpdate();
     }
}
// method to delete clients from database
    public void delete() {
      try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      }
   }
}
