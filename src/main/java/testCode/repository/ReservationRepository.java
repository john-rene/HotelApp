package testCode.repository;


import testCode.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}