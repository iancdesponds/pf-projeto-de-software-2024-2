package br.insper.projeto.feedback.service;

import br.insper.projeto.feedback.dto.FeedbackDTO;
import br.insper.projeto.feedback.dto.UsuarioDTO;
import br.insper.projeto.feedback.model.Feedback;
import br.insper.projeto.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    private UsuarioDTO validarToken(String jwtToken) {
        RestTemplate restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.set("Authorization", jwtToken);

        var entity = new HttpEntity<>(headers);
        String url = "http://184.72.80.215/usuario/validate";

        try {
            var response = restTemplate.exchange(url, HttpMethod.GET, entity, UsuarioDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro ao validar token: " + e.getStatusCode(), e);
        }
    }

    public void criarFeedback(String jwtToken, FeedbackDTO feedbackDTO) {
        UsuarioDTO usuario = validarToken(jwtToken);
        if (!"ADMIN".equals(usuario.getPapel())) {
            throw new SecurityException("Apenas usuários com o papel ADMIN podem criar feedbacks.");
        }

        var feedback = new Feedback();
        feedback.setConteudo(feedbackDTO.getConteudo());
        feedback.setUsuarioId(usuario.getCpf());

        feedbackRepository.save(feedback);
    }

    public List<Feedback> listarFeedbacks(String jwtToken) {
        UsuarioDTO usuario = validarToken(jwtToken);
        if (!List.of("ADMIN", "DEVELOPER").contains(usuario.getPapel())) {
            throw new SecurityException("Apenas usuários com papéis ADMIN ou DEVELOPER podem listar feedbacks.");
        }
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> obterFeedback(String jwtToken, String id) {
        UsuarioDTO usuario = validarToken(jwtToken);
        if (!List.of("ADMIN", "DEVELOPER").contains(usuario.getPapel())) {
            throw new SecurityException("Apenas usuários com papéis ADMIN ou DEVELOPER podem consultar detalhes de feedbacks.");
        }
        return feedbackRepository.findById(id);
    }

    public void excluirFeedback(String jwtToken, String id) {
        UsuarioDTO usuario = validarToken(jwtToken);
        if (!"ADMIN".equals(usuario.getPapel())) {
            throw new SecurityException("Apenas usuários com o papel ADMIN podem excluir feedbacks.");
        }
        feedbackRepository.deleteById(id);
    }
}
