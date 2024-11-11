package br.insper.projeto.feedback.controller;

import br.insper.projeto.feedback.dto.FeedbackDTO;
import br.insper.projeto.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> criarFeedback(@RequestHeader("Authorization") String jwtToken, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.criarFeedback(jwtToken, feedbackDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> listarFeedbacks(@RequestHeader("Authorization") String jwtToken) {
        return ResponseEntity.ok(feedbackService.listarFeedbacks(jwtToken));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterFeedback(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        return ResponseEntity.ok(feedbackService.obterFeedback(jwtToken, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirFeedback(@RequestHeader("Authorization") String jwtToken, @PathVariable String id) {
        feedbackService.excluirFeedback(jwtToken, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
