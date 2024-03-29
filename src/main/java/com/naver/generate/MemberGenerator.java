package com.naver.generate;

import com.naver.entity.Member;
import com.naver.generator.RandomGenerator;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.awt.geom.GeneralPath;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.cglib.core.Local;

public final class MemberGenerator {
    private static List<Integer> ageDistributionList = new ArrayList<>();

    static {
        int[] ageDistribution = {3, 9, 5, 2, 1};
        int age = 10;

        for(int ad : ageDistribution) {
            for (int i = 0; i < ad; i++) {
                ageDistributionList.add(age);
            }
            age += 10;
        }
    }

    public static List<Member> generateMember(int count) {
        List<Member> memberList = new ArrayList<>();
        LocalDateTime startDate = LocalDateTime.of(2010, 1, 1, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2015, 12, 31, 23, 59, 59);

        for (int i = 1; i <= count; ++i) {
            String loginId = RandomGenerator.generateString(8, 15);
            String password = RandomGenerator.generateString(8, 15);
            String nickname = "user" + i;
            Collections.shuffle(ageDistributionList);
            int age = ageDistributionList.get(0);
            LocalDateTime time = RandomGenerator.generateLocalDateTime(startDate, endDate);

            memberList.add(new Member(loginId, password, nickname, age, time, time));
        }

        memberList.sort(Comparator.comparing(Member::getCreatedAt));

        return memberList;
    }
}
