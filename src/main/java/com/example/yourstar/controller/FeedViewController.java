package com.example.yourstar.controller;

import com.example.yourstar.data.dto.FeedViewDto;
import com.example.yourstar.service.FeedViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedViewController {

    private FeedViewService feedViewService;

    @Autowired
    public FeedViewController(FeedViewService feedViewService) {
        this.feedViewService = feedViewService;
    }

    @GetMapping(value = "/feedload")
    public ResponseEntity<List<FeedViewDto>> getFeedByUser(Authentication authentication, @RequestParam int page, @RequestParam int size) {
        String userId = authentication.getName(); //getName이 안돼서 일단 되는 name으로 함
        List<FeedViewDto> feedPage = feedViewService.fetchFeedByUserIdWithPagination(userId, page, size);
        return ResponseEntity.ok(feedPage);
    }
}
