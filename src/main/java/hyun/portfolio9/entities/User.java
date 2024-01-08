package hyun.portfolio9.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hyun.portfolio9.entities.references.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
    private String userNickName;
    private String userPassword;
    private String userBirth;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    @Enumerated(EnumType.STRING)
    private Role userRole; // GUEST, ADMIN
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Posts> postsList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();
}
