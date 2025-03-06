package school.faang.user_service.filter.user;

import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.User;

import java.util.stream.Stream;

public class UserPhoneFilter implements UserFilter {

    @Override
    public boolean isAvailable(UserFilterDto filter) {

        return filter.getPhonePattern() != null && !filter.getPhonePattern().isBlank();
    }

    @Override
    public Stream<User> apply(Stream<User> users, UserFilterDto filter) {
        return users.filter(user -> filter.getPhonePattern().equals(user.getPhone()));
    }
}
