package app.subscription.service;

import app.subscription.model.Subscription;
import app.user.model.User;

public interface SubscriptionService {
    Subscription createDefaultSubscription();
}
