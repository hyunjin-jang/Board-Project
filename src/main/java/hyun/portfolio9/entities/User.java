package hyun.portfolio9.entities;

import hyun.portfolio9.entities.references.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private Role userRole;
}
