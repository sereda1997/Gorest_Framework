import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPojo {
    private int id;
    private String name;
    private String email;
    private String gender;

    public UserPojo(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public UserPojo(int id, String name, String email, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
