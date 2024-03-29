package com.naver.init;

import com.naver.entity.HashTag;
import com.naver.entity.Member;
import com.naver.generate.MemberGenerator;
import com.naver.repository.HashTagRepository;
import com.naver.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Intializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final HashTagRepository hashTagRepository;

    public Intializer(MemberRepository memberRepository, HashTagRepository hashTagRepository) {
        this.memberRepository = memberRepository;
        this.hashTagRepository = hashTagRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    private void saveMember() {
        List<Member> members = MemberGenerator.generateMember(10000);
        memberRepository.saveAll(members);
    }

    private void saveHashTag() {
        List<HashTag> hashTagList = new ArrayList<>();

        hashTagList.add(
                new HashTag("#로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#액션", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#일상", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#스릴러", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#개그", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#무협/사극", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#감성", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#스포츠", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#연도별웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#브랜드웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(new HashTag("#드라마&영화 원작웹툰", LocalDateTime.of(2010, 1, 1, 0, 0, 0),
                LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#먼치킨", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#학원로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#로판", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#게임판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#해외작품", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#법정드라마", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#범죄", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#인플루언서", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#고자극로맨스", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#아이돌", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#이세계", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#동양풍판타지", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#빙의", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));
        hashTagList.add(
                new HashTag("#소년왕도물", LocalDateTime.of(2010, 1, 1, 0, 0, 0), LocalDateTime.of(2010, 1, 1, 0, 0, 0)));

        hashTagRepository.saveAll(hashTagList);
    }
}
