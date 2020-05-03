package br.edu.utfpr.libex7.application.service.books;

import br.edu.utfpr.libex7.application.ports.out.books.RemoveBookPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RemoveBookServiceTest {

    private RemoveBookPort port;
    private RemoveBookService service;

    @BeforeEach
    public void setup(){
        this.port = mock(RemoveBookPort.class);
        this.service = new RemoveBookService(port);
    }

    @Test
    @DisplayName("Given Book Id When Remove Then Do Nothing")
    public void givenBookIdWhenRemoveThenDoNothing(){
        this.service.remove(1L);
        verify(port, times(1)).remove(anyLong());
    }
}
