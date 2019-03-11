package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.Voo;

@Repository
public interface VooDao extends JpaRepository<Voo, Integer> {

	//Query createQuery(String string);

}
