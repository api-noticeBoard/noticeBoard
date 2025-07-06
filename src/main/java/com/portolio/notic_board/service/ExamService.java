package com.portolio.notic_board.service;

import com.portolio.notic_board.entity.ExamEntity;
import com.portolio.notic_board.mapper.ExamMapper;
import com.portolio.notic_board.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository; // 레포지토리 객체명 변경
    private final ExamMapper examMapper; // 매퍼 객체명 변경

    @Transactional(readOnly = true)
    public List<ExamEntity> getAllExams() { // 메서드명 변경
        return examRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ExamEntity> getExamById(Long id) { // 메서드명 변경
        return examRepository.findById(id);
    }

    @Transactional
    public ExamEntity createExam(ExamEntity exam) { // 메서드명, 파라미터명 변경
        return examRepository.save(exam);
    }

    @Transactional
    public ExamEntity updateExam(Long id, ExamEntity updatedExam) { // 메서드명, 파라미터명 변경
        return examRepository.findById(id)
                .map(exam -> {
                    exam.setTitle(updatedExam.getTitle());
                    exam.setContent(updatedExam.getContent());
                    return examRepository.save(exam);
                }).orElseThrow(() -> new IllegalArgumentException("Exam not found with id: " + id));
    }

    @Transactional
    public void deleteExam(Long id) { // 메서드명 변경
        examRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ExamEntity> searchExams(String keyword) { // 메서드명 변경
        return examMapper.searchExams(keyword);
    }

    @Transactional(readOnly = true)
    public List<ExamEntity> getAllExamsUsingMyBatisXml() { // 메서드명 변경
        return examMapper.findAllExamsXml();
    }

    @Transactional(readOnly = true)
    public ExamEntity getExamByIdUsingMyBatisXml(Long id) { // 메서드명 변경
        return examMapper.findExamByIdXml(id);
    }

    @Transactional
    public int updateExamByMyBatis(ExamEntity exam) { // 메서드명, 파라미터명 변경
        return examMapper.updateExamByMyBatis(exam);
    }

    @Transactional
    public void insertExamByMyBatis(ExamEntity exam) { // 메서드명, 파라미터명 변경
        examMapper.insertExam(exam);
    }

    @Transactional
    public void deleteExamByMyBatis(Long id) { // 메서드명 변경
        examMapper.deleteExam(id);
    }
}
