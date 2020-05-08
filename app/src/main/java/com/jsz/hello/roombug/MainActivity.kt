package com.jsz.hello.roombug

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val disposables = Disposables()
    private val storage by lazy { MyDatabase.getInstance(this).storage() }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { update() }
    }

    private fun update() {
        disposables += Completable.fromAction { storage.updateData(PREPOPULATE_DATA, PETS) }
            .andThen(storage.getAll())
            .map { it }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe { users, _ -> textView.text = "${users.size}" }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()

    }
}

val PREPOPULATE_DATA = listOf(
    User("ironman", "Tony", "Stark"),
    User("batman", "Bruce", "Wane")
)

val PETS = listOf(
    Pet("doggo1", "Alpha", "ironman"),
    Pet("doggo2", "Bravo", "ironman")
)
