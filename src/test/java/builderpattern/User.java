package builderpattern;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
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
