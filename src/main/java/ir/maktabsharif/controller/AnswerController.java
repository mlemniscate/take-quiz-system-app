package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.AnswerMapper;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.service.AnswerService;
import ir.maktabsharif.service.dto.AnswerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController extends BaseRestFul<Answer, AnswerDTO, Long, AnswerService, AnswerMapper> {

    public AnswerController(AnswerService service, AnswerMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/{questionId}/{studentId}/{quizId}")
    public ResponseEntity<AnswerDTO> saveUpdateAnswer(@RequestBody AnswerDTO answerDTO,
                                                      @PathVariable Long questionId,
                                                      @PathVariable Long studentId,
                                                      @PathVariable Long quizId) {
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.saveQuestionAnswer(
                                mapper.convertDTOToEntity(answerDTO),
                                questionId,
                                studentId,
                                quizId
                        )
                )
        );
    }

    @GetMapping("/{studentId}/{quizId}")
    public ResponseEntity<List<AnswerDTO>> findAllQuizAnswersByStudent(@PathVariable Long studentId,
                                                                        @PathVariable Long quizId){
        return ResponseEntity.ok(
                mapper.convertListEntityToDTO(
                        service.findAllQuizAnswersByStudent(studentId, quizId)
                )
        );
    }

    // modify all the multi questions of an student and set the score of them
    @GetMapping("/multi-modification/{studentId}/{quizId}")
    public ResponseEntity<Void> modifyStudentQuiz(@PathVariable Long studentId,
                                                  @PathVariable Long quizId) {
        service.modifyStudentQuiz(studentId, quizId);
        return ResponseEntity.ok().build();
    }


}
