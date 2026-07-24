package cz.uhk.pro2kf2026;

import cz.uhk.pro2kf2026.model.Item;
import cz.uhk.pro2kf2026.model.Rental;
import cz.uhk.pro2kf2026.repository.RentalRepository;
import cz.uhk.pro2kf2026.service.RentalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Test
    void getRental_ShouldReturnRental_WhenExists() {
        Rental mockRental = mock(Rental.class);
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(mockRental));

        Rental result = rentalService.getRental(1L);

        assertNotNull(result);
        assertEquals(mockRental, result);
    }

    @Test
    void saveRental_ShouldCallRepositorySave() {
        Rental mockRental = mock(Rental.class);

        rentalService.saveRental(mockRental);

        verify(rentalRepository).save(mockRental);
    }

    @Test
    void deleteRental_ShouldCallDelete_WhenExists() {
        Rental mockRental = mock(Rental.class);
        when(rentalRepository.findById(1L)).thenReturn(Optional.of(mockRental));

        rentalService.deleteRental(1L);

        verify(rentalRepository).deleteById(1L);
    }

    @Test
    void getAllRentals_ShouldReturnList() {
        List<Rental> mockList = Arrays.asList(mock(Rental.class));
        when(rentalRepository.findAll()).thenReturn(mockList);

        List<Rental> result = rentalService.getAllRentals();

        assertEquals(1, result.size());
    }

    @Test
    void getRental_ShouldReturnNull_WhenNotExists() {
        when(rentalRepository.findById(99L)).thenReturn(Optional.empty());

        Rental result = rentalService.getRental(99L);

        assertNull(result);
    }

    @Test
    void deleteRental_ShouldNotCallDelete_WhenItemDoesNotExist() {
        when(rentalRepository.findById(99L)).thenReturn(Optional.empty());

        rentalService.deleteRental(99L);

        verify(rentalRepository, never()).deleteById(anyLong());
    }
}