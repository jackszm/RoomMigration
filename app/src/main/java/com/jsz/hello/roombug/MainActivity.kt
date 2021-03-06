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
        textView.text = ""
        disposables += Completable.fromAction { storage.updateData(USERS, PETS) }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe { textView.text = "Updated" }
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }
}

val USERS = listOf(
    User("user_0001", "Jon", "Snow"),
    User("user_0002", "Daenerys", "Targaryen")
)

val PETS = listOf(
    Pet("pet_0001", "Drogon", "user_0001"),
    Pet("pet_0002", "Ghost", "user_0002")
)
