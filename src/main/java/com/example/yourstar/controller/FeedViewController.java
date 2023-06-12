package com.example.yourstar.controller;

import com.example.yourstar.data.dto.FeedViewDto;
import com.example.yourstar.data.dto.post.PostWriteFormDto;
import com.example.yourstar.data.dto.profile.PageDto;
import com.example.yourstar.service.FeedViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feed")
public class FeedViewController {

    private FeedViewService feedViewService;

    @Autowired
    public FeedViewController(FeedViewService feedViewService) {
        this.feedViewService = feedViewService;
    }

    @PostMapping(value = "/feedload")
    public ResponseEntity<List<FeedViewDto>> getFeedByUser(Authentication authentication, @RequestBody PageDto page) {
        log.info("넘겨받은 토큰{}",authentication.getName());
        log.info("넘겨받은 페이지{}",page.getPage());
        String userId = authentication.getName(); //getName이 안돼서 일단 되는 name으로 함
        List<FeedViewDto> feedPage = feedViewService.fetchFeedByUserIdWithPagination(userId, page.getPage(), 5);
        return ResponseEntity.ok(feedPage);
    }
}
