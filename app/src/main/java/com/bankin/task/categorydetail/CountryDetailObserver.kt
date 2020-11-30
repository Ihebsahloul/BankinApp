package com.bankin.task.categorydetail

import com.bankin.domain.countries.model.Country
import io.reactivex.observers.DisposableSingleObserver

class CountryDetailObserver(private val presenter: CountryDetailPresenter): DisposableSingleObserver<Country>() {


  override fun onError(e: Throwable) {
    e.printStackTrace()
  }

  override fun onSuccess(t: Country) {
    presenter.showCountryDetail(t)
  }
}
