package school.faang.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import school.faang.user_service.dto.UserDto;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.User;
import school.faang.user_service.exception.DataValidationException;
import school.faang.user_service.service.SubscriptionService;
import school.faang.user_service.validator.UserFilterValidator;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserFilterValidator userFilterValidator;

    public void followUser(long followerId, long followeeId) {
        validateDifferenceIds(followerId, followeeId);
        subscriptionService.followUser(followerId, followeeId);
    }

    public void unfollowUser(long followerId, long followeeId) {
        validateDifferenceIds(followerId, followeeId);
        subscriptionService.unfollowUser(followerId, followeeId);
    }

    public List<UserDto> getFollowers(long followeeId, UserFilterDto filter) {
        userFilterValidator.validateUserFilterDto(filter);
        return subscriptionService.getFollowers(followeeId, filter);
    }

    public List<UserDto> getFollowers(long followeeId) {
        return subscriptionService.getFollowers(followeeId);
    }

    public int getFollowersCount(long followeeId) {
        return subscriptionService.getFollowersCount(followeeId);
    }


    public List<UserDto> getFollowee(long followerId) {
        return subscriptionService.getFollowee(followerId);
    }

    private void validateDifferenceIds(long followerId, long followeeId) {
        if (followerId == followeeId) {
            throw new DataValidationException("followerId and followeeId must be differences");
        }
    }
}
