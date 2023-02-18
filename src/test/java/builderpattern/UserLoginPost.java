package builderpattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginPost {
    //login credentials
    private String email;
    private String password;
    //json response
    private String message;
    private String token;

}
