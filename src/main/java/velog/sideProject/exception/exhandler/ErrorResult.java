package velog.sideProject.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ErrorResult {
    private String timestamp;
    private String status;
    private String error;
    private String path;

    public ErrorResult(String status, String error, String path) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        this.timestamp = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS"));
        this.status = status;
        this.error = error;
        this.path = path;
    }

}
