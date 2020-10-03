package com.dds.loftcoins.ux.wallets;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.dds.loftcoins.domain.coins.ICoin;
import com.dds.loftcoins.domain.coins.ICoinsRepository;
import com.dds.loftcoins.domain.coins.ICurrencyRepository;
import com.dds.loftcoins.domain.coins.IWalletsRepository;
import com.dds.loftcoins.domain.coins.Transaction;
import com.dds.loftcoins.domain.coins.Wallet;
import com.dds.loftcoins.domain.coins.WalletsRepository;
import com.dds.loftcoins.utils.IRxSchedulers;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import timber.log.Timber;

class WalletsViewModel extends ViewModel {
    private final Subject<Integer> walletPosition = BehaviorSubject.createDefault(0);

    private final Observable<List<Wallet>> wallets;

    private final Observable<List<Transaction>> transactions;

    private final IWalletsRepository walletsRepo;

    private final ICurrencyRepository currencyRepo;

    private final IRxSchedulers schedulers;

    @Inject
    WalletsViewModel(ICoinsRepository coinsRepo, ICurrencyRepository currencyRepo, IRxSchedulers schedulers) {
        this.walletsRepo = (IWalletsRepository) new WalletsRepository(coinsRepo);
        this.currencyRepo = currencyRepo;
        this.schedulers = schedulers;

        wallets = currencyRepo.currency()
                .switchMap(walletsRepo::wallets)
                .doOnNext((wal) -> Timber.d("%s", wal))
                .replay(1)
                .autoConnect();

        transactions = wallets
                .switchMap((wallets) -> walletPosition
                        .map(wallets::get)
                )
                .switchMap(walletsRepo::transactions)
                .doOnNext((t) -> Timber.d("%s", t))
                .replay(1)
                .autoConnect();
    }

    @NonNull
    Observable<List<Wallet>> wallets() {
        return wallets.observeOn(schedulers.main());
    }

    @NonNull
    Observable<List<Transaction>> transactions() {
        return transactions.observeOn(schedulers.main());
    }

    @NonNull
    Completable addWallet() {
        return wallets
                .switchMapSingle((list) -> Observable
                        .fromIterable(list)
                        .map(Wallet::coin)
                        .map(ICoin::id)
                        .toList()
                )
                .switchMapCompletable((ids) -> currencyRepo
                        .currency()
                        .firstOrError()
                        .map((c) -> walletsRepo.addWallet(c, ids))
                        .ignoreElement()
                )
                .observeOn(schedulers.main());
    }

    void changeWallet(int position) {
        walletPosition.onNext(position);
    }
}

