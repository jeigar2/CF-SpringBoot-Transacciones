package es.comepiedras.retosprogramacion.repository;

import es.comepiedras.retosprogramacion.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
