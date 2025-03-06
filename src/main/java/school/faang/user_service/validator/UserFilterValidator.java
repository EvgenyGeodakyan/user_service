package school.faang.user_service.validator;


import org.springframework.stereotype.Component;
import school.faang.user_service.dto.UserFilterDto;
import school.faang.user_service.exception.DataValidationException;

@Component
public class UserFilterValidator {
    public void validateUserFilterDto(UserFilterDto filter) {
        if (filter == null) {
            throw new DataValidationException("filter cannot be null");
        }
    }
}
