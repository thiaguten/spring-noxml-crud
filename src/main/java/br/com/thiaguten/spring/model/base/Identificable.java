package br.com.thiaguten.spring.model.base;

import java.io.Serializable;

public interface Identificable<PK extends Serializable> {

    PK getId();

}
