package com.mjuAppSW.joA.domain.vote;

import com.mjuAppSW.joA.domain.voteCategory.VoteCategory;
import com.mjuAppSW.joA.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote {

    @Id @GeneratedValue
    @Column(name = "Vote_id")
    private Long id;

    @Column(name = "Give_id", nullable = false)
    private Long giveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Take_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Category_id", nullable = false)
    private VoteCategory voteCategory;

    @Column(nullable = false)
    private LocalDate date; // 변환 방법 알아봐야

    private String hint;

    @Builder
    public Vote(Long giveId, Member member, VoteCategory voteCategory, LocalDate date, String hint) {
        this.giveId = giveId;
        this.member = member;
        this.voteCategory = voteCategory;
        this.date = date;
        this.hint = hint;
    }
}
