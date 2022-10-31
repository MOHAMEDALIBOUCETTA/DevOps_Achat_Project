package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AchatApplicationTests {


    @Test
    void contextLoads() {
    }

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Test
    @Rollback(value = false)
    public void whenSaveUser_shouldReturnProduct(){
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setCodeCategorie("Test Code");
        when(categorieProduitRepository.save(ArgumentMatchers.any(CategorieProduit.class))).thenReturn(categorieProduit);
        CategorieProduit created = categorieProduitService.addCategorieProduit(categorieProduit);
        assertThat(created.getCodeCategorie()).isSameAs(categorieProduit.getCodeCategorie());
        verify(categorieProduitRepository).save(categorieProduit);
    }

}
