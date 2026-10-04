package com.wasil.journal_app.services;


import com.wasil.journal_app.dto.journals.JournalRequest;
import com.wasil.journal_app.dto.journals.JournalResponse;
import com.wasil.journal_app.exceptions.ResourceNotFoundException;
import com.wasil.journal_app.models.Journals;
import com.wasil.journal_app.models.User;
import com.wasil.journal_app.respository.JournalsRepository;
import com.wasil.journal_app.respository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JournalServiceTest {
    @InjectMocks
    private JournalService journalService;
    @Mock
    private JournalsRepository journalsRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void testCreateJournal(){
        Long userId = 10L;
        User user = new User();
        user.setUserId(userId);

        JournalRequest request = new JournalRequest();
        request.setTitle("My Title");
        request.setDescription("My Description");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        JournalResponse result = journalService.createJournal(request, userId);

        ArgumentCaptor<Journals> captor = ArgumentCaptor.forClass(Journals.class);
        verify(journalsRepository, times(1)).save(captor.capture());

        Journals saved = captor.getValue();
        assertEquals("My Title", saved.getTitle());
        assertEquals("My Description", saved.getDescription());
        assertSame(user, saved.getUser());

        assertNotNull(result);
        assertEquals("My Title", result.getTitle());
        assertEquals("My Description", result.getDescription());

        verify(userRepository).findById(userId);
        verifyNoMoreInteractions(userRepository, journalsRepository);
    }

    @Test
    void createJournal_userNotFound(){
        Long id = 11L;
        User user = new User();
        user.setUserId(id);
        JournalRequest request = new JournalRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> journalService.createJournal(request, id));

        verify(journalsRepository, never()).save(any(Journals.class));
    }

    @Test
    void getJournalsByUserId_test(){
        Long userId = 10L;
        User user = new User();
        user.setUserId(userId);

        Journals j1 = new Journals();
        j1.setTitle("First");
        j1.setUser(user);
        Journals j2 = new Journals();
        j2.setTitle("Second");
        j2.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(journalsRepository.findByUser(user)).thenReturn(List.of(j1, j2));

        List<JournalResponse> result = journalService.getJournalsByUserId(userId);

        assertEquals(2, result.size());
        assertEquals("First", result.get(0).getTitle());
        assertEquals("Second", result.get(1).getTitle());
    }

}
