package com.azbj.otcqc;

import com.azbj.otcqc.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class RejectServiceTest {

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private RejectServiceTest rejectServiceTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRejectApplications() {
        // Given
        List<String> applicationNumbers = Arrays.asList("APP123", "APP456", "APP789");
        when(validationService.rejectApplications()).thenReturn(applicationNumbers);

        // When
        List<String> result = validationService.rejectApplications();

        // Then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains("APP123"));
        assertTrue(result.contains("APP456"));
        assertTrue(result.contains("APP789"));

        // Verify
        verify(validationService, times(1)).rejectApplications();
    }
}
