package school.faang.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private static final String SUBSCRIBED_EXCEPTION_FORM = "follower with id %d has already subscribed to followee with id %d";
    private final SubscriptionRepository subscriptionRepository;

    public void followUser(long followerId, long followeeId) {
        if (subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId)) {
            throw new DataValidationException(String.format(SUBSCRIBED_EXCEPTION_FORM, followerId, followeeId));
        }
       subscriptionRepository.followUser(followerId, followeeId);
    }
}
