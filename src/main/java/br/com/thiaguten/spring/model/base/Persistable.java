package br.com.thiaguten.spring.model.base;

import java.io.Serializable;

public interface Persistable<PK extends Serializable> extends Identificable<PK>, Serializable {

}
