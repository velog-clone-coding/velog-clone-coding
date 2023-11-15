package velog.sideProject.entity;

import jakarta.persistence.*;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long authorityId;

    @Column(name = "role", length = 45, nullable = false)
    private String role;

}
