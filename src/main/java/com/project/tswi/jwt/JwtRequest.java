package com.project.tswi.jwt;

import lombok.*;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtRequest {
    private String username;
    private String password;

}
