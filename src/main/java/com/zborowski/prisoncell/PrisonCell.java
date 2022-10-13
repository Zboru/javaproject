package com.zborowski.prisoncell;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "prison_cells")
@Getter
@Setter
@NoArgsConstructor
public class PrisonCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 30, name="cell_name")
    private String cellName;

    @Column(nullable = false, unique = false, length = 1, name ="capacity")
    private Integer capacity;

}
