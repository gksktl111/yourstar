package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.FeedViewDao;
import com.example.yourstar.data.dto.FeedViewDto;
import com.example.yourstar.data.entity.FeedViewEntity;
import com.example.yourstar.data.repository.FeedViewRepository;
import com.example.yourstar.service.FeedViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedViewServiceImpl implements FeedViewService {

    private FeedViewRepository feedViewRepository;

    private FeedViewDao feedViewDao;

    private FeedViewEntity feedViewEntity;

    @Autowired
    public FeedViewServiceImpl(FeedViewRepository feedViewRepository, FeedViewDao feedViewDao) {
        this.feedViewRepository = feedViewRepository;
        this.feedViewDao = feedViewDao;
    }

    @Override
    public List<FeedViewDto> fetchFeedByUserIdWithPagination(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("postEntity.createdAt").descending());
        List<FeedViewEntity> feedPage = feedViewDao.findAllByToUserId(userId, pageable);
        return feedPage.stream().map(feed -> new FeedViewDto(feed.getUser().getUserId(), feed.getPostId())).collect(Collectors.toList());
    }
}