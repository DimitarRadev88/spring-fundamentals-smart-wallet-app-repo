package app.user.service.impl;

import app.exception.PasswordDoesNotMatchException;
import app.exception.UserDoesNotExistException;
import app.subscription.dao.SubscriptionRepository;
import app.subscription.model.Subscription;
import app.subscription.service.SubscriptionService;
import app.user.dao.UserRepository;
import app.user.model.User;
import app.user.model.dto.UserLoginRequest;
import app.user.model.dto.UserLoginResponse;
import app.user.model.dto.UserRegisterResponse;
import app.user.model.enumeration.UserRole;
import app.user.service.UserService;
import app.wallet.Wallet;
import app.wallet.service.WalletService;
import app.user.model.dto.UserRegisterRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WalletService walletService;
    private final SubscriptionService subscriptionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletService walletService, SubscriptionRepository subscriptionRepository, SubscriptionService subscriptionService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.walletService = walletService;
        this.subscriptionService = subscriptionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .username(userRegisterRequest.getUsername())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .country(userRegisterRequest.getCountry())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .role(UserRole.USER)
                .subscriptions(new ArrayList<>())
                .wallets(new ArrayList<>())
                .userRoleChangedOn(LocalDateTime.now())
                .isActive(true)
                .build();

        Subscription defaultSubscription = subscriptionService.createDefaultSubscription();
        defaultSubscription.setOwner(user);

        Wallet wallet = walletService.createNewWallet();
        wallet.setOwner(user);

        user.getSubscriptions().add(defaultSubscription);
        user.getWallets().add(wallet);

        User saved = userRepository.saveAndFlush(user);

        return UserRegisterResponse
                .builder()
                .username(saved.getUsername())
                .id(saved.getId())
                .build();
    }

    @Override
    public UserLoginResponse login(UserLoginRequest user) {
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());

        if (byUsername.isEmpty()) {
            throw new UserDoesNotExistException(user.getUsername() + " does not exist");
        }

        User userEntity = byUsername.get();

        if (!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            throw new PasswordDoesNotMatchException("Invalid password");
        }

        UserLoginResponse userLoginResponse = new UserLoginResponse();

//        userLoginResponse.setToken();

        return userLoginResponse;
    }
}
