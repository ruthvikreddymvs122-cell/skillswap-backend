package com.skillswap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillswap.entity.Session;
import com.skillswap.entity.SessionStatus;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByExchangeRequestId(Long exchangeRequestId);

    List<Session> findByStatus(SessionStatus status);

    List<Session> findByExchangeRequestRequesterId(Long userId);

    List<Session> findByExchangeRequestReceiverId(Long userId);
}