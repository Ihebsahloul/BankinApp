package com.bankin.task.categorydetail
import com.bankin.domain.countries.model.Country
import com.bankin.domain.countrydetail.GetCountryDetailUseCase
import com.bankin.task.mvp.CleanPresenter
import io.reactivex.SingleObserver
import javax.inject.Inject

class CountryDetailPresenter @Inject constructor(private val getCountryDetailUseCase: GetCountryDetailUseCase) : CleanPresenter<CountryDetailView>() {

  override fun initialise() {
    getView()?.initialiseView()
  }

  override fun disposeSubscriptions() {

  }

  fun showCountryDetail(country: Country) {
    getView()?.showCountryDetail(country)
  }
}
