package school.faang.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private static final String SUBSCRIBED_EXCEPTION_FORM = "follower with id %d %s subscribed to followee with id %d";
    private static final String USER_EXIST_FORM = "%s with id %d isn`t exist";
    private final SubscriptionRepository subscriptionRepository;

    public void followUser(long followerId, long followeeId) {
        validateFollowerAndFollowee(followerId, followeeId, "has already", true);
        subscriptionRepository.followUser(followerId, followeeId);
    }

    public void unfollowUser(long followerId, long followeeId) {
        validateFollowerAndFollowee(followerId, followeeId, "hasn`t", false);
        subscriptionRepository.unfollowUser(followerId, followeeId);
    }

    private void validateFollowerAndFollowee(long followerId, long followeeId, String subscribed, boolean isSubscribed) {
        if (subscriptionRepository.existsById(followerId)) {
            throw new DataValidationException(String.format(USER_EXIST_FORM, "follower", followerId));
        }

        if (subscriptionRepository.existsById(followeeId)) {
            throw new DataValidationException(String.format(USER_EXIST_FORM, "followee", followeeId));
        }

        if (subscriptionRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId) != isSubscribed) {
            throw new DataValidationException(
                    String.format(SUBSCRIBED_EXCEPTION_FORM, followerId, subscribed, followeeId));
        }
    }
}
