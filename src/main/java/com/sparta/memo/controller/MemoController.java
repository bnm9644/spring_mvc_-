package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controller 역할, HTML 따로 반환하지 않음 - RestController
@RestController
@RequestMapping("/api")
public class MemoController {

    // 데이터베이스 배우지 않은 상태, 연결도 하지 않은 상태에선 자바 컬렉션 Map 사용
    // Key, Value 형식으로 가는데, Long 은 ID,  Memo는 생성자로 만든 , name, contents 사용
    private final Map<Long, Memo> memoList = new HashMap<>();

    /*
    * 데이터 Body 부분에 JSON 형태로 넘어감.
    * - Postman : Post - http://localhost:8080/api/memos : raw -> JSON 방식으로 삽입 - Send - 작성
    *
    */
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {

        // 1.RequestDto -> Entity 수정 - 저장 해야 함. alt + enter : 생성자 생성
        Memo memo = new Memo(memoRequestDto); // 세팅 후 객체 생성

        // 2. Memo의 Max ID를 찾아야 함. - ID 값으로 메모 구분을 함. 데이터베이스에 가장 마지막 값을 구해 거기서 +1을 하면 해결
        // apply_seq 같은 개념 - memoList.size()가 1이상이면 max+1 처리 아니면 1
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        //몇개가 있는지 찾음. KeySet - Key 값 가져옴

        memo.setId(maxId);

        // 3. DB 저장 - put
        memoList.put(memo.getId(), memo);

        // 4. Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    // 메모 조회 - Postman : Get - http://localhost:8080/api/memos - 이때까지 작성한 Memo 나옴
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map to List - ::new : 생성자 사용
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }
}
