package builderpattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userlist {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone_number;
    private String nid;
    private String role;
    private String createdAt;
    private String updatedAt;
    private String balance;

}
