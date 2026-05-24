package gym_solution.demo.service;

import gym_solution.demo.dto.AdministratorDTO;
import gym_solution.demo.dto.NotificationDTO;
import gym_solution.demo.dto.response.AdministratorResponseDTO;
import gym_solution.demo.dto.response.NotificationResponseDTO;
import gym_solution.demo.model.Notification;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO addNotification(NotificationDTO notificationDTO);

    NotificationResponseDTO updateNotification(NotificationResponseDTO notificationResponseDTO);

    void deleteNotificationById(Long id);

    NotificationResponseDTO findNotificationById(Long id);

    List<NotificationResponseDTO> findAllNotifications();
}
