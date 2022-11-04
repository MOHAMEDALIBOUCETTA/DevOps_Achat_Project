package tn.esprit.rh.achat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class secteurActiviteServiceTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @Test
    @Rollback(value = false)
    public void whenSaveUser_shouldReturnProduct(){
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("Test Code");
        when(secteurActiviteRepository.save(ArgumentMatchers.any(SecteurActivite.class))).thenReturn(secteurActivite);
        SecteurActivite created = secteurActiviteService.addSecteurActivite(secteurActivite);
        assertThat(created.getCodeSecteurActivite()).isSameAs(secteurActivite.getCodeSecteurActivite());
        verify(secteurActiviteRepository).save(secteurActivite);
    }

    @Test
    public void shouldReturnAllProducts(){
        List<SecteurActivite> secteurActiviteList = new ArrayList<>();
        secteurActiviteList.add( new SecteurActivite());
        given(secteurActiviteRepository.findAll()).willReturn(secteurActiviteList);
        List<SecteurActivite> expected = secteurActiviteService.retrieveAllSecteurActivite();
        assertEquals(expected, secteurActiviteList);
        verify(secteurActiviteRepository).findAll();
    }
}
