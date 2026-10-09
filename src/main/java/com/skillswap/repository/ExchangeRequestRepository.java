package com.skillswap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillswap.entity.ExchangeRequest;
import com.skillswap.entity.ExchangeStatus;

public interface ExchangeRequestRepository
        extends JpaRepository<ExchangeRequest, Long> {

    List<ExchangeRequest> findByRequesterId(Long requesterId);

    List<ExchangeRequest> findByReceiverId(Long receiverId);

    List<ExchangeRequest> findByStatus(ExchangeStatus status);

    List<ExchangeRequest> findByReceiverIdAndStatus(
            Long receiverId,
            ExchangeStatus status
    );

    List<ExchangeRequest> findByRequesterIdAndStatus(
            Long requesterId,
            ExchangeStatus status
    );
}