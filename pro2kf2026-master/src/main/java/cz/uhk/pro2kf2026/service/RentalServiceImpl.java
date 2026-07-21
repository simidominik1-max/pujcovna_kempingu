package cz.uhk.pro2kf2026.service;


import cz.uhk.pro2kf2026.model.Rental;
import cz.uhk.pro2kf2026.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental getRental(long id) {
        return rentalRepository.findById(id).orElse(null);

    }

    @Override
    public void saveRental(Rental rental)  {
        rentalRepository.save(rental);
    }

    @Override
    public void deleteRental(long id) {
        rentalRepository.findById(id).ifPresent(rental -> rentalRepository.deleteById(id));
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }


}
