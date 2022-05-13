package progkor.factsite.service.impl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import progkor.factsite.model.FactText;
import progkor.factsite.model.Theme;
import progkor.factsite.model.exception.NotFoundException;
import progkor.factsite.service.FactSiteService;


class FactSiteServiceImplTest {

    private static final FactText GENERAL_FACTS = new FactText(1L, "Test fact", Theme.GENERAL);
    private static final FactText ANIMAL_FACTS = new FactText(2L, "Test animal", Theme.ANIMALS);
    private static final List<FactText> FACT_TEXTS = List.of(
            GENERAL_FACTS,
            ANIMAL_FACTS
    );
    public static final long UNKNOWN_FACT_TEXT_ID = -1L;
    public static final String SCIENCE_FACT_TEXT_CONTEXT = "Test science";

    private FactSiteService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FactSiteServiceImpl(FACT_TEXTS);
    }

//    @Test
//    void getAllFactTextsShouldReturnAllFactTexts() {
//        // when
//        final List<FactText> actual = underTest.getAllFactTexts();
//        //then
//        assertThat(actual).isEqualTo(FACT_TEXTS);
//    }

    @Test
    void getFactTextShouldReturnFactTextWhenGivenIdOfExistingFactText() {
        //when
        final FactText actual = underTest.getFactText(ANIMAL_FACTS.getId());
        //then
        assertThat(actual).isEqualTo(ANIMAL_FACTS);
    }

    @Test
    void getFactTextShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingFactText () {
        assertThrows(NotFoundException.class, () -> underTest.getFactText(UNKNOWN_FACT_TEXT_ID));
    }

    @Test
    void createFactTextShouldReturnFactTextWhenDelegateIt() {
        //given
        final FactText scienceFactText = new FactText(null, SCIENCE_FACT_TEXT_CONTEXT, Theme.SCIENCE);
        final FactText expectedFactText = new FactText(3L, SCIENCE_FACT_TEXT_CONTEXT, Theme.SCIENCE);
        //when
        final FactText actual = underTest.createFactText(scienceFactText);
        //then
        assertThat(actual).isEqualTo(expectedFactText);
    }

    @Test
    void updateFactTextShouldReturnUpdatedFactTextWhenGivenIdOfExistingFactText() {
        //given
        final FactText scienceFactText = new FactText(null, SCIENCE_FACT_TEXT_CONTEXT, Theme.SCIENCE);
        final FactText expectedFactText = new FactText(ANIMAL_FACTS.getId(), SCIENCE_FACT_TEXT_CONTEXT, Theme.SCIENCE);
        //when
        final FactText actual = underTest.updateFactText(ANIMAL_FACTS.getId(), scienceFactText);
        //then
        assertThat(actual).isEqualTo(expectedFactText);
    }

    @Test
    void updateFactTextShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingFactText() {
        //given
        final FactText scienceFactText = new FactText(null, SCIENCE_FACT_TEXT_CONTEXT, Theme.SCIENCE);
        //when then
        assertThrows(NotFoundException.class, () -> underTest.updateFactText(UNKNOWN_FACT_TEXT_ID, scienceFactText));
    }

    @Test
    void deleteFactTextShouldDeleteFactTextWhenGivenIdOfFactText() {
        //given
        final List<FactText> expectedFactTexts = List.of(GENERAL_FACTS);
        //when
        underTest.deleteFactText(ANIMAL_FACTS.getId());
        final List<FactText> actual = underTest.getAllFactTexts();
        //then
        assertThat(actual).isEqualTo(expectedFactTexts);
        //Assertions.assertEquals(actual, expectedFactTexts);
    }
}