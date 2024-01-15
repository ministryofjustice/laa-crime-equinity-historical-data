package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.crime.equinity.historicaldata.exception.ResourceNotFoundException;
import uk.gov.justice.laa.crime.equinity.historicaldata.model.Tasks;
import uk.gov.justice.laa.crime.equinity.historicaldata.repository.TasksRepository;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("equinity/tasks")
public class TaskController {
    private final TasksRepository tasksRepository;

    @GetMapping(value="/test")
    public ResponseEntity<LocalDateTime> getTasks() {
        Tasks task = tasksRepository.findById(5001483L)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + 5001483L));
        return new ResponseEntity<>(task.getDTOriginated(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<String> getTasksById(@PathVariable("id") long id) {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        Tasks task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));
        byte[] varBinary = task.getOFDImage();
        String str = new String(varBinary, StandardCharsets.UTF_8);
        String resultString = str.replaceAll("\u0000", "");
        JSONObject xmlJSONObj = XML.toJSONObject(resultString);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        return new ResponseEntity<>(jsonPrettyPrintString, HttpStatus.OK);
    }
}
