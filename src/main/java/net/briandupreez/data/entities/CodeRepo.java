package net.briandupreez.data.entities;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepo extends CrudRepository<Code, Integer> {
}
