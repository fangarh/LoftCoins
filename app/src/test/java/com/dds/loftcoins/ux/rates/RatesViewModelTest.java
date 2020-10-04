package com.dds.loftcoins.ux.rates;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.dds.loftcoins.domain.coins.ICoin;
import com.dds.loftcoins.domain.coins.ICoinsRepository;
import com.dds.loftcoins.domainstub.CoinStub;
import com.dds.loftcoins.domainstub.CoinsRepositoryStub;
import com.dds.loftcoins.domainstub.CurencyRepositoryStub;
import com.dds.loftcoins.utils.RxShedulerStub;
import com.google.common.truth.Truth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;

@RunWith(AndroidJUnit4.class)
public class RatesViewModelTest {
    private RatesViewModel model;
    private CoinsRepositoryStub coinRepo;
    private CurencyRepositoryStub currRepo;

    @Before
    public void setUp() throws Exception {
        coinRepo = new CoinsRepositoryStub();
        currRepo = new CurencyRepositoryStub(ApplicationProvider.getApplicationContext());

        model = new RatesViewModel(coinRepo, currRepo, new RxShedulerStub());
    }

    @Test
    public void CoinsActionsTest() {
        TestObserver<List<ICoin>> testData = model.coins().test();
        List<ICoin> coins = Arrays.asList(new CoinStub(), new CoinStub());

        model.isRefreshing().test().assertValue(true);

        coinRepo.listings.onNext(coins);

        model.isRefreshing().test().assertValue(false);

        testData.assertValue(coins);

        ICoinsRepository.Query query = coinRepo.lastListingsQuery;
        Truth.assertThat(query).isNotNull();
        Truth.assertThat(query.forceUpdate()).isTrue();

        model.switchSortingOrder();
        model.isRefreshing().test().assertValue(false);

        coinRepo.listings.onNext(coins);

        model.isRefreshing().test().assertValue(false);

        query = coinRepo.lastListingsQuery;
        Truth.assertThat(query).isNotNull();
        Truth.assertThat(query.forceUpdate()).isFalse();
    }

    @After
    public void tearDown() throws Exception {

    }
}