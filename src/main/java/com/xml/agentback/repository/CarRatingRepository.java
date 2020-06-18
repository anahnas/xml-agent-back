package com.xml.agentback.repository;

        import com.xml.agentback.model.CarRating;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;
        import java.util.Optional;

public interface CarRatingRepository extends JpaRepository<CarRating, Long> {
    @Override
    Optional<CarRating> findById(Long aLong);
    @Override
    List<CarRating> findAll();
    @Override
    void deleteById(Long id);
    @Override
    CarRating save(CarRating fuelType);

    List<CarRating> findAllByCarId(Long id);

    List<CarRating> findAllByUserId(Long id);

}
