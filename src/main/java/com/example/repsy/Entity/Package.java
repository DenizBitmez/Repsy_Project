package com.example.repsy.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String version;

    public String name;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "jsonb")
    private String dependencies;

    private LocalDateTime uploadedAt;

    public Package(String packageName, String version, LocalDateTime now) {
    }
}
