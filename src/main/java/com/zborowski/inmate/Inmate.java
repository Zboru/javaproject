package com.zborowski.inmate;

import com.zborowski.prisoncell.PrisonCell;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inmates")
@Getter
@Setter
@NoArgsConstructor
public class Inmate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = false, length = 30, name="first_name")
    private String firstname;

    @Column(nullable = false, unique = false, length = 30, name ="last_name")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "prison_cell")
    private PrisonCell prisonCell;

}
