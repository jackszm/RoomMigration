package com.jsz.hello.roombug

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class Disposables {
    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    fun dispose() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}

operator fun Disposables.plusAssign(disposable: Disposable) {
    add(disposable)
}
