import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private String tel;
  private String availability;
  private String specialty;
  private String image;
  private int id;

  public Stylist(String name, String tel, String availability, String specialty, String image) {
    this.name = name;
    this.tel = tel;
    this.availability = availability;
    this.specialty = specialty;
    this.image = image;
  }

// returns the stylists name
  public String getName() {
    return name;
  }
// returns the stylists telephone number
  public String getTel() {
    return tel;
  }
// return how available the stylist is
  public String getAvailability() {
    return availability;
  }
// returns the stylists specialty
  public String getSpecialty() {
    return specialty;
  }
// returns the stylists image
  public String getImage() {
    return image;
  }
// returns the stylist id
  public int getId() {
    return id;
  }
  // method to connect stylist to the database
    public static List<Stylist> all() {
      String sql = "SELECT id, name, tel, availability, specialty, image FROM stylists;";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Stylist.class);
      }
    }

    @Override
    public boolean equals(Object otherStylists) {
      if (!(otherStylists instanceof Stylist)) {
        return false;
      } else {
        Stylist newStylist = (Stylist) otherStylists ;
        return this.getName().equals(newStylist.getName()) &&
               this.getTel().equals(newStylist.getTel()) &&
               this.getAvailability().equals(newStylist.getAvailability()) &&
               this.getSpecialty().equals(newStylist.getSpecialty()) &&
               this.getImage().equals(newStylist.getImage()) &&
               this.getId() == newStylist.getId();
      }
    }

   // method to save stylists information to the database
     public void save() {
       try(Connection con = DB.sql2o.open()) {
         String sql = "INSERT INTO stylists (name, tel, availability, specialty, image) VALUES (:name, :tel, :availability, :specialty, :image)";
         this.id = (int) con.createQuery(sql, true)
           .addParameter("name", this.name)
           .addParameter("tel", this.tel)
           .addParameter("availability", this.availability)
           .addParameter("specialty", this.specialty)
           .addParameter("image", this.image)
           .executeUpdate()
           .getKey();
       }
     }
   // method to find information in the database
       public static Stylist find(int id) {
         try(Connection con = DB.sql2o.open()) {
           String sql = "SELECT * FROM stylists where id=:id";
           Stylist stylist = con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetchFirst(Stylist.class);
           return stylist;
         }
       }
}
