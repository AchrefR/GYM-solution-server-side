package gym_solution.demo.dto.response;

import gym_solution.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class NotificationResponseDTO {

    private Long notificationId;

    private String title;

    private String message;

    private String type;

    private boolean isRead;

    private String createdAt;

    private List<Long> usersId;

}
