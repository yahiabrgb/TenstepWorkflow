package TenstepWorkflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TenstepWorkflow.entities.Documents;



@Repository
public interface DocumentsRepository extends JpaRepository<Documents, String> {

}