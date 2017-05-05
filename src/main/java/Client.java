import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

 public class Client {
   private String name;
   private String phone;
   private String availability;
   private int id;
   private String image;
   private String style;
   private int styListId;

    public Client(String name, String phone, String availability, String image, String style, int styListId){
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.image = image;
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
     public String getImage() {
       return image;
     }
// This returns the stylist id
     public int getStyListId() {
       return styListId;
     }

     public String getStyle() {
       return style;
     }

     public int getId() {
       return id;
     }

     public static List<Client> all() {
       String sql = "SELECT id, name, phone, availability, image, style, stylistId FROM clients;";
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
                this.getImage().equals(newClient.getImage()) &&
                this.getStyle().equals(newClient.getStyle()) &&
                this.getStyListId() == newClient.getStyListId()
                && this.getId() == newClient.getId();
      }
    }

    public void save() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO clients (name, phone, availability, image, style, styListId) VALUES (:name, :phone, :availability, :image, :style, :styListId)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .addParameter("phone", this.phone)
         .addParameter("availability", this.availability)
         .addParameter("image", this.image)
         .addParameter("style", this.style)
         .addParameter("styListId", this.styListId)
         .executeUpdate()
         .getKey();
     }
  }

          // public static Client find(int id) {
          //   try(Connection con = DB.sql2o.open()) {
          //     String sql = "SELECT * FROM clients where id=:id";
          //     Client client = con.createQuery(sql)
          //       .addParameter("id", id)
          //       .executeAndFetchFirst(Client.class);
          //     return task;
          //   }
          // }

    //  public void update(String name, String phone, String availability, String image, String style, int styListId) {
    //    try(Connection con = DB.sql2o.open()) {
    //    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    //    con.createQuery(sql)
    //    .addParameter("name", name)
    //    .addParameter("phone", phone)
    //    .addParameter("availability", availability)
    //    .addParameter("image", image)
    //    .addParameter("style", style)
    //    .addParameter("styListId", styListId)
    //    .addParameter("id", id)
    //    .executeUpdate();
    //  }
   }
