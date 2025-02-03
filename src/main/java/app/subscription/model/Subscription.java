package app.subscription.model;

import app.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionPeriod period;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType type;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean renewalAllowed;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime expirationDate;

}
