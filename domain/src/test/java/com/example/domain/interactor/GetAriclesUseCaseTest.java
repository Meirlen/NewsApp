package com.example.domain.interactor;

import com.example.domain.repository.TickerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class GetAriclesUseCaseTest {

    private GetAriclesUseCase getAriclesUseCase;

    @Mock
    private TickerRepository tickerRepository;

    @Before
    public void setUp() throws Exception {
        getAriclesUseCase = new GetAriclesUseCase(tickerRepository);
    }

    @Test
    public void shouldDelegateCallToRepositoryFromRemote() {
        GetAriclesUseCase.Params params = new GetAriclesUseCase.Params("us","20","xxx",20);
        getAriclesUseCase.buildUseCaseSingle(params);
        Mockito.verify(tickerRepository).getArticles(params);
    }


}