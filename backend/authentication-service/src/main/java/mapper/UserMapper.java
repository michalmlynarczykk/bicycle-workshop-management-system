package mapper;

import com.michalmlynarczyk.authenticationservice.model.WorkshopPosition;
import com.michalmlynarczyk.authenticationservice.model.dto.response.UserDetailsResponse;
import com.michalmlynarczyk.authenticationservice.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static UserDetailsResponse toDto(final User user) {
        if (user == null) {
            return null;
        }
        final List<WorkshopPosition> positions = user.getRoles()
                .stream()
                .map(role -> WorkshopPosition.getByRoleName(role.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return new UserDetailsResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getWorkshopAssignedAt(),
                positions
        );
    }
}
