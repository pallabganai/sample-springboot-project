package com.mycompany.app.dataprovider.entity;

import com.mycompany.app.core.entity.CatFacts;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAT_FACTS")
@Getter
@Setter
public class CatFactsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int factId;
    private String fact;
    private int length;

    public CatFacts to() {
        return new CatFacts(fact, length);
    }

    public static CatFactsEntity from(CatFacts catFacts) {
        CatFactsEntity catFactsEntity = new CatFactsEntity();
        catFactsEntity.setFact(catFacts.getFact());
        catFactsEntity.setLength(catFacts.getLength());

        return catFactsEntity;
    }
}
