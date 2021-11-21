package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.BaseQuestionMapper;
import ir.maktabsharif.domain.BaseQuestion;
import ir.maktabsharif.service.BaseQuestionService;
import ir.maktabsharif.service.dto.BaseQuestionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseQuestionRestFull<E extends BaseQuestion, D extends BaseQuestionDTO, S extends BaseQuestionService<E>,
        M extends BaseQuestionMapper<E, D>> extends BaseRestFul<E, D, Long, S, M> {


    public BaseQuestionRestFull(S service, M mapper) {
        super(service, mapper);
    }

    @GetMapping("/{courseId}/{teacherId}")
    public ResponseEntity<List<D>> getCourseAndTeacherQuestionBank(
            @PathVariable Long courseId,@PathVariable Long teacherId
    ) {
        return ResponseEntity.ok(
                mapper.convertListEntityToDTO(
                        service.findByCourseIdAndTeacherId(courseId, teacherId)
                )
        );
    }

    @GetMapping("/get-questions/{quizId}")
    public ResponseEntity<List<D>> getQuizQuestions(
            @PathVariable Long quizId
    ) {
        List<E> eList = service.findAllByQuizId(quizId);
        List<D> ds = mapper.convertListEntityToDTO(eList);
        return ResponseEntity.ok(ds);
    }

    @PostMapping("/{courseId}/{teacherId}/{quizId}")
    public ResponseEntity<D> saveQuestion(
            @PathVariable Long courseId,@PathVariable Long teacherId, @PathVariable Long quizId,
            @RequestBody D questionDTO
    ) {
        E question = mapper.convertDTOToEntity(questionDTO);
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.saveNotSecure(question, courseId, teacherId, quizId)
                )
        );
    }

    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long quizId, @RequestBody D questionDTO){
        service.deleteNotSecure(quizId, mapper.convertDTOToEntity(questionDTO));
        return ResponseEntity.ok().build();
    }
}
