package com.naver.generator;

import java.util.Random;

public class CommentGenerator {

    static String[] sample0 = {"오","오잉 ","짧지만","","","와 ","아...", "","아니 ", "젠장", "진짜 ", "근데 ", "zzzzzzz", "ㅋㅋㅋㅋㅋㅋㅋㅋ", "??:", "앜ㅋㅋㅋㅋㅋㅋ", "이런 ", ""};
    static String[] sample1 = {"굳","왜 재밌지","뭔데ㅋㅋ","베댓 시켜주세요","진자","개재밌다","뭔일이지","진짜","늦었네요","반갑다","빨리 올라왔네","이해불가..","이미 본건데?","","굿", "좋아요", "개웃기네", "뭐야", "너무 웃겨", "왜 이럼", "주인공 왜 안나와요", "음.....", "이런", "", "실화?"};
    static String[] sample2 = {"😂😂","ㅋㅋㅋㅋㅋㅋㅋㅋ","ㅠㅠ","ㅜㅜ","ㅋㅋㅋㅋ큐ㅠㅠㅠ큐ㅠㅠ","ㅎ ㅎ",".....^^","","zzzzzㅋㅋㅋㅋㅋㅋㅋㅋ", "","~~~~~~",";;", "zzzzzzzㅋㅋ", "ㅋㅋㅋㅋㅋㅋㅋㅋ", "....", "❤", "🤣", "🤣🤣🤣🤣🤣", "ㅋㅋ", "ㅎㅎㅎ", "!!", "?", "ㄷㄷㄷㄷㄷㄷ"};
    public static String comment() {
        Random random = new Random();
        String comment = "";

        comment += sample0[random.nextInt(sample0.length)];
        comment += sample1[random.nextInt(sample0.length)];
        comment += sample2[random.nextInt(sample0.length)];

        if(comment.equals("")) comment = "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ";
        System.out.println(comment);

        return comment;
    }
}
