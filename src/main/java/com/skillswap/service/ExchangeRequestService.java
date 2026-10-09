package com.skillswap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.entity.ExchangeRequest;
import com.skillswap.entity.ExchangeStatus;
import com.skillswap.repository.ExchangeRequestRepository;

@Service
public class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;

    public ExchangeRequestService(
            ExchangeRequestRepository exchangeRequestRepository) {
        this.exchangeRequestRepository = exchangeRequestRepository;
    }

    // =========================
    // CREATE REQUEST
    // =========================
    public ExchangeRequest createRequest(
            ExchangeRequest request) {

        if (request.getStatus() == null) {
            request.setStatus(ExchangeStatus.PENDING);
        }

        return exchangeRequestRepository.save(request);
    }

    // =========================
    // GET REQUEST BY ID
    // =========================
    public ExchangeRequest getRequestById(Long id) {

        return exchangeRequestRepository
                .findById(id)
                .orElse(null);
    }

    // =========================
    // GET ALL REQUESTS
    // =========================
    public List<ExchangeRequest> getAllRequests() {

        return exchangeRequestRepository.findAll();
    }

    // =========================
    // REQUESTS SENT BY USER
    // =========================
    public List<ExchangeRequest> getSentRequests(Long userId) {

        return exchangeRequestRepository
                .findByRequesterId(userId);
    }

    // =========================
    // REQUESTS RECEIVED BY USER
    // =========================
    public List<ExchangeRequest> getReceivedRequests(Long userId) {

        return exchangeRequestRepository
                .findByReceiverId(userId);
    }

    // =========================
    // PENDING REQUESTS
    // =========================
    public List<ExchangeRequest> getPendingRequests(Long userId) {

        return exchangeRequestRepository
                .findByReceiverIdAndStatus(
                        userId,
                        ExchangeStatus.PENDING
                );
    }

    // =========================
    // ACCEPT REQUEST
    // =========================
    public ExchangeRequest acceptRequest(Long id) {

        ExchangeRequest request =
                exchangeRequestRepository
                        .findById(id)
                        .orElse(null);

        if (request == null) {
            return null;
        }

        request.setStatus(ExchangeStatus.ACCEPTED);

        return exchangeRequestRepository.save(request);
    }

    // =========================
    // REJECT REQUEST
    // =========================
    public ExchangeRequest rejectRequest(Long id) {

        ExchangeRequest request =
                exchangeRequestRepository
                        .findById(id)
                        .orElse(null);

        if (request == null) {
            return null;
        }

        request.setStatus(ExchangeStatus.REJECTED);

        return exchangeRequestRepository.save(request);
    }

    // =========================
    // DELETE REQUEST
    // =========================
    public boolean deleteRequest(Long id) {

        if (!exchangeRequestRepository.existsById(id)) {
            return false;
        }

        exchangeRequestRepository.deleteById(id);

        return true;
    }
}