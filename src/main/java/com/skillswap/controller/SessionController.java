package com.skillswap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillswap.entity.Session;
import com.skillswap.entity.SessionStatus;
import com.skillswap.service.SessionService;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    // Create session
    @PostMapping
    public ResponseEntity<Session> createSession(
            @RequestBody Session session) {

        return ResponseEntity.ok(
                sessionService.createSession(session)
        );
    }

    // Get all sessions
    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {

        return ResponseEntity.ok(
                sessionService.getAllSessions()
        );
    }

    // Get session by ID
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(
            @PathVariable Long id) {

        Session session =
                sessionService.getSessionById(id);

        if (session == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(session);
    }

    // Get sessions for exchange request
    @GetMapping("/exchange/{exchangeRequestId}")
    public ResponseEntity<List<Session>>
    getSessionsByExchangeRequest(
            @PathVariable Long exchangeRequestId) {

        return ResponseEntity.ok(
                sessionService
                        .getSessionsByExchangeRequest(
                                exchangeRequestId
                        )
        );
    }

    // Get sessions created by user
    @GetMapping("/requester/{userId}")
    public ResponseEntity<List<Session>>
    getSessionsByRequester(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                sessionService
                        .getSessionsByRequester(userId)
        );
    }

    // Get sessions received by user
    @GetMapping("/receiver/{userId}")
    public ResponseEntity<List<Session>>
    getSessionsByReceiver(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                sessionService
                        .getSessionsByReceiver(userId)
        );
    }

    // Get sessions by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Session>>
    getSessionsByStatus(
            @PathVariable SessionStatus status) {

        return ResponseEntity.ok(
                sessionService
                        .getSessionsByStatus(status)
        );
    }

    // Update session status
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Session>
    updateStatus(
            @PathVariable Long id,
            @PathVariable SessionStatus status) {

        Session updated =
                sessionService.updateStatus(
                        id,
                        status
                );

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // Delete session
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(
            @PathVariable Long id) {

        boolean deleted =
                sessionService.deleteSession(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}