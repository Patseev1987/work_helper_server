package ru.diploma.inflate_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.diploma.inflate_server.model.Tool;
import ru.diploma.inflate_server.services.ToolService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;
    @GetMapping("/tools")
    public List<Tool> getAllTools() {
        return toolService.getAllTools();
    }

    @GetMapping("/tools/code")
    public List<Tool> getToolByCodeLike(@RequestParam String code) {
       return toolService.getToolByCodeLike(code);
    }
}
