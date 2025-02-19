package biz.managers3.managers3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {
 private String code;

 public NotFoundException(String code, String message) {
     super(message);
     this.code = code;
 }

}
