package com.example.yourstar.service;

import com.example.yourstar.data.dto.FeedViewDto;

import java.util.List;

public interface FeedViewService {
    List<FeedViewDto> fetchFeedByUserIdWithPagination(String userId, int page, int size);
}