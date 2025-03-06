package school.faang.user_service.filter.user;

import org.springframework.stereotype.Component;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.entity.User;

import java.util.stream.Stream;

@Component
public interface UserFilter {

    boolean isAvailable(UserFilterDto filter);

    Stream<User> apply(Stream<User> users, UserFilterDto filter);
}
