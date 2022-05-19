package com.example.flatB.common;

public class ResponseMessage {
    //회원관련
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String NOT_DUPLICATED_USERID = "회원 아이디 사용 가능";
    public static final String DUPLICATED_USERID = "회원 아이디 중복";
    public static final String NOT_DUPLICATED_NICKNAME = "닉네임 사용 가능";
    public static final String DUPLICATED_NICKNAME = "닉네임 중복";

    //게시글 관련
    public static final String POST_CREATED = "게시글 작성 성공";
    public static final String POST_NOT_CREATED = "게시글 작성 실패";
    public static final String POST_READ = "게시글 조회 성공";
    public static final String POST_READ_FAIL = "게시글 조회 실패";
    public static final String POST_UPDATE = "게시글 수정 성공";
    public static final String POST_UPDATE_FAIL = "게시글 수정 실패";
    public static final String POST_DELETE = "게시글 삭제 성공";
    public static final String POST_DELETE_FAIL = "게시글 삭제 실패";

    //크롤링관련
    public static final String CRAWLLING_SUCCESS = "크롤링 성공";
    public static final String CRAWLLING_FAIL = "크롤링 실패";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";

}
