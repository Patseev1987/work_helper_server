package ru.diploma.inflate_server.services;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.repositories.ToolRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    public List<Tool> getToolByCodeLike(String code) {
        return toolRepository.findAllByCodeLike(code);
    }

    }



}
