package gym_solution.demo.controller;

import gym_solution.demo.dto.NotificationDTO;
import gym_solution.demo.dto.response.NotificationResponseDTO;
import gym_solution.demo.model.Notification;
import gym_solution.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor

public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/")
    NotificationResponseDTO add(@RequestBody NotificationDTO notificationDTO) {
        return this.notificationService.addNotification(notificationDTO);
    }

    @PutMapping("/")
    NotificationResponseDTO update(@RequestBody NotificationResponseDTO notificationResponseDTO) {
        return this.notificationService.updateNotification(notificationResponseDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.notificationService.deleteNotificationById(id);
    }

    @GetMapping("/{id}")
    NotificationResponseDTO findById(@PathVariable Long id) {
        return this.notificationService.findNotificationById(id);
    }

    @GetMapping("/")
    List<NotificationResponseDTO> findAll() {
        return this.notificationService.findAllNotifications();
    }

}
