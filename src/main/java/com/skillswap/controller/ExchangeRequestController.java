package com.skillswap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillswap.entity.ExchangeRequest;
import com.skillswap.service.ExchangeRequestService;

@RestController
@RequestMapping("/api/exchange-requests")
@CrossOrigin(origins = "http://localhost:5175")
public class ExchangeRequestController {

    private final ExchangeRequestService exchangeRequestService;

    public ExchangeRequestController(
            ExchangeRequestService exchangeRequestService) {
        this.exchangeRequestService = exchangeRequestService;
    }

    // CREATE REQUEST
    // POST /api/exchange-requests
    @PostMapping
    public ResponseEntity<ExchangeRequest> createRequest(
            @RequestBody ExchangeRequest request) {

        return ResponseEntity.ok(
                exchangeRequestService.createRequest(request)
        );
    }

    // GET ALL REQUESTS
    // GET /api/exchange-requests
    @GetMapping
    public ResponseEntity<List<ExchangeRequest>> getAllRequests() {

        return ResponseEntity.ok(
                exchangeRequestService.getAllRequests()
        );
    }

    // GET REQUEST BY ID
    // GET /api/exchange-requests/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRequest> getRequestById(
            @PathVariable Long id) {

        ExchangeRequest request =
                exchangeRequestService.getRequestById(id);

        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(request);
    }

    // GET SENT REQUESTS
    // GET /api/exchange-requests/sent/{userId}
    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<ExchangeRequest>> getSentRequests(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                exchangeRequestService.getSentRequests(userId)
        );
    }

    // GET RECEIVED REQUESTS
    // GET /api/exchange-requests/received/{userId}
    @GetMapping("/received/{userId}")
    public ResponseEntity<List<ExchangeRequest>> getReceivedRequests(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                exchangeRequestService.getReceivedRequests(userId)
        );
    }

    // GET PENDING REQUESTS
    // GET /api/exchange-requests/pending/{userId}
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<ExchangeRequest>> getPendingRequests(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                exchangeRequestService.getPendingRequests(userId)
        );
    }

    // ACCEPT REQUEST
    // PUT /api/exchange-requests/{id}/accept
    @PutMapping("/{id}/accept")
    public ResponseEntity<ExchangeRequest> acceptRequest(
            @PathVariable Long id) {

        ExchangeRequest request =
                exchangeRequestService.acceptRequest(id);

        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(request);
    }

    // REJECT REQUEST
    // PUT /api/exchange-requests/{id}/reject
    @PutMapping("/{id}/reject")
    public ResponseEntity<ExchangeRequest> rejectRequest(
            @PathVariable Long id) {

        ExchangeRequest request =
                exchangeRequestService.rejectRequest(id);

        if (request == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(request);
    }

    // DELETE REQUEST
    // DELETE /api/exchange-requests/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long id) {

        boolean deleted =
                exchangeRequestService.deleteRequest(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}