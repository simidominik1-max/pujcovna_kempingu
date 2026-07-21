package cz.uhk.pro2kf2026.service;


import cz.uhk.pro2kf2026.model.Rental;

import java.util.List;

public interface RentalService {
    Rental getRental(long id);
    void saveRental(Rental rental);
    void deleteRental(long id);
    List<Rental> getAllRentals();


}
