package com.example.repsy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class packageUploadResponse {
    private String name;
    private String version;
    private String author;
    private List<DependencyDTO> dependencies;

    public class DependencyDTO {
        private String packageName;
        private String version;
    }
}
