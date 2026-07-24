package cz.uhk.pro2kf2026;

import cz.uhk.pro2kf2026.model.User;
import cz.uhk.pro2kf2026.repository.UserRepository;
import cz.uhk.pro2kf2026.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUser_ShouldReturnUser_WhenExists() {
        User mockUser = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUser(1L);

        assertNotNull(result);
        assertEquals(mockUser, result);
    }

    @Test
    void saveUser_WithBlankPassword_ShouldKeepOriginalPassword() {
        User mockUser = mock(User.class);
        User mockOriginalUser = mock(User.class);

        when(mockUser.getPassword()).thenReturn(" ");
        when(mockUser.getId()).thenReturn(1L);
        when(mockOriginalUser.getPassword()).thenReturn("oldEncodedPassword");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockOriginalUser));

        userService.saveUser(mockUser);

        verify(mockUser).setPassword("oldEncodedPassword");
        verify(userRepository).save(mockUser);
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    void saveUser_WithNewPassword_ShouldEncodePassword() {
        User mockUser = mock(User.class);

        when(mockUser.getPassword()).thenReturn("newPassword");
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");

        userService.saveUser(mockUser);

        verify(mockUser).setPassword("encodedPassword");
        verify(userRepository).save(mockUser);
    }

    @Test
    void deleteUser_ShouldCallDelete_WhenExists() {
        User mockUser = mock(User.class);
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void getAllUsers_ShouldReturnList() {
        List<User> mockList = Arrays.asList(mock(User.class), mock(User.class));
        when(userRepository.findAll()).thenReturn(mockList);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails() {
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(mockUser);

        UserDetails result = userService.loadUserByUsername("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(userRepository).findByUsername("testUser");
    }
}
