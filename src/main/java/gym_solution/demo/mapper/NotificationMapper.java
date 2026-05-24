package gym_solution.demo.mapper;

import gym_solution.demo.dto.NotificationDTO;
import gym_solution.demo.dto.response.NotificationResponseDTO;
import gym_solution.demo.model.Notification;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
@Builder
public class NotificationMapper {

    public Notification toNotification(NotificationDTO notificationDTO) {
        return Notification.builder().
                title(notificationDTO.getTitle()).
                message(notificationDTO.getMessage()).
                type(notificationDTO.getType()).
                isRead(notificationDTO.isRead()).
                createdAt(notificationDTO.getCreatedAt()).
                users(notificationDTO.getUsers())
                .build();
    }

    public NotificationResponseDTO toResponse(Notification notification) {
        List<Long> usersId = new ArrayList<>();
        notification.getUsers().forEach(user -> usersId.add(user.getUserId()));
        return NotificationResponseDTO.builder().
                notificationId(notification.getNotificationId()).
                title(notification.getTitle()).
                message(notification.getMessage()).
                type(notification.getType()).
                isRead(notification.isRead()).
                createdAt(notification.getCreatedAt()).
                usersId(usersId)
                .build();
    }

    public List<NotificationResponseDTO> toResponses(List<Notification> notifications) {
        List<NotificationResponseDTO> notificationResponseDTOS = new ArrayList<>();
        notifications.forEach((notification -> notificationResponseDTOS.add(toResponse(notification))));
        return notificationResponseDTOS;
    }


}
