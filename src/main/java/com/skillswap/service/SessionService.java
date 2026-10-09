package com.skillswap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.Session;
import com.skillswap.entity.SessionStatus;
import com.skillswap.repository.SessionRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // Create a new session
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    // Get all sessions
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    // Get session by ID
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    // Get sessions for an exchange request
    public List<Session> getSessionsByExchangeRequest(Long exchangeRequestId) {
        return sessionRepository.findByExchangeRequestId(exchangeRequestId);
    }

    // Get sessions created by a requester
    public List<Session> getSessionsByRequester(Long userId) {
        return sessionRepository.findByExchangeRequestRequesterId(userId);
    }

    // Get sessions received by a receiver
    public List<Session> getSessionsByReceiver(Long userId) {
        return sessionRepository.findByExchangeRequestReceiverId(userId);
    }

    // Get sessions by status
    public List<Session> getSessionsByStatus(SessionStatus status) {
        return sessionRepository.findByStatus(status);
    }

    // Update session status
    public Session updateStatus(Long id, SessionStatus status) {

        Session session = sessionRepository.findById(id).orElse(null);

        if (session == null) {
            return null;
        }

        session.setStatus(status);

        return sessionRepository.save(session);
    }

    // Delete session
    public boolean deleteSession(Long id) {

        if (!sessionRepository.existsById(id)) {
            return false;
        }

        sessionRepository.deleteById(id);

        return true;
    }
}