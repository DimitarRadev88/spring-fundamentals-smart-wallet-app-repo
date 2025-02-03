package app.user.model;

import app.subscription.model.Subscription;
import app.user.model.enumeration.Country;
import app.user.model.enumeration.UserRole;
import app.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String profilePicture;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Column(nullable = false)
    private Boolean isActive;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime updatedOn;
    @Basic
    private LocalDateTime userRoleChangedOn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Subscription> subscriptions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Wallet> wallets;

}
