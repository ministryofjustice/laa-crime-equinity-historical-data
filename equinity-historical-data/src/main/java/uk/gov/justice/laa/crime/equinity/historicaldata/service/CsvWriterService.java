package uk.gov.justice.laa.crime.equinity.historicaldata.service;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class CsvWriterService {
    private final HttpServletResponse response;


    public CSVWriter open() throws IOException {
        return new CSVWriter(response.getWriter(), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
    }

    public String getResponseHeaderFilename(String csvFileName) {
        return String.format(
                "attachment; filename=\"%s\"",
                csvFileName
        );
    }

    public void setupResponseHeaders(String csvFileName) {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, getResponseHeaderFilename(csvFileName));
    }

    public void writeLine(CSVWriter writer, String[] csvDataLine) {
        writer.writeNext(csvDataLine);
    }

    public void close(CSVWriter writer) throws IOException {
        writer.close();
    }
}