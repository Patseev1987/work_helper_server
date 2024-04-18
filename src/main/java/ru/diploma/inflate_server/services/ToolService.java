package ru.diploma.inflate_server.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.repositories.ToolRepository;

@Service
@AllArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;

    public Tool save(Tool tool) {
        return toolRepository.save(tool);
    }
}
