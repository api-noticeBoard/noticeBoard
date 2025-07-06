package com.portolio.notic_board.controller;

import com.portolio.notic_board.dto.ExamDto;
import com.portolio.notic_board.entity.ExamEntity;
import com.portolio.notic_board.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/exams") // URL 경로 변경
public class ExamController {
    private final ExamService examService; // 서비스 객체명 변경

    // 시험 목록 페이지
    @GetMapping
    public String listExams(Model model) { // 메서드명 변경
        List<ExamEntity> exams = examService.getAllExams(); // 메서드 호출 및 변수명 변경
        model.addAttribute("exams", exams); // 모델 속성명 변경
        return "exam/list"; // 템플릿 경로 변경
    }

    // 시험 상세 페이지
    @GetMapping("/{id}")
    public String viewExam(@PathVariable Long id, Model model) { // 메서드명 변경
        ExamEntity exam = examService.getExamById(id) // 메서드 호출 및 변수명 변경
                .orElseThrow(() -> new IllegalArgumentException("Invalid exam ID: " + id));
        model.addAttribute("exam", exam); // 모델 속성명 변경
        return "exam/detail"; // 템플릿 경로 변경
    }

    // 시험 작성 폼 페이지
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("examDto", new ExamDto()); // DTO 객체명, 모델 속성명 변경
        return "exam/form"; // 템플릿 경로 변경
    }

    // 시험 작성 처리
    @PostMapping("/new")
    public String createExam(@ModelAttribute ExamDto examDto, RedirectAttributes redirectAttributes) { // 메서드명, 파라미터명 변경
        ExamEntity exam = ExamEntity.builder() // 엔티티 빌더명 변경
                .title(examDto.getTitle())
                .content(examDto.getContent())
                .author(examDto.getAuthor())
                .build();
        examService.createExam(exam); // 서비스 메서드 호출, 파라미터명 변경
        redirectAttributes.addFlashAttribute("message", "시험이 성공적으로 작성되었습니다."); // 메시지 변경
        return "redirect:/exams"; // 리다이렉트 경로 변경
    }

    // 시험 수정 폼 페이지
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ExamEntity exam = examService.getExamById(id) // 메서드 호출 및 변수명 변경
                .orElseThrow(() -> new IllegalArgumentException("Invalid exam ID: " + id));
        model.addAttribute("examDto", ExamDto.builder() // DTO 빌더명, 모델 속성명 변경
                .id(exam.getId())
                .title(exam.getTitle())
                .content(exam.getContent())
                .author(exam.getAuthor())
                .build());
        return "exam/form"; // 템플릿 경로 변경
    }

    // 시험 수정 처리
    @PostMapping("/edit/{id}")
    public String updateExam(@PathVariable Long id, @ModelAttribute ExamDto examDto, RedirectAttributes redirectAttributes) { // 메서드명, 파라미터명 변경
        ExamEntity exam = ExamEntity.builder() // 엔티티 빌더명 변경
                .id(id)
                .title(examDto.getTitle())
                .content(examDto.getContent())
                .author(examDto.getAuthor())
                .build();
        examService.updateExam(id, exam); // 서비스 메서드 호출, 파라미터명 변경
        redirectAttributes.addFlashAttribute("message", "시험이 성공적으로 수정되었습니다."); // 메시지 변경
        return "redirect:/exams/" + id; // 리다이렉트 경로 변경
    }

    // 시험 삭제 처리
    @PostMapping("/delete/{id}")
    public String deleteExam(@PathVariable Long id, RedirectAttributes redirectAttributes) { // 메서드명 변경
        examService.deleteExam(id); // 서비스 메서드 호출
        redirectAttributes.addFlashAttribute("message", "시험이 성공적으로 삭제되었습니다."); // 메시지 변경
        return "redirect:/exams"; // 리다이렉트 경로 변경
    }

    // 시험 검색 페이지
    @GetMapping("/search")
    public String searchExams(@RequestParam(value = "keyword", required = false) String keyword, Model model) { // 메서드명 변경
        List<ExamEntity> exams; // 변수명 변경
        if (keyword != null && !keyword.trim().isEmpty()) {
            exams = examService.searchExams(keyword); // 서비스 메서드 호출
        } else {
            exams = examService.getAllExams(); // 서비스 메서드 호출
        }
        model.addAttribute("exams", exams); // 모델 속성명 변경
        model.addAttribute("keyword", keyword);
        return "exam/list"; // 템플릿 경로 변경
    }
}
