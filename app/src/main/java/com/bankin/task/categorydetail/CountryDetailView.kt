package com.bankin.task.categorydetail

import com.bankin.domain.countries.model.Country

interface CountryDetailView {
  fun initialiseView()
  fun getCountryDetail()
  fun showCountryDetail(country : Country)
}
