import java.util.ArrayList;
import java.util.List;

 public class Client {
   private String name;
   private String phone;
   private String availability;
   private int id;
   private String image;
   private int styListId;

    public Client(String name, String phone, String availability, String image, int styListId){
        this.name = name;
        this.phone = phone;
        this.availability = availability;
        this.image = image;
        this.styListId = styListId;
     }
}
