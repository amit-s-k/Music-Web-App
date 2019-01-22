package com.stackroute.music.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Music {
    @Id
    private int track_id;
    @Column(name = "track_name", nullable = false)
    private String track_name;
    @Column(name = "track_comments", nullable = false)
    private String track_comments;
}
