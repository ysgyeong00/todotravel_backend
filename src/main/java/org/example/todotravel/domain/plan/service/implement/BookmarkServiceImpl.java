package org.example.todotravel.domain.plan.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.todotravel.domain.plan.entity.Bookmark;
import org.example.todotravel.domain.plan.entity.Plan;
import org.example.todotravel.domain.plan.repository.BookmarkRepository;
import org.example.todotravel.domain.plan.service.BookmarkService;
import org.example.todotravel.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional
    public Bookmark createBookmark(Plan plan, User user) {
        Bookmark bookmark = Bookmark.builder()
                .bookmarkUser(user)
                .plan(plan)
                .build();
        return bookmarkRepository.save(bookmark);
    }

    @Override
    @Transactional
    public void removeBookmark(Plan plan, User user) {
        bookmarkRepository.deleteByPlanAndBookmarkUser(plan, user);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countBookmark(Plan plan) {
        return bookmarkRepository.countByPlan(plan);
    }
}
