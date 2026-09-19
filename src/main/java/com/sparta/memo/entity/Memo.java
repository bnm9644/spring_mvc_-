package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    // 데이터 삽입을 위해 생성자 생성
    public Memo(MemoRequestDto memoRequestDto) {
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
    }

    // 데이터 수정 위해 생성자 생성
    public void update(MemoRequestDto memoRequestDto) {
        this.username = memoRequestDto.getUsername();
        this.contents = memoRequestDto.getContents();
    }
}

// Getter/Setter , 생성자 없음