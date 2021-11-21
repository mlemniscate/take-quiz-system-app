package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.AnswerMapper;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.service.AnswerService;
import ir.maktabsharif.service.dto.AnswerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController extends BaseRestFul<Answer, AnswerDTO, Long, AnswerService, AnswerMapper> {

    public AnswerController(AnswerService service, AnswerMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/{questionId}/{studentId}")
    public ResponseEntity<AnswerDTO> saveUpdateAnswer(@RequestBody AnswerDTO answerDTO,
                                                      @PathVariable Long questionId,
                                                      @PathVariable Long studentId) {
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.saveQuestionAnswer(
                                mapper.convertDTOToEntity(answerDTO),
                                questionId,
                                studentId
                        )
                )
        );
    }
}
