package com.portolio.notic_board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor // 생성자 에러 해결을 위해 추가
@AllArgsConstructor // 생성자 에러 해결을 위해 추가
public class ExamDto {
    private Long id;
    private String title;
    private String content;
    private String author;
}
