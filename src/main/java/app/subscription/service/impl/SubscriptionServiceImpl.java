package app.subscription.service.impl;

import app.subscription.dao.SubscriptionRepository;
import app.subscription.model.Subscription;
import app.subscription.model.SubscriptionPeriod;
import app.subscription.model.SubscriptionStatus;
import app.subscription.model.SubscriptionType;
import app.subscription.service.SubscriptionService;
import app.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription createDefaultSubscription() {
        return Subscription.builder()
                .status(SubscriptionStatus.ACTIVE)
                .type(SubscriptionType.DEFAULT)
                .period(SubscriptionPeriod.MONTHLY)
                .price(new BigDecimal("0"))
                .startDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().minusMonths(1))
                .renewalAllowed(true)
                .build();
    }

}
