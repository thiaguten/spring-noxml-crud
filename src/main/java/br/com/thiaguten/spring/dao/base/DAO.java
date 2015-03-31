package br.com.thiaguten.spring.dao.base;

import java.io.Serializable;
import java.util.List;

import br.com.thiaguten.spring.model.base.Persistable;

public interface DAO<T extends Persistable<? extends Serializable>, PK extends Serializable> {

	Class<T> getEntidadeClazz();

	Class<PK> getChavePrimariaClazz();

	T salvar(T t);

	T recuperar(PK pk);

	T alterar(T t);

	void deletar(T t);

	void deletarPorId(PK pk);

	List<T> listar();

}
