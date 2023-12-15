package hyun.portfolio9.entities;

import hyun.portfolio9.entities.references.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private List<Role> userRole = Arrays.asList(Role.GUEST);
}
