package com.example.tmobilecodingchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.tmobilecodingchallenge.models.*
import com.example.tmobilecodingchallenge.remote.CardService
import com.example.tmobilecodingchallenge.repo.CardRepo
import com.example.tmobilecodingchallenge.utils.MainCoroutineScopeRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class CardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val captor = slot<List<Card>>()

    private val cardService = mockk<CardService>()

    private val mockObserver: Observer<List<Card>> = mockk(relaxed = true)

    private val repo = mockk<CardRepo>()

    private lateinit var card: Card

    @Before
    fun setup() {
        card = Card(
            CardX(
                Attributes(Font(10), ""),
                Description(AttributesX(FontX(10), ""), ""),
                null,
                Title(AttributesXX(FontXX(10), ""), "Value"),
                ""
            ),
            CardType.TITLE_DESCRIPTION
        )
    }

    @Test
    fun `given CardViewModel is initialized, when init block is ran, then cardsLD should be updated`() {
        coroutineScope.runBlockingTest {
            val responseDTO = ResponseDTO(Page(listOf(card)))
            coEvery { cardService.getResponse() } returns Response.success(responseDTO)
            coEvery { repo.getCards() } returns flow { emit(listOf(card)) }

            val cardViewModel = CardViewModel(repo)

            cardViewModel.cardsLD.observeForever(mockObserver)

            verify(exactly = 1) { mockObserver.onChanged(capture(captor)) }
            assertEquals(CardType.TITLE_DESCRIPTION, captor.captured.first().card_type)
        }
    }
}
