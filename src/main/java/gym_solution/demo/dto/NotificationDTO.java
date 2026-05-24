package gym_solution.demo.dto;

import gym_solution.demo.model.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class NotificationDTO {

    private String title;

    private String message;

    private String type;

    private boolean isRead;

    private String createdAt;

    private List<User> users;

}
