package gym_solution.demo.service.serviceImpl;

import gym_solution.demo.dto.NotificationDTO;
import gym_solution.demo.dto.response.NotificationResponseDTO;
import gym_solution.demo.exception.NotFoundException;
import gym_solution.demo.mapper.NotificationMapper;
import gym_solution.demo.model.Notification;

import gym_solution.demo.repository.NotificationRepository;
import gym_solution.demo.repository.UserRepository;
import gym_solution.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public NotificationResponseDTO addNotification(NotificationDTO notificationDTO) {
        return this.notificationMapper.toResponse(this.notificationRepository.save(this.notificationMapper.toNotification(notificationDTO)));
    }

    @Override
    @Transactional
    public NotificationResponseDTO updateNotification(NotificationResponseDTO notificationResponseDTO) {
        Notification notification = this.notificationRepository.findById(notificationResponseDTO.getNotificationId()).orElseThrow(() -> new NotFoundException("Notification with id " + notificationResponseDTO.getNotificationId() + " not found"));
        notification.setTitle(notificationResponseDTO.getTitle());
        notification.setMessage(notificationResponseDTO.getMessage());
        notification.setType(notificationResponseDTO.getType());
        notification.setRead(notificationResponseDTO.isRead());
        notification.setCreatedAt(notificationResponseDTO.getCreatedAt());
        return this.notificationMapper.toResponse(this.notificationRepository.save(notification));

    }

    @Override
    @Transactional
    public void deleteNotificationById(Long id) {
        if (!this.notificationRepository.existsById(id)) {
            throw new NotFoundException("Notification with id " + id + " not found");
        }
        this.notificationRepository.deleteById(id);
    }

    @Override
    public NotificationResponseDTO findNotificationById(Long id) {
        return this.notificationMapper.toResponse(this.notificationRepository.findById(id).orElseThrow(() -> new NotFoundException("Notification with id " + id + " not found")));
    }

    @Override
    public List<NotificationResponseDTO> findAllNotifications() {
        return this.notificationMapper.toResponses(this.notificationRepository.findAll());
    }


}
