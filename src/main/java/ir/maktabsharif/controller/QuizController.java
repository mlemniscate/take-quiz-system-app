package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.QuizMapper;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.service.QuizService;
import ir.maktabsharif.service.dto.QuizDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController extends BaseRestFul<Quiz, QuizDTO, Long, QuizService, QuizMapper> {

    public QuizController(QuizService service, QuizMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/all-student-active-quizzes/{id}")
    public ResponseEntity<List<QuizDTO>> getAllStudentActiveQuizzes(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.convertListEntityToDTO(
                        service.findAllStudentActiveQuizzes(id)
                )
        );
    }

    @GetMapping("/start-student-quiz/{studentId}/{quizId}")
    public ResponseEntity<LocalDateTime> start(@PathVariable Long studentId, @PathVariable Long quizId, HttpServletRequest request) {
        return ResponseEntity.ok(service.startStudentQuiz(studentId, quizId, request));
    }



//    @PostMapping("/persistMessage")
//    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
//        @SuppressWarnings("unchecked")
//        List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
//        if (messages == null) {
//            messages = new ArrayList<>();
//            request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
//        }
//        messages.add(msg);
//        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
//        return "redirect:/";
//    }
//
//    @GetMapping("/messages")
//    public List<String> process(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        @SuppressWarnings("unchecked")
//        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
//
//        if (messages == null) {
//            messages = new ArrayList<>();
//        }
//        return messages;
//    }

}
