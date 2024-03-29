package com.naver.generator;

import com.naver.entity.*;
import com.naver.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;


@Service
public class CommentGenerator {

    private final CommentRepository commentRepository;
    private final MemberGenerator memberGenerator;
    private  final RecommentRepository recommentRepository;
    private  final CommentEmotionRepository commentEmotionRepository;
    private  final RecommentEmotionRepository recommentEmotionRepository;

    @Autowired
    public CommentGenerator(CommentRepository commentRepository, MemberGenerator memberGenerator, RecommentRepository recommentRepository, CommentEmotionRepository commentEmotionRepository, RecommentEmotionRepository recommentEmotionRepository) {
        this.commentRepository = commentRepository;
        this.memberGenerator = memberGenerator;
        this.recommentRepository = recommentRepository;
        this.commentEmotionRepository = commentEmotionRepository;
        this.recommentEmotionRepository = recommentEmotionRepository;
    }

    static String[] sample0 = {"오","오잉 ","짧지만","","","와 ","아...", "","아니 ", "젠장", "진짜 ", "근데 ", "zzzzzzz", "ㅋㅋㅋㅋㅋㅋㅋㅋ", "??:", "앜ㅋㅋㅋㅋㅋㅋ", "이런 ", ""};
    static String[] sample1 = {"굳","왜 재밌지","뭔데ㅋㅋ","베댓 시켜주세요","진자","개재밌다","뭔일이지","진짜","늦었네요","반갑다","빨리 올라왔네","이해불가..","이미 본건데?","","굿", "좋아요", "개웃기네", "뭐야", "너무 웃겨", "왜 이럼", "주인공 왜 안나와요", "음.....", "이런", "", "실화?"};
    static String[] sample2 = {"😂😂","ㅋㅋㅋㅋㅋㅋㅋㅋ","ㅠㅠ","ㅜㅜ","ㅋㅋㅋㅋ큐ㅠㅠㅠ큐ㅠㅠ","ㅎ ㅎ",".....^^","","zzzzzㅋㅋㅋㅋㅋㅋㅋㅋ", "","~~~~~~",";;", "zzzzzzzㅋㅋ", "ㅋㅋㅋㅋㅋㅋㅋㅋ", "....", "❤", "🤣", "🤣🤣🤣🤣🤣", "ㅋㅋ", "ㅎㅎㅎ", "!!", "?", "ㄷㄷㄷㄷㄷㄷ"};
    public String context() {
        Random random = new Random();
        String comment = "";

        comment += sample0[random.nextInt(sample0.length)];
        comment += sample1[random.nextInt(sample0.length)];
        comment += sample2[random.nextInt(sample0.length)];

        if(comment.equals("")) comment = "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ";
        System.out.println(comment);

        return comment;
    }



    // 에피소드에 대한 댓글 생성
    public void saveComment(Episode episode) {
        Random random = new Random();
        // 댓글 갯수
        int limit = 1;
        int n = random.nextInt(limit);

        // 해당 에피소드를 본 유저 중에서 랜덤으로 댓글 작성자 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(n, episode.getEpisodeId());

        for(int i = 0; i < n; i++){
            String context = context();
            // 읽은 날짜보다 늦게 createdDate 만들기
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(episode.getCreatedAt(), episode.getCreatedAt().plus(100, ChronoUnit.DAYS));

            Comment newComment = commentRepository.save(new Comment(context, members.get(i), episode, createdDate, createdDate));

            // 해당 코멘트에 대한 감정 생성
            saveCommentEmotion(newComment);
            // 해당 코멘트에 대한 대댓글 생성
            saveRecomment(newComment);
        }
    }

    // 특정 코멘트에 대한 recomment 생성하기
    public void saveRecomment(Comment comment) {
        Random random = new Random();
        int limit = 1;
        int n = random.nextInt(limit)+1;

        // 해당 에피소드를 본 유저 중에서 랜덤으로 댓글 작성자 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(n, comment.getEpisode().getEpisodeId());

        for(int i = 0; i < n; i++){
            String recomment = context();
            Member member = memberGenerator.randomMemberExcept(comment.getCommentId());

            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(comment.getCreatedAt(), comment.getCreatedAt().plus(100, ChronoUnit.DAYS));

            Recomment newRecomment = recommentRepository.save(new Recomment(recomment, comment, member, createdDate, createdDate));
            saveRecommentEmotion(newRecomment);
        }
    }

    // 코멘트에 대한 감정 넣기
    public void saveCommentEmotion(Comment comment) {
        Random random = new Random();
        int limit = 1;
        int memberCnt = random.nextInt(limit);
        if(memberCnt == 0) return;

        // 해당 에피소드를 본 유저 중에서 랜덤으로 댓글 작성자 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(n, comment.getEpisode().getEpisodeId());

        int likeCnt = random.nextInt(memberCnt);
        for(int i = 0; i < likeCnt; i++){
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(comment.getCreatedAt(), comment.getCreatedAt().plus(100, ChronoUnit.DAYS));
            commentEmotionRepository.save(new CommentEmotion(true, comment, members.get(i), createdDate, createdDate));
        }
        for(int i = likeCnt; i < memberCnt; i++){
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(comment.getCreatedAt(), comment.getCreatedAt().plus(100, ChronoUnit.DAYS));
            commentEmotionRepository.save(new CommentEmotion(false, comment, members.get(i), createdDate, createdDate));
        }
    }

    // 대댓글 감정 넣기
    public void saveRecommentEmotion(Recomment recomment) {
        Random random = new Random();
        // commentEmotion 개수 가져오기
        int commentLike = commentEmotionRepository.countByCommentId(recomment.getCommentId());
        int memberCnt = random.nextInt(commentLike);
        if(memberCnt == 0) return; // 그냥 저장안하고 나오기

        // 해당 에피소드를 본 유저 중에서 랜덤으로 댓글 작성자 찾기
        List<Member> members = memberGenerator.randomEpisodeMembers(memberCnt, recomment.getComment().getEpisode().getEpisodeId());

        // 좋아요
        int likeCnt = random.nextInt(memberCnt);
        for(int i = 0; i < likeCnt; i++){
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(recomment.getCreatedAt(), recomment.getCreatedAt().plus(100, ChronoUnit.DAYS));
            recommentEmotionRepository.save(new RecommentEmotion(true, recomment, members.get(i), createdDate, createdDate));
        }
        // 싫어요
        for(int i = likeCnt; i < memberCnt; i++){
            LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(recomment.getCreatedAt(), recomment.getCreatedAt().plus(100, ChronoUnit.DAYS));
            recommentEmotionRepository.save(new RecommentEmotion(false, recomment, members.get(i), createdDate, createdDate));
        }
    }

    // 전체 코멘트 리스트에 대한 대댓글 생성
    public void recomment() {
        Random random = new Random();
        //List<Long> commentIds = commentRepository.findAllCommentIds();
        List<Comment> comments = commentRepository.findAll();
        for(Comment comment : comments){
            int recommentCnt = random.nextInt(1)+1;

            for(int i = 0; i < recommentCnt; i++){
                System.out.println(comment.getCommentId());
                String recomment = context();
                Member member = memberGenerator.randomMemberExcept(comment.getCommentId());

                LocalDateTime createdDate = RandomGenerator.generateLocalDateTime(comment.getCreatedAt(), comment.getCreatedAt().plus(100, ChronoUnit.DAYS));

                recommentRepository.save(new Recomment(recomment, comment, member, createdDate, createdDate));
            }
        }
    }

}
