package com.adelmo.apiUrnaEletronica.entities;

import com.adelmo.apiUrnaEletronica.enums.ElectoralPositionsEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_position")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ElectoralPositionsEnum positionName;
}
