package uk.gov.justice.laa.crime.equinity.historicaldata.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("equinity/tasks")
public class TaskController {
    private final TasksRepository tasksRepository;

    @GetMapping(value="/test")
    public ResponseEntity<String> getTasks() {
        return getTasksById(5001483L);
    }

    @GetMapping(value="/{id}")
    private ResponseEntity<String> getTasksById(@PathVariable("id") long id) {
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
