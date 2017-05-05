import java.util.ArrayList;
import java.util.List;

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
// This returns the category id
     public int getStyListId() {
       return styListId;
     }

     public String getStyle() {
       return style;
     }
}
